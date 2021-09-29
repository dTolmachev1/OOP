package io.github.dtolmachev1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
            if (!Files.exists(EMPTY_PATH)) {
                Files.createFile(EMPTY_PATH);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // single-character file
            if(!Files.exists(SINGLE_CHARACTER_PATH)) {
                Files.createFile(SINGLE_CHARACTER_PATH);
            }
            BufferedWriter writer = Files.newBufferedWriter(SINGLE_CHARACTER_PATH, CHARSET);
            writer.write(singleCharacter);
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // small file
            if(!Files.exists(SMALL_PATH)) {
                Files.createFile(SMALL_PATH);
            }
            BufferedWriter writer = Files.newBufferedWriter(SMALL_PATH, CHARSET);
            writer.write(small);
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // medium file
            if(!Files.exists(MEDIUM_PATH)) {
                Files.createFile(MEDIUM_PATH);
            }
            BufferedWriter writer = Files.newBufferedWriter(MEDIUM_PATH, CHARSET);
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
            Files.deleteIfExists(EMPTY_PATH);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // single-character file
            Files.deleteIfExists(SINGLE_CHARACTER_PATH);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // small file
            Files.deleteIfExists(SMALL_PATH);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {  // medium file
            Files.deleteIfExists(MEDIUM_PATH);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Empty pattern, Empty text, Text doesn't contain this pattern")
    void findOccurrences_EmptyEmptyNotContain() {
        String pattern = "";
        exp = findOccurrences(pattern, empty);
        src = PatternSearching.findOccurrences(pattern, EMPTY_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, EMPTY_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, empty);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Empty text, Text doesn't contain this pattern")
    void findOccurrences_SingleCharacterEmptyNotContain() {
        String pattern = generateRandomString(1);
        exp = findOccurrences(pattern, empty);
        src = PatternSearching.findOccurrences(pattern, EMPTY_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, EMPTY_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, empty);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Empty pattern, Single-character text, Text doesn't contain this pattern")
    void findOccurrences_EmptySingleCharacterNotContain() {
        String pattern = "";
        exp = findOccurrences(pattern, singleCharacter);
        src = PatternSearching.findOccurrences(pattern, SINGLE_CHARACTER_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SINGLE_CHARACTER_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, singleCharacter);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Single-character text, text doesn't contain this pattern")
    void findOccurrences_SingleCharacterSingleCharacterNotContain() {
        String pattern = generateRandomString(singleCharacter.length());
        while(pattern.equals(singleCharacter)) {
            pattern = generateRandomString(singleCharacter.length());
        }
        exp = findOccurrences(pattern, singleCharacter);
        src = PatternSearching.findOccurrences(pattern, SINGLE_CHARACTER_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SINGLE_CHARACTER_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, singleCharacter);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Single-character text, text contains this pattern")
    void findOccurrences_SingleCharacterSingleCharacterContains() {
        String pattern = singleCharacter;
        exp = findOccurrences(pattern, singleCharacter);
        src = PatternSearching.findOccurrences(pattern, SINGLE_CHARACTER_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SINGLE_CHARACTER_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, singleCharacter);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Single-character text, Text doesn't contain this pattern")
    void findOccurrences_MultiCharacterSingleCharacterNotContain() {
        String pattern = generateRandomString(2 * singleCharacter.length());
        exp = findOccurrences(pattern, singleCharacter);
        src = PatternSearching.findOccurrences(pattern, SINGLE_CHARACTER_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SINGLE_CHARACTER_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, singleCharacter);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Empty pattern, Small text, Text doesn't contain this pattern")
    void findOccurrences_EmptySmallNotContain() {
        String pattern = "";
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Small text")
    void findOccurrences_SingleCharacterSmall() {
        String pattern = generateRandomString(1);
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Single-character pattern, Small text, Text contains this pattern")
    void findOccurrences_SingleCharacterSmallContains() {
        int position = random.nextInt(small.length());
        String pattern = small.substring(position, position + 1);
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Small text, Text starts with this pattern")
    void findOccurrences_MultiCharacterSmallStarts() {
        int position = 1 + random.nextInt(small.length());
        String pattern = small.substring(0, position);
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Small text, Text ends with this pattern")
    void findOccurrences_MultiCharacterSmallEnds() {
        int position = random.nextInt(small.length());
        String pattern = small.substring(position);
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Small text")
    void findOccurrences_MultiCharacterSmall() {
        String pattern = generateRandomString(1 + random.nextInt(small.length()));
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Small text, Text contains this pattern")
    void findOccurrences_MultiCharacterSmallContains() {
        int position = random.nextInt(small.length());
        String pattern = small.substring(position, position + random.nextInt(small.length() - position + 1));
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Small pattern, Small text, Text doesn't contain this pattern")
    void findOccurrences_SmallSmallNotContain() {
        String pattern = generateRandomString(small.length());
        while(pattern.equals(small)) {
            small = generateRandomString(small.length());
        }
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Small pattern, Small text, Text contains this pattern")
    void findOccurrences_SmallSmallContains() {
        String pattern = small;
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern,  text, Text doesn't contain this pattern")
    void findOccurrences_MultiCharacterSmallNotContain() {
        String pattern = generateRandomString(2 * small.length());
        exp = findOccurrences(pattern, small);
        src = PatternSearching.findOccurrences(pattern, SMALL_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, SMALL_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, small);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Medium text")
    void findOccurrences_MultiCharacterMedium() {
        String pattern = generateRandomString(2048);
        exp = findOccurrences(pattern, medium);
        src = PatternSearching.findOccurrences(pattern, MEDIUM_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, MEDIUM_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, medium);
        Assertions.assertEquals(src, exp);
    }

    @Test
    @DisplayName("Multi-character pattern, Medium text, Text contains this pattern")
    void findOccurrences_MultiCharacterMediumContains() {
        int position = random.nextInt(medium.length() - 2048);
        String pattern = medium.substring(position, position + 2048);
        exp = findOccurrences(pattern, medium);
        src = PatternSearching.findOccurrences(pattern, MEDIUM_FILE);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, MEDIUM_PATH);
        Assertions.assertEquals(src, exp);
        src = PatternSearching.findOccurrences(pattern, medium);
        Assertions.assertEquals(src, exp);
    }

    private static Random random;
    private List<Integer> src;
    private List<Integer> exp;
    private static String empty;
    private static String singleCharacter;
    private static String small;
    private static String medium;
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final char DELIMITER = '\uFFFD';
    private static final File EMPTY_FILE = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "empty.txt");
    private static final File SINGLE_CHARACTER_FILE = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "single-character.txt");
    private static final File SMALL_FILE = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "small.txt");
    private static final File MEDIUM_FILE = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "medium.txt");
    private static final Path EMPTY_PATH = Paths.get("src", "test", "resources", "empty.txt");
    private static final Path SINGLE_CHARACTER_PATH = Paths.get("src", "test", "resources", "single-character.txt");
    private static final Path SMALL_PATH = Paths.get("src", "test", "resources", "small.txt");
    private static final Path MEDIUM_PATH = Paths.get("src", "test", "resources", "medium.txt");

    /* reference function to find all occurrences of a given pattern */
    private List<Integer> findOccurrences(String pattern, String text) {
        List<Integer> occurrences = new ArrayList<>();  // for storing occurrences positions
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
        return random.ints(Character.MIN_VALUE, Character.MAX_VALUE+1).filter(codePoint -> (!Character.isSurrogate((char) codePoint)) && (((char) codePoint) != DELIMITER)).limit(length).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}