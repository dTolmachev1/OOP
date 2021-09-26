package io.github.dtolmachev1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * <p>Class for pattern searching with z-algorithm.</p>
 */
public class PatternSearching {
    /**
     * <p>Finds all occurrences of a given pattern using z-function.</p>
     * 
     * @param fileName of a file for searching.
     * @param pattern desired substring.
     * @return <code>ArrayList</code> with occurrences positions.
     */
    public static ArrayList<Integer> findOccurrences(String fileName, String pattern) {
        ArrayList<Integer> occurrences = new ArrayList<>();
        if(pattern.length() != 0) {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
                occurrences = processText(reader, pattern);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return occurrences;
    }

    /* finds all occurrences of a given pattern using precalculated z-array */
    private static ArrayList<Integer> processText(BufferedReader reader, String pattern) {
        ArrayList<Integer> occurrences = new ArrayList<>();
        char[] buffer = new char[pattern.length()];
        try {
            reader.read(buffer, 0, buffer.length - 1);
            int[] zArr = zFunction(pattern);
            int left = 0;  // left z-box boundary
            int right = 0;  // right z-box boundary
            for (int i = 0; reader.read(buffer, buffer.length - 1, 1) != -1; i++) {
                if (((i == 0) || (i > right)) || ((i != 0) && (i <= right) && (zArr[i - left] >= right - i + 1))) {
                    left = i;
                    if ((i == 0) || (i > right)) {
                        right = i;
                    }
                    while ((right - i < buffer.length) && (right - left < buffer.length) && (buffer[right-i] == pattern.charAt(right - left))) {
                        right++;
                    }
                    if (right - left == buffer.length) {
                        occurrences.add(left);
                    }
                    right--;
                }
                System.arraycopy(buffer, 1, buffer, 0, buffer.length - 1);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return occurrences;
    }

    /* builds z-array for a given string */
    private static int[] zFunction(String string) {
        int[] zArr = new int[string.length()];  // for storing z-array
        int left = 0;  // left z-box boundary
        int right = 0;  // right z-box boundary
        for(int i = 1; i < string.length(); i++) {
            if((i > right) || ((i <= right) && (zArr[i-left] >= right - i + 1))) {
                left = i;
                if(i > right) {
                    right = i;
                }
                while((right < string.length()) && (string.charAt(right) == string.charAt(right - left))) {
                    right++;
                }
                zArr[i] = right - left;
                right--;
            }
            else if(zArr[i-left] < right - i + 1) {
                zArr[i] = zArr[i-left];
            }
        }
        return zArr;
    }
}
