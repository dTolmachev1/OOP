package io.github.dtolmachev1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

class PatternSearchingTest {
    @BeforeAll
    static void setUp() {
        /* initializing variables */
        random = new Random();
        empty = "";
        singleCharacter = generateRandomString(1);
        small = generateRandomString(1024);
        StringBuilder buffer = new StringBuilder(2097152);
        for(int i = 0; i < 1024; i++) {
            if(i != 0) {
                buffer.append('\n');
            }
            buffer.append(generateRandomString(1024));
        }
        medium = buffer.toString();

        /* creating files */
        try {  // empty file
            if (!Files.exists(EMPTY_FILE)) {
                Files.createFile(EMPTY_FILE);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // single-character file
            if(!Files.exists(SINGLE_CHARACTER_FILE)) {
                Files.createFile(SINGLE_CHARACTER_FILE);
            }
            BufferedWriter writer = Files.newBufferedWriter(SINGLE_CHARACTER_FILE, CHARSET);
            writer.write(singleCharacter);
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // small file
            if(!Files.exists(SMALL_FILE)) {
                Files.createFile(SMALL_FILE);
            }
            BufferedWriter writer = Files.newBufferedWriter(SMALL_FILE, CHARSET);
            writer.write(small);
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // medium file
            if(!Files.exists(MEDIUM_FILE)) {
                Files.createFile(MEDIUM_FILE);
            }
            BufferedWriter writer = Files.newBufferedWriter(MEDIUM_FILE, CHARSET);
            writer.write(medium);
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown() {
        /* deleting files */
        try {  // empty file
            Files.deleteIfExists(EMPTY_FILE);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // single-character file
            Files.deleteIfExists(SINGLE_CHARACTER_FILE);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // small file
            Files.deleteIfExists(SMALL_FILE);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // medium file
            Files.deleteIfExists(MEDIUM_FILE);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Empty pattern, Empty text, Text doesn't contain this pattern")
    void findOccurrences_EmptyEmptyNotContain() {
        String pattern = "";
        src = PatternSearching.findOccurrences(EMPTY_FILE.toString(), pattern);
        exp = findOccurrences(pattern, empty);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Empty text, Text doesn't contain this pattern")
    void findOccurrences_SingleCharacterEmptyNotContain() {
        String pattern = generateRandomString(1);
        src = PatternSearching.findOccurrences(EMPTY_FILE.toString(), pattern);
        exp = findOccurrences(singleCharacter, pattern);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Empty pattern, Single-character text, Text doesn't contain this pattern")
    void findOccurrences_EmptySingleCharacterNotContain() {
        String pattern = "";
        src = PatternSearching.findOccurrences(SINGLE_CHARACTER_FILE.toString(), pattern);
        exp = findOccurrences(pattern, singleCharacter);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Single-character text, text doesn't contain this pattern")
    void findOccurrences_SingleCharacterSingleCharacterNotContain() {
        String pattern = generateRandomString(singleCharacter.length());
        while(pattern.equals(singleCharacter)) {
            pattern = generateRandomString(singleCharacter.length());
        }
        src = PatternSearching.findOccurrences(SINGLE_CHARACTER_FILE.toString(), pattern);
        exp = findOccurrences(pattern, singleCharacter);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Single-character text, text contains this pattern")
    void findOccurrences_SingleCharacterSingleCharacterContains() {
        String pattern = singleCharacter;
        src = PatternSearching.findOccurrences(SINGLE_CHARACTER_FILE.toString(), pattern);
        exp = findOccurrences(pattern, singleCharacter);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Single-character text, Text doesn't contain this pattern")
    void findOccurrences_MultiCharacterSingleCharacterNotContain() {
        String pattern = generateRandomString(2 * singleCharacter.length());
        src = PatternSearching.findOccurrences(SINGLE_CHARACTER_FILE.toString(), pattern);
        exp = findOccurrences(pattern, singleCharacter);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Empty pattern, Small text, Text doesn't contain this pattern")
    void findOccurrences_EmptySmallNotContain() {
        String pattern = "";
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Small text")
    void findOccurrences_SingleCharacterSmall() {
        String pattern = generateRandomString(1);
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Small text, Text contains this pattern")
    void findOccurrences_SingleCharacterSmallContains() {
        int position = random.nextInt(small.length());
        String pattern = small.substring(position, position + 1);
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Small text, Text starts with this pattern")
    void findOccurrences_MultiCharacterSmallStarts() {
        int position = 1 + random.nextInt(small.length());
        String pattern = small.substring(0, position);
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Small text, Text ends with this pattern")
    void findOccurrences_MultiCharacterSmallEnds() {
        int position = random.nextInt(small.length());
        String pattern = small.substring(position);
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Small text")
    void findOccurrences_MultiCharacterSmall() {
        String pattern = generateRandomString(1 + random.nextInt(small.length()));
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Small text, Text contains this pattern")
    void findOccurrences_MultiCharacterSmallContains() {
        int position = random.nextInt(small.length());
        String pattern = small.substring(position, position + random.nextInt(small.length() - position + 1));
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Small pattern, Small text, Text doesn't contain this pattern")
    void findOccurrences_SmallSmallNotContain() {
        String pattern = generateRandomString(small.length());
        while(pattern.equals(small)) {
            small = generateRandomString(small.length());
        }
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Small pattern, Small text, Text contains this pattern")
    void findOccurrences_SmallSmallContains() {
        String pattern = small;
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern,  text, Text doesn't contain this pattern")
    void findOccurrences_MultiCharacterSmallNotContain() {
        String pattern = generateRandomString(2 * small.length());
        src = PatternSearching.findOccurrences(SMALL_FILE.toString(), pattern);
        exp = findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Medium text")
    void findOccurrences_MultiCharacterMedium() {
        String pattern = generateRandomString(2048);
        src = PatternSearching.findOccurrences(MEDIUM_FILE.toString(), pattern);
        exp = findOccurrences(pattern, medium);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Medium text, Text contains this pattern")
    void findOccurrences_MultiCharacterMediumContains() {
        int position = random.nextInt(medium.length() - 2048);
        String pattern = medium.substring(position, position + 2048);
        src = PatternSearching.findOccurrences(MEDIUM_FILE.toString(), pattern);
        exp = findOccurrences(pattern, medium);
        Assertions.assertEquals(src, exp);
    }

    private static Random random;
    private ArrayList<Integer> src;
    private ArrayList<Integer> exp;
    private static String empty;
    private static String singleCharacter;
    private static String small;
    private static String medium;
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final Path EMPTY_FILE = Paths.get("src", "test", "resources", "empty.txt");
    private static final Path SINGLE_CHARACTER_FILE = Paths.get("src", "test", "resources", "single-character.txt");
    private static final Path SMALL_FILE = Paths.get("src", "test", "resources", "small.txt");
    private static final Path MEDIUM_FILE = Paths.get("src", "test", "resources", "medium.txt");

    /* reference function to find all occurrences of a given pattern */
    private ArrayList<Integer> findOccurrences(String pattern, String text) {
        ArrayList<Integer> occurrences = new ArrayList<>();  // for storing occurrences positions
        if(pattern.length() != 0 && text.length() != 0) {
            int idx = 0;
            while (idx != -1) {
                idx = text.indexOf(pattern, idx);
                if (idx != -1) {
                    occurrences.add(idx);
                    idx++;
                }
            }
        }
        return occurrences;
    }

    /* generates random string of a specific length */
    private static String generateRandomString(int length) {
        return random.ints(Character.MIN_VALUE, Character.MAX_VALUE+1).filter(codePoint -> !Character.isSurrogate((char) codePoint)).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}