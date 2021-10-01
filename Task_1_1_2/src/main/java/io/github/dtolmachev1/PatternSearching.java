package io.github.dtolmachev1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class for pattern searching with z-algorithm.</p>
 */
public class PatternSearching {
    /**
     * <p>Finds all occurrences of a given pattern using z-function.</p>
     *
     * @param pattern desired substring.
     * @param file for searching.
     * @return <code>List</code> with occurrences positions.
     */
    public static List<Integer> findOccurrences(String pattern, File file) {
        List<Integer> occurrences = new ArrayList<>();
        if(pattern.length() != 0) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), CHARSET))) {
                occurrences = findOccurrences(pattern, reader);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return occurrences;
    }

    /**
     * <p>Finds all occurrences of a given pattern using z-function.</p>
     * 
     * @param pattern desired substring.
     * @param path to the file for searching.
     * @return <code>List</code> with occurrences positions.
     */
    public static List<Integer> findOccurrences(String pattern, Path path) {
        List<Integer> occurrences = new ArrayList<>();
        if(pattern.length() != 0) {
            try (BufferedReader reader = Files.newBufferedReader(path, CHARSET)) {
                occurrences = findOccurrences(pattern, reader);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return occurrences;
    }

    /**
     * <p>Finds all occurrences of a given pattern using z-function.</p>
     *
     * @param pattern desired substring.
     * @param text string for searching.
     * @return <code>List</code> with occurrences positions.
     */
    public static List<Integer> findOccurrences(String pattern, String text) {
        List<Integer> occurrences = new ArrayList<>();
        if(pattern.length() != 0) {
            try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
                occurrences = findOccurrences(pattern, reader);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return occurrences;
    }

    /**
     * <p>Finds all occurrences of a given pattern using z-function.</p>
     *
     * @param pattern desired substring.
     * @param reader with stream for  searching.
     * @return <code>List</code> with occurrences positions.
     */
    public static List<Integer> findOccurrences(String pattern, Reader reader) {
        List<Integer> occurrences = new ArrayList<>();
        try {
            char[] buffer = new char[16*pattern.length()];
            pattern.getChars(0, pattern.length(), buffer, 0);
            buffer[pattern.length()] = DELIMITER;
            reader.read(buffer, pattern.length() + 1, pattern.length() - 1);
            int length;  // for storing number of read characters
            for(int i = 0; (length = reader.read(buffer, 2 * pattern.length(), buffer.length - 2 * pattern.length())) != -1; i++) {
                int[] zArr = zFunction(buffer);
                for(int j = pattern.length() + 1; j < length + pattern.length() + 1; j++) {
                    if(zArr[j] == pattern.length()) {
                        occurrences.add(i * (buffer.length - 2 * pattern.length()) + j - pattern.length() - 1);
                    }
                }
                System.arraycopy(buffer, buffer.length - pattern.length() + 1, buffer, pattern.length() + 1, pattern.length() - 1);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return occurrences;
    }

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final char DELIMITER = '\uFFFD';

    /* builds z-array for a given string */
    private static int[] zFunction(char[] block) {
        int[] zArr = new int[block.length];  // for storing z-array
        int left = 0;  // left z-box boundary
        int right = 0;  // right z-box boundary
        for(int i = 1; i < block.length; i++) {
            if(i <= right) {
                zArr[i] = Math.min(right - i + 1, zArr[i-left]);
            }
            while((i + zArr[i] < block.length) && (block[zArr[i]] == block[i+zArr[i]])) {
                zArr[i]++;
            }
            if(i + zArr[i] - 1 > right) {
                left = i;
                right = i + zArr[i] - 1;
            }
        }
        return zArr;
    }
}
