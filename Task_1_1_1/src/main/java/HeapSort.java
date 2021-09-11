public class HeapSort {
    public static<T extends Comparable<T>> void heapSort(T[] arr) {
        MinHeap<T> heap = new MinHeap<>(arr);
        for(int i = 0; i < arr.length; i++) {
            arr[i] = heap.extractMin();
        }
    }
}
