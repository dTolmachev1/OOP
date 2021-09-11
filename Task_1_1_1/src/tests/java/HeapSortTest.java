import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class HeapSortTest {
    @BeforeAll
    static void setUp() {
        rand = new Random();
    }

    @Test
    @DisplayName("Empty array")
    void empty() {
        src = new Integer[0];
        exp = src;
        HeapSort.heapSort(src);
        Assertions.assertArrayEquals(src, exp);
    }

    @Test
    @DisplayName("Single element array")
    void singleElement() {
        src = new Integer[]{rand.nextInt(Integer.MAX_VALUE)};
        exp = src;
        HeapSort.heapSort(src);
        Assertions.assertArrayEquals(src, exp);
    }

    @Test
    @DisplayName("Array with equal elements")
    void equalElements() {
        src = new Integer[100000];
        Arrays.fill(src, rand.nextInt(Integer.MAX_VALUE));
        exp = src;
        HeapSort.heapSort(src);
        Assertions.assertArrayEquals(src, exp);
    }

    @Test
    @DisplayName("Randomised array")
    void randomised() {
        src = new Integer[100000];
        Arrays.setAll(src, i -> rand.nextInt(Integer.MAX_VALUE));
        exp = src;
        HeapSort.heapSort(src);
        Arrays.sort(exp);
        Assertions.assertArrayEquals(src, exp);
    }

    @Test
    @DisplayName("Sorted array")
    void sorted() {
        src = new Integer[100000];
        Arrays.setAll(src, i -> rand.nextInt(Integer.MAX_VALUE));
        Arrays.sort(src);
        exp = src;
        HeapSort.heapSort(src);
        Assertions.assertArrayEquals(src, exp);
    }

    @Test
    @DisplayName("Backwards sorted array")
    void backwardsSorted() {
        src = new Integer[100000];
        Arrays.setAll(src, i -> rand.nextInt(Integer.MAX_VALUE));
        Arrays.sort(src, Collections.reverseOrder());
        exp = src;
        HeapSort.heapSort(src);
        reverse(exp);
        Assertions.assertArrayEquals(src, exp);
    }

    private static Random rand;
    private static Integer[] src;
    private static Integer[] exp;

    /* reverses the array */
    private static<T> void reverse(T[] arr) {
        for(int i = 0; i < arr.length/2; i++) {
            T tmp = arr[i];
            arr[i] = arr[arr.length-i-1];
            arr[arr.length-i-1] = tmp;
        }
    }
}
