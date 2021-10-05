package io.github.dtolmachev1;

import java.io.Serializable;
import java.lang.Cloneable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.EmptyStackException;

/**
 * <p>Stack <code>class</code>.</p>
 *
 * @param <E> type of stack elements.
 */
public class Stack<E> implements Serializable, Cloneable, RandomAccess, Iterable<E> {
    /**
     * <p>Default constructor for empty stack.</p>
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        this.capacityFactor = 1.5;
        this.elementCount = 0;
        this.elementData = (E[]) new Object[CAPACITY_START_COUNT];
    }

    /**
     * <p>Constructor that creates new stack with a given <code>capacity</code>.</p>
     *
     * @param capacityCount initial capacity of a newly created stack.
     */
    @SuppressWarnings("unchecked")
    public Stack(int capacityCount) {
        this.capacityFactor = 1.5;
        this.elementCount = 0;
        this.elementData = (E[]) new Object[capacityCount];
    }

    /**
     * <p>Constructor that initializes new stack with all elements from the given collection.</p>
     *
     * @param collection which elements will be copied to a new one.
     */
    @SuppressWarnings("unchecked")
    public Stack(Collection<E> collection) {
        this.capacityFactor = 1.5;
        this.elementCount = collection.size();
        this.elementData = (E[]) new Object[(int) capacityFactor*elementCount];
        this.elementData = collection.toArray(this.elementData);
    }

    /**
     * <p>Returns the number of elements in the stack.</p>
     *
     * @return number of the elements in the stack.
     */
    public int count() {
        return size();
    }

    /**
     * <p>Checks if given stack contains no items.</p>
     *
     * @return <code>true</code> if given stack contains no items or <code>false</code> otherwise.
     */
    public boolean empty() {
        return isEmpty();
    }

    /**
     * <p>Pushes an item onto the top of the stack.</p>
     *
     * @param item to be pushed onto the stack.
     * @return pushed item.
     */
    public E push(E item) {
        add(item);
        return peek();
    }

    /**
     * <p>Removes the item at the top of the stack and returns that item.</p>
     *
     * @return the item at the top of the stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public E pop() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return remove(size() - 1);
    }

    /**
     * <p>Returns the item at the top of the stack without removing it.</p>
     *
     * @return the item at the top of the stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public E peek() throws EmptyStackException {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return get(size() - 1);
    }

    /**
     * <p>Pushes all of the items from the specified stack onto the top of this one.</p>
     *
     * @param stack which items to be pushed onto the top of this one.
     * @return <code>true</code> if the stack changed as a result of the call or <code>false</code> otherwise.
     */
    public boolean pushStack(Stack<E> stack) {
        return addAll(stack);
    }

    /**
     * <p>Removes the specified number of items at the top of the stack and returns that items as a new stack.</p>
     *
     * @param count number of items to be removed.
     * @return new stack that contains the specified number of items at the top of this one.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Stack<E> popStack(int count) throws IndexOutOfBoundsException {
        Stack<E> stack = peekStack(count);
        ensure(elementCount - count);
        this.elementCount -= count;
        return stack;
    }

    /**
     * <p>Returns the specified number of items at the top of the stack without removing it.</p>
     *
     * @param count number of items to be returned.
     * @return new stack that contains the specified number of items at the top of this one.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Stack<E> peekStack(int count) throws IndexOutOfBoundsException {
        Objects.checkIndex(elementCount - count, elementCount);
        Stack<E> stack = new Stack<>(count);
        System.arraycopy(elementData, elementCount - count, stack.elementData, 0, count);
        stack.elementCount = count;
        return stack;
    }

    /**
     * <p>Returns position of the first occurrence of the item in the stack (starting from <code>1</code>).</p>
     *
     * @param item to search for.
     * @return position of the first item occurrence in the stack or <code>-1</code> if the stack doesn't contain this item.
     */
    public int search(E item) {
        int index = indexOf(item);
        return index >= 0 ? index + 1 : index;
    }

    /**
     * <p>Creates new Stack with elements from the given one.</p>
     *
     * @return stack with elements copied from the source one.
     */
    @Override
    public Stack<E> clone() {
        try {
            @SuppressWarnings("unchecked") Stack<E> stack = (Stack<E>) super.clone();
            stack.elementData = Arrays.copyOf(elementData, elementCount);
            return stack;
        }
        catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>Creates an <code>iterator</code> over stack elements.</p>
     *
     * @return an <code>iterator</code> over stack elements.
     */
    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    /**
     * <p>Creates a <code>list iterator</code> over stack elements.</p>
     *
     * @return a <code>list iterator</code> over stack elements.
     */
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    /**
     * <p>Creates a <code>list iterator</code> over stack elements starting at the specified position.</p>
     *
     * @return a <code>list iterator</code> over stack elements starting at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public ListIterator<E> listIterator(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, elementCount);
        return new ListIterator<>() {
            /* returns true if the iteration has more elements when traversing the stack in the forward direction */
            @Override
            public boolean hasNext() {
                return cursor < elementCount;
            }

            /* returns the next element in the iteration */
            @Override
            public E next() throws NoSuchElementException {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastRet = cursor++;
                return get(lastRet);
            }

            /* returns the index of the element that would be returned by a subsequent call to next(). (Returns stack size if the list iterator is at the end of the stack) */
            @Override
            public int nextIndex() {
                return cursor;
            }

            /* returns true if the iteration has more elements when traversing the stack in the reverse direction */
            @Override
            public boolean hasPrevious() {
                return cursor > 0;
            }

            /* returns the previous element in the iteration */
            @Override
            public E previous() throws NoSuchElementException {
                if(!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                lastRet = --cursor;
                return get(lastRet);
            }

            /* returns the index of the element that would be returned by a subsequent call to previous(). (Returns -1 if the list iterator is at the beginning of the stack) */
            @Override
            public int previousIndex() {
                return cursor - 1;
            }

            /* replaces the last element returned by next() or previous() with the specified element */
            @Override
            public void set(E element) throws IllegalStateException {
                if(lastRet < 0) {
                    throw new IllegalStateException();
                }
                Stack.this.set(lastRet, element);
            }

            /* inserts the specified element into the stack */
            @Override
            public void add(E element) {
                Stack.this.add(cursor++, element);
                lastRet = -1;
            }

            /* removes from the stack the last element that was returned by next() or previous() */
            @Override
            public void remove() throws IllegalStateException {
                if(lastRet < 0) {
                    throw new IllegalStateException();
                }
                Stack.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            }

            private int cursor = index;  // current position
            int lastRet = -1;  // last returned value
        };
    }

    /**
     * <p>Returns the number of elements in the stack.</p>
     *
     * @return number of the elements in the stack.
     */
    public int size() {
        return elementCount;
    }

    /**
     * <p>Checks if given stack contains no elements.</p>
     *
     * @return <code>true</code> if given stack contains no elements or <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return elementCount == 0;
    }

    /**
     * <p>Creates an array containing all elements from the stack.</p>
     *
     * @return an array containing all elements from this stack.
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, elementCount);
    }

    /**
     * <p>Stores all elements from this stack into the specified array or creates new one if it's length is not enough.</p>
     *
     * @param array for storing elements from the stack.
     * @return specified array or new one if length of specified is not enough.
     * @throws ArrayStoreException if the runtime type of the specified array is not a supertype of the runtime type of every element in this stack.
     * @throws NullPointerException if the specified array is <code>null</code>.
     */
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    public <T> T[] toArray(T[] array) throws ArrayStoreException, NullPointerException {
        if(array.length < elementCount) {
            return (T[]) Arrays.copyOf(elementData, elementCount, array.getClass());
        }
        System.arraycopy(elementData, 0, array, 0, elementCount);
        return array;
    }

    /**
     * <p>Returns the element at the specified position in the stack.</p>
     *
     * @param index of the element to return.
     * @return the element at the specified position in the stack.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public E get(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, elementCount);
        return elementData[index];
    }

    /**
     * <p>Replaces the element at the specified position in the stack with the given element.</p>
     *
     * @param index of the element to replace.
     * @param element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public E set(int index, E element) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, elementCount);
        E previous = elementData[index];
        this.elementData[index] = element;
        return previous;
    }

    /**
     * <p>Checks if stack contains given element.</p>
     *
     * @param object for searching.
     * @return <code>true</code> iff stack contains at least one such element or <code>false</code> otherwise.
     * @throws ClassCastException if the type of the specified element is incompatible with this stack.
     */
    public boolean contains(Object object) throws ClassCastException {
        return indexOf(object) >= 0;
    }

    /**
     * <p>Appends stack with the specified element.</p>
     *
     * @param object to be appended to the stack.
     * @return <code>true</code> if stack changed as a result of the call.
     * @throws ClassCastException if the type of the specified element is incompatible with this stack.
     */
    public boolean add(Object object) throws ClassCastException {
        add(elementCount, object);
        return true;
    }

    /**
     * <p>Inserts specified element at the given position into the stack.</p>
     *
     * @param index at which specified element should be inserted
     * @param object to be inserted to the stack.
     * @throws ClassCastException if the type of the specified element is incompatible with this stack.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @SuppressWarnings("unchecked")
    public void add(int index, Object object) throws ClassCastException, IndexOutOfBoundsException {
        Objects.checkIndex(index, elementCount + 1);
        ensure(elementCount + 1);
        if(index < elementCount) {
            System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
        }
        elementData[index] = (E) object;
        elementCount++;
    }

    /**
     * <p>Removes a single instance of the specified element from the stack (if it's presence).</p>
     *
     * @param object to be removed from the stack.
     * @return <code>true</code> if an element was removed as a result of this call.
     * @throws ClassCastException if the type of the specified element is incompatible with this stack.
     */
    public boolean remove(Object object) throws ClassCastException {
        int index = indexOf(object);
        if(index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * <p>Removes element from the specified position in the stack.</p>
     *
     * @param index at which element should be removed.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        Objects.checkIndex(index, elementCount);
        E previous = elementData[index];
        if(index != elementCount - 1) {
            System.arraycopy(elementData, index + 1, elementData, index, elementCount - index - 1);
        }
        ensure(elementCount - 1);
        elementCount--;
        return previous;
    }

    /**
     * <p>Checks if the stack contains all the elements from the given collection.</p>
     *
     * @param stack which elements will be searched in the stack.
     * @return <code>true</code> if stack contains all the elements from the specified collection or <code>false</code> otherwise.
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this stack.
     */
    public boolean containsAll(Stack<E> stack) throws ClassCastException {
        Object[] array = stack.toArray();
        for(int i = 0; i < array.length; i++) {
            if(!contains(array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Appends the stack with all the elements from the specified collection.</p>
     *
     * @param stack which elements to be added to the stack.
     * @return <code>true</code> if stack changed as a result of the call.
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this stack.
     */
    public boolean addAll(Stack<E> stack) throws ClassCastException {
        return addAll(elementCount, stack);
    }

    /**
     * <p>Inserts all the elements from the specified collection into the stack starting from given position.</p>
     *
     * @param index at which elements from the specified collection should be inserted.
     * @param stack which elements to be inserted to the stack.
     * @return <code>true</code> if stack changed as a result of the call.
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this stack.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @SuppressWarnings("SuspiciousSystemArraycopy")
    public boolean addAll(int index, Stack<E> stack) throws ClassCastException, IndexOutOfBoundsException {
        Objects.checkIndex(index, elementCount + 1);
        if(stack.size() > 0) {
            Object[] array = stack.toArray();
            ensure(elementCount + array.length);
            if(index < elementCount) {
                System.arraycopy(elementData, index, elementData, index + array.length, elementCount - index);
            }
            System.arraycopy(array, 0, elementData, index, array.length);
            elementCount += array.length;
            return true;
        }
        return false;
    }

    /**
     * <p>Removes all the elements from the specified collection that are also contains in the stack.</p>
     *
     * @param stack which elements to be removed from the stack.
     * @return <code>true</code> if stack changed as a result of the call.
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this stack.
     */
    public boolean removeAll(Stack<E> stack) throws ClassCastException {
        boolean flag = false;
        for(int i = 0; i < elementCount; i++) {
            if(stack.contains(elementData[i])) {
                remove(i--);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * <p>Removes from the stack all of its elements that aren't contained in the specified collection.</p>
     *
     * @param stack which elements to be retained in the stack.
     * @return <code>true</code> if stack changed as a result of the call.
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this stack.
     */
    public boolean retainAll(Stack<E> stack) throws ClassCastException {
        boolean flag = false;
        for(int i = 0; i < elementCount; i++) {
            if(!stack.contains(elementData[i])) {
                remove(i--);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * <p>Returns the index of the first occurrence of the specified element in the stack or <code>-1</code> if the stack doesn't contain this element.</p>
     *
     * @param object to search for.
     * @return the index of the first occurrence of the specified element in the stack or <code>-1</code> if the stack doesn't contain this element.
     * @throws ClassCastException if the type of the specified element is incompatible with this stack.
     */
    public int indexOf(Object object) throws ClassCastException {
        for(int i = 0; i < elementCount; i++) {
            if(object.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * <p>Returns the index of the last occurrence of the specified element in the stack or <code>-1</code> if the stack doesn't contain this element.</p>
     *
     * @param object to search for.
     * @return the index of the last occurrence of the specified element in the stack or <code>-1</code> if the stack doesn't contain this element.
     * @throws ClassCastException if the type of the specified element is incompatible with this stack.
     */
    public int lastIndexOf(Object object) throws ClassCastException {
        for(int i = elementCount - 1; i >= 0; i--) {
            if(object.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * <p>Removes all the elements from the stack.</p>
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        this.elementCount = 0;
        this.elementData = (E[]) new Object[CAPACITY_START_COUNT];
    }

    private E[] elementData;  // array with elements
    private int elementCount;  // number of elements
    private final double capacityFactor;  // value that shows ratio of elementCount and elementData.length
    private final int CAPACITY_START_COUNT = 16;  // capacityCount for empty stack

    /* ensures that stack capacity is enough for current elementCount and reallocates it if necessary */
    private boolean ensure(int count) {
        if((elementData.length > 2 * capacityFactor * count) || (elementData.length < count)) {
            this.elementData = Arrays.copyOf(elementData, (int) capacityFactor * count);
            return true;
        }
        return false;
    }
}
