package io.github.dtolmachev1;

/**
 * <p>Class for sorting with heapsort algorithm.</p>
 */
public class HeapSort {
    /**
     * <p>Sorts source array using heapsort algorithm.</p>
     *
     * @param arr source array
     * @param <T> type of elements, should be <code>Comparable</code>.
     */
    public static<T extends Comparable<T>> void heapSort(T[] arr) {
        /* building minheap from source array
        for(int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
        /* extracting elements from heap */
        for(int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i,  0);
        }
    }

    /* transforms subtree with root in idx into binary heap */
    private static<T extends Comparable<T>> void heapify(T[] arr, int len, int idx) {
        int largest = idx;  // greatest element, initially root
        int left = 2 * idx + 1;  // left child
        int right = 2 * idx + 2;  // right child
        if(left < len && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }
        if(right < len && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }
        if(largest != idx) {
            swap(arr, largest, idx);
            heapify(arr, len, largest);
        }
    }

    /* swaps to elements in the array */
    private static<T> void swap(T[] arr, int idx1, int idx2) {
        T tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
