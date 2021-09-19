import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

class MinHeapTest {
    @BeforeAll
    static void setUp() {
        rand = new Random();
        arr = new Integer[10000];
        Arrays.setAll(arr, i -> rand.nextInt(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("isEmpty()")
    void isEmpty() {
    heap = new MinHeap<>(1);
    Assertions.assertTrue(heap.isEmpty());
    heap.add(arr[0], 0);
    Assertions.assertFalse(heap.isEmpty());
    heap.extractMin();
    Assertions.assertTrue(heap.isEmpty());
    }

    @Test
    @DisplayName("contains()")
    void contains() {
        heap = new MinHeap<>(10000);
        for(int i = 0; i < heap.size(); i++) {
            Assertions.assertFalse(heap.contains(i));
            heap.add(i);
            Assertions.assertTrue(heap.contains(i));
        }
    }

    @Test
    @DisplayName("size()")
    void size() {
        heap = new MinHeap<>(arr);
        for(int i = arr.length; i > 0; i--) {
            Assertions.assertEquals(heap.size(), i);
            heap.extractMin();
            Assertions.assertEquals(heap.size(), i-1);
        }
    }

    @Test
    @DisplayName("add() (with indices")
    void addWithIndices() {
        heap = new MinHeap<>(arr.length/2);
        for(int i = 0; i < arr.length; i++) {
            heap.add(arr[i], i);
            Assertions.assertTrue(heap.contains(arr[i]));
        }
    }

    @Test
    @DisplayName("add() (without indices")
    void addWithoutIndices() {
        heap = new MinHeap<>(arr.length/2);
        for(Integer val : arr) {
            heap.add(val);
            Assertions.assertTrue(heap.contains(val));
        }
    }

    @Test
    @DisplayName("getMin()")
    void getMin() {
        heap = new MinHeap<>(arr);
        Arrays.sort(arr);
        Assertions.assertEquals(heap.getMin(), arr[0]);
    }

    @Test
    @DisplayName("extractMin()")
    void extractMin() {
        heap = new MinHeap<>(arr);
        Arrays.sort(arr);
        for(Integer val : arr) {
            Assertions.assertEquals(heap.extractMin(), val);
        }
    }

    @Test
    @DisplayName("delete()")
    void delete() {
        heap = new MinHeap<>(arr);
        for(int i = 0; i < arr.length; i++) {
            heap.delete(i);
            Assertions.assertEquals(heap.size(), arr.length-i-1);
        }
    }

    private static Random rand;
    private static MinHeap<Integer> heap;
    private static Integer[] arr;
}
