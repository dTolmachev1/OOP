import java.util.Arrays;

/**
 * <p>Minheap data structure.</p>
 *
 * @param <T> type of elements, should be <code>Comparable</code>.
 */
public class MinHeap<T extends Comparable<T>> {
    /**
     * <p>Initialises heap with a given capacity.</p>
     *
     * @param capacity for initial capacity (maybe increased later).
     */
    public MinHeap(int capacity) {
        this.values = (T[]) new Comparable[capacity];
        this.heap2idx = new int[capacity];
        this.idx2heap = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    /**
     * <p>Initialises heap from a given array.</p>
     *
     * @param arr array, which elements will be stored into heap.
     */
    public MinHeap(T[] arr) {
        this.values = (T[]) new Comparable[3*arr.length/2];
        this.heap2idx = new int[3*arr.length/2];
        this.idx2heap = new int[3*arr.length/2];
        this.size = 0;
        this.capacity = 3*arr.length/2;
        for(int i = 0; i < arr.length; i++) {
            add(arr[i], i);
        }
    }

    /**
     * <p>Checks if heap is empty.</p>
     *
     * @return <code>True</code> if heap is empty and <code>False</code> otherwise.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * <p>Checks if heap contains given element.</p>
     *
     * @param value desired element.
     * @return <code>True</code> if heap contains given element and <code>False</code> otherwise.
     */
    public boolean contains(T value) {
        for(int i = 0; i < this.size; i++) {
            if(value.compareTo(this.values[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Returns heap size.</p>
     *
     * @return current size of a heap
     */
    public int size() {
        return this.size;
    }

    /**
     * <p>Adds new element to heap.</p>
     *
     * @param value element which should be added.
     * @param idx index of its element in the source array.
     */
    public void add(T value, int idx) {
        this.size++;
        if(this.size > this.capacity) {
            this.capacity = 3*this.capacity/2;
            this.values = Arrays.copyOf(this.values, this.capacity);
            this.heap2idx = Arrays.copyOf(this.heap2idx, this.capacity);
            this.idx2heap = Arrays.copyOf(this.idx2heap, this.capacity);
        }
        this.values[this.size-1] = value;
        this.heap2idx[this.size-1] = idx;
        this.idx2heap[idx] = this.size-1;
        siftUp(this.size-1);
    }

    /**
     * <p>Adds new element to heap.</p>
     *
     * @param value element which should be added.
     */
    public void add(T value) {
        this.size++;
        if(this.size > this.capacity) {
            this.capacity = 3*this.capacity/2;
            this.values = Arrays.copyOf(this.values, this.capacity);
            this.heap2idx = Arrays.copyOf(this.heap2idx, this.capacity);
            this.idx2heap = Arrays.copyOf(this.idx2heap, this.capacity);
        }
        this.values[this.size-1] = value;
        this.heap2idx[this.size-1] = this.size-1;
        this.idx2heap[this.size-1] = this.size-1;
        siftUp(this.size-1);
    }

    /**
     * <p>Returns minimal element from heap.</p>
     *
     * @return element from the top of the heap (should be minimal element, follows from the heap invariant).
     */
    public T getMin() {
        return this.values[0];
    }

    /**
     * <p>Extracts minimal element from heap.</p>
     *
     * @return element from the top of the heap (should be minimal element, follows from the heap invariant).
     */
    public T extractMin() {
        T value = this.values[0];
        swap(0, this.size-1);
        this.size--;
        siftDown(0);
        return value;
    }

    /**
     * <p>Deletes element from heap by its index in the source array.</p>
     *
     * @param idx index of its element in the source array.
     * @return deleted element.
     */
    public T delete(int idx) {
        T value = this.values[this.idx2heap[idx]];
        swap(this.idx2heap[idx], this.size-1);
        this.size--;
        siftDown(this.idx2heap[idx]);
        return value;
    }

    private T[] values;
    private int[] heap2idx;
    private int[] idx2heap;
    private int size;
    private int capacity;

    /* returns an index of the parent node or -1 if it doesn't exist */
    private int getParentIdx(int idx) {
        return (idx-1)/2 < this.size ? (idx-1)/2 : -1;
    }

    /* returns an index of the left node or -1 if it doesn't exist */
    private int getLeftIdx(int idx) {
        return 2*idx+1 < this.size ? 2*idx+1 : -1;
    }

    /* returns an index of the right node or -1 if it doesn't exist */
    private int getRightIdx(int idx) {
        return 2*idx+2 < this.size ? 2*idx+2 : -1;
    }

    /* swaps two elements in the heap */
    private void swap(int idx1, int idx2) {
        T val = this.values[idx1];
        this.values[idx1] = this.values[idx2];
        this.values[idx2] = val;
        int idx = this.heap2idx[idx1];
        this.heap2idx[idx1] = this.heap2idx[idx2];
        this.heap2idx[idx2] = idx;
        idx = this.idx2heap[this.heap2idx[idx1]];
        this.idx2heap[this.heap2idx[idx1]] = this.idx2heap[this.heap2idx[idx2]];
        this.idx2heap[this.heap2idx[idx2]] = idx;
    }

    /* restores heap-invariant */
    private void siftUp(int idx) {
        if(getParentIdx(idx) != -1 && this.values[idx].compareTo(this.values[getParentIdx(idx)]) < 0) {
            swap(idx, getParentIdx(idx));
            siftUp(getParentIdx(idx));
        }
    }

    /* restores heap-invariant */
    private void siftDown(int idx) {
        int minChild = -1;
        if (getLeftIdx(idx) != -1 && getRightIdx(idx) == -1) {
            minChild = getLeftIdx(idx);
        } else if (getLeftIdx(idx) == -1 && getRightIdx(idx) != -1) {
            minChild = getRightIdx(idx);
        } else if (getLeftIdx(idx) != -1 && getRightIdx(idx) != -1) {
            minChild = this.values[getLeftIdx(idx)].compareTo(this.values[getRightIdx(idx)]) < 0 ? getLeftIdx(idx) : getRightIdx(idx);
        }
        if (minChild != -1 && this.values[idx].compareTo(this.values[minChild]) > 0) {
            swap(idx, minChild);
            siftDown(minChild);
        }
    }
}
