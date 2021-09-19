/**
 * <p>Class for sorting with minheap.</p>
 */
public class HeapSort {
    /**
     * <p>Sorts source array using minheap.</p>
     *
     * @param arr source array
     * @param <T> type of elements, should be <code>Comparable</code>.
     */
    public static<T extends Comparable<T>> void heapSort(T[] arr) {
        MinHeap<T> heap = new MinHeap<>(arr);
        for(int i = 0; i < arr.length; i++) {
            arr[i] = heap.extractMin();
        }
    }
}
