package io.github.dtolmachev1;

import java.io.Serializable;
import java.lang.Cloneable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Class for representing n-ary tree.</p>
 *
 * @param <E> Type of tree elements.
 */
public class Tree<E extends Comparable<E>> implements Serializable, Cloneable, Iterable<E>, Collection<E> {
    private Tree<E> parent;  // parent
    private int parentIndex;  // index of this node in parent's children list
    private List<Tree<E>> children;  // list of children
    private E key;  // actual data
    private static final int ROOT_PARENT_INDEX = -1;  // parent index for root of the tree

    /**
     * <p>Default constructor to initialize empty tree.</p>
     */
    public Tree() {
        this.parent = this;
        this.parentIndex = ROOT_PARENT_INDEX;
        this.children = new ArrayList<>();
        this.key = null;
    }

    /**
     * <p>Constructor to initialize tree with specified root.</p>
     *
     * @param root Tree root.
     */
    public Tree(E root) {
        this();
        this.key = root;
    }

    /**
     * <p>Constructor to initialize tree with specified root and children.</p>
     *
     * @param root Tree root.
     * @param children Collection with children.
     */
    public Tree(E root, Collection<E> children) {
        this(root);
        this.addAll(children);
    }

    /**
     * <p>Checks if this object is equal to the specified one.</p>
     *
     * @param object A tree instance to compare.
     * @return <code>true</code> if objects are equal or <code>false</code> otherwise.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object object) {
        if(object == this) {
            return true;
        }
        if(!(object.getClass().isInstance(this.getClass()))) {
            return false;
        }
        Tree<E> other = (Tree<E>) object;
        boolean parentEquals = (this.parent == null && other.parent == null)
                || (this.parent != null && this.parent.equals(other.parent));
        boolean parentIndexEquals = this.parentIndex == other.parentIndex;
        boolean childrenEquals = (this.children == null && other.children == null)
                || (this.children != null && this.children.equals(other.children));
        boolean keyEquals = (this.key == null && other.key == null)
                || (this.key != null && this.key.equals(other.key));
        return parentEquals && parentIndexEquals && childrenEquals && keyEquals;
    }

    /**
     * <p>A hash code for this tree.</p>
     *
     * @return A suitable hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    /**
     * <p>Creates a shallow copy of this tree.</p>
     *
     * @return Copy of this tree.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Tree<E> clone() {
        Tree<E> tree = null;  // for cloned object
        try {
            tree = (Tree<E>) super.clone();
            tree.children = new ArrayList<>(this.children.size());
            for(Tree<E> child : this.children) {
                tree.children.add(child.clone());
                tree.children.get(tree.children.size() - 1).parent = this;
            }
            tree.key = this.key;
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return tree;
    }

    /**
     * <p>Creates an <code>iterator</code> over tree elements.</p>
     *
     * @return an <code>iterator</code> over tree elements.
     */
    @Override
    public Iterator<E> iterator() {
        return depthFirstSearchIterator();
    }

    /**
     * <p>Creates an <code>iterator</code> over tree elements using depth-first search.</p>
     *
     * @return an <code>iterator</code> over tree elements.
     */
    public Iterator<E> depthFirstSearchIterator() {
        return new Iterator<>() {
            /* returns true if the iteration has more elements when traversing the tree in the forward direction */
            @Override
            public boolean hasNext() {
                return count < size;
            }

            /* returns the next element in the iteration */
            @Override
            public E next() throws NoSuchElementException {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                if(cursor.parent != cursor && (cursor.parentIndex >= cursor.parent.children.size() || cursor.parent.children.get(cursor.parentIndex) != cursor)) {
                    throw new ConcurrentModificationException();
                }
                count++;
                lastRet = cursor;
                nextCursor();
                return lastRet.key;
            }

            /* removes subtree with last element that was returned by next() or previous() as a root from the tree */
            @Override
            public void remove() throws IllegalStateException {
                if(lastRet == null) {
                    throw new IllegalStateException();
                }
                if(lastRet.parent != lastRet && (lastRet.parentIndex >= lastRet.parent.children.size() || lastRet.parent.children.get(lastRet.parentIndex) != lastRet)) {
                    throw new ConcurrentModificationException();
                }
                size -= lastRet.size();
                lastRet.remove(lastRet.key);
                lastRet = null;
            }

            private Tree<E> cursor = Tree.this;  // current position
            private Tree<E> lastRet = null;  // last returned value
            private int count = 0; // number of visited nodes
            private int size = size();  // number of elements in the iterated tree

            /* moves cursor to the next position */
            private void nextCursor() {
                int index = 0;
                while(cursor.parent != cursor && index >= cursor.children.size()) {
                    index = cursor.parentIndex + 1;
                    cursor = cursor.parent;
                }
                cursor = index < cursor.children.size() ? cursor.children.get(index) : null;
            }
        };
    }

    /**
     * <p>Creates an <code>iterator</code> over tree elements using breadth first search.</p>
     *
     * @return an <code>iterator</code> over tree elements.
     */
    public Iterator<E> breadthFirstSearchIterator() {
        return new Iterator<>() {
        /* returns true if the iteration has more elements when traversing the tree in the forward direction */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        /* returns the next element in the iteration */
        @Override
        public E next() throws NoSuchElementException {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            if(cursor.parent != cursor && (cursor.parentIndex >= cursor.parent.children.size() || cursor.parent.children.get(cursor.parentIndex) != cursor)) {
                throw new ConcurrentModificationException();
            }
            count++;
            lastRet = cursor;
            nextCursor();
            return lastRet.key;
        }

            /* removes subtree with last element that was returned by next() or previous() as a root from the tree */
            @Override
            public void remove() throws IllegalStateException {
                if(lastRet == null) {
                    throw new IllegalStateException();
                }
                if(lastRet.parent != lastRet && (lastRet.parentIndex >= lastRet.parent.children.size() || lastRet.parent.children.get(lastRet.parentIndex) != lastRet)) {
                    throw new ConcurrentModificationException();
                }
                size -= lastRet.size();
                lastRet.remove(lastRet.key);
                lastRet = null;
            }

        private Tree<E> cursor = Tree.this;  // current position
        private Tree<E> lastRet = null;  // last returned value
        private int count = 0; // number of visited nodes
        private int size = size();  // number of elements in the iterated tree

            /* moves cursor to the next position */
        private void nextCursor() {
            int index = cursor.parentIndex + 1;
            if(index >= cursor.parent.children.size()) {
                index = cursor.parent.parentIndex + 1;
                while(index < cursor.parent.parent.children.size() && cursor.parent.parent.children.get(index).children.isEmpty()) {
                    index++;
                }
                if(index < cursor.parent.parent.children.size()) {
                    cursor = cursor.parent.parent.children.get(index).children.get(0);
                }
                else cursor = !cursor.children.isEmpty() ? cursor.children.get(0) : null;
            }
            else cursor = cursor.parent.children.get(index);
        }
    };
    }

    /**
     * <p>Returns number of elements in the tree.</p>
     *
     * @return Number of elements in the tree.
     */
    @Override
    public int size() {
        int size = 0;
        for(Tree<E> child : children) {
            size += child.size();
        }
        return this.key != null ? size + 1 : size;
    }

    /**
     * <p>Checks if given tree contains no elements.</p>
     *
     * @return <code>true</code> if given tree contains no elements or <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return this.key == null && this.children.isEmpty();
    }

    /**
     * <p>Returns an array containing all elements from this tree.</p>
     *
     * @return An array containing all elements from this tree.
     */
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        Iterator<E> iterator = iterator();
        for(int i = 0; iterator.hasNext(); i++) {
            array[i] = iterator.next();
        }
        return array;
    }

    /**
     * <p>Stores all elements from this tree to the specified array or creates new one if it's length is note enough.</p>
     *
     * @param array For storing elements from the tree.
     * @param <T> Runtime type of the specified array.
     * @return Specified array or new one if length of specified is not enough.
     * @throws ArrayStoreException if the runtime type of the specified array is not a supertype of the runtime type of every element in this tree.
     * @throws NullPointerException if the specified array is <code>null</code>.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) throws ArrayStoreException, NullPointerException {
        if(array == null) {
            throw new NullPointerException();
        }
        if(array.length < this.size()) {
            array = (T[]) new Object[this.size()];
        }
        try {
            Iterator<E> iterator = iterator();
            for(int i = 0; iterator.hasNext(); i++) {
                array[i] = (T) iterator.next();
            }
        } catch(ClassCastException e) {
            throw new ArrayStoreException();
        }
        return array;
    }

    /**
     * <p>Returns subtree with ke in the root equal to the specified one.</p>
     *
     * @param element Key for searching.
     * @return Subtree with specified root or <code>null</code> if tree contains no such element.
     */
    public Tree<E> get(E element) {
        if(this.key.equals(element)) {
            return this;
        }
        for(Tree<E> child : this.children) {
            Tree<E> tree = child.get(element);
            if(tree != null) {
                return tree;
            }
        }
        return null;
    }

    /**
     * <p>Returns key of the specified tree.</p>
     *
     * @return Key of the specified tree.
     */
    public E get() {
        return this.key;
    }

    /**
     * <p>Replaces key of the specified element with the given one.</p>
     *
     * @param element To be stored instead of previous key.
     * @return The element previously in this node.
     */
    public E set(E element) {
        E previous = this.key;
        this.key = element;
        return previous;
    }

    /**
     * <p>Checks if tree contains given element.</p>
     *
     * @param object For searching.
     * @return <code>true</code> if tree contains such element or <code>false</code> otherwise.
     * @throws ClassCastException If the type of the specified element is incompatible with elements of this tree.
     */
    @Override
    public boolean contains(Object object) throws ClassCastException {
        if(object.equals(this.key)) {
            return true;
        }
        for(Tree<E> child : children) {
            if(child.contains(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Inserts specified element as a child of this tree.</p>
     *
     * @param element To be inserted to the tree.
     * @return <code>true</code> if tree changed as a result of the call or <code>false</code> otherwise.
     */
    @Override
    public boolean add(E element) {
        this.children.add(new Tree<>(element));
        this.children.get(this.children.size() - 1).parent = this;
        this.children.get(this.children.size() - 1).parentIndex = this.children.size() - 1;
        return true;
    }

    /**
     * <p>Removes subtree with specified element as a root from the tree (if it's presence).</p>
     *
     * @param object Root of subtree to be removed from the tree.
     * @return <code>true</code> if a subtree was removed as a result of this call or <code>false</code> otherwise.
     * @throws ClassCastException If the type of the specified element is incompatible with elements of this tree.
     */
    @Override
    public boolean remove(Object object) throws ClassCastException {
        if(this.key.equals(object)) {
            this.children.clear();
            this.key = null;
            return true;
        }
        for(int i = 0; i < this.children.size(); i++) {
            if(this.children.get(i).remove(object)) {
                if(this.children.get(i).key == null) {
                    this.children.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Checks if the tree contains all the elements from the given collection.</p>
     *
     * @param collection Which elements will be search in the tree.
     * @return <code>true</code> if tree contains all elements from the specified collection or <code>false</code> otherwise.
     * @throws ClassCastException If the types of one or more elements in the specified collection are incompatible with this tree.
     */
    @Override
    public boolean containsAll(Collection<?> collection) throws ClassCastException {
        for(Object object : collection) {
            if(!contains(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if the tree contains any of the elements from the given collection.</p>
     *
     * @param collection Which elements will be search in the tree.
     * @return <code>true</code> if tree contains any of elements from the specified collection or <code>false</code> otherwise.
     * @throws ClassCastException If the types of one or more elements in the specified collection are incompatible with this tree.
     */
    public boolean containsAny(Collection<?> collection) throws ClassCastException {
        for(Object object : collection) {
            if(contains(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Inserts all the elements from the specified collection as children of this tree.</p>
     *
     * @param collection Which elements to be inserted to the tree.
     * @return <code>true</code> if tree changed as a result of the call or <code>false</code> otherwise.
     * @throws ClassCastException If the types of one or more elements in the specified collection are incompatible with this tree.
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) throws ClassCastException {
        int index = this.children.size();
        int i = 0;
        for(E element : collection) {
            this.children.add(new Tree<>(element));
            this.children.get(index + i).parent = this;
            this.children.get(index + i).parentIndex = index + i;
            i++;
        }
        return true;
    }

    /**
     * <p>Removes all the elements from the specified collection that are also contained in the tree.</p>
     *
     * @param collection Which elements to be removed from the tree.
     * @return <code>true</code> if tree changed as a result of the call or <code>false</code> otherwise.
     * @throws ClassCastException If the types of one or more elements in the specified collection are incompatible with this tree.
     */
    @Override
    public boolean removeAll(Collection<?> collection) throws ClassCastException {
        if(collection.contains(this.key)) {
            this.children.clear();
            this.key = null;
            return true;
        }
        boolean flag = false;
        for(int i = 0; i < this.children.size(); i++) {
            if(this.children.get(i).removeAll(collection)) {
                if(this.children.get(i).key == null) {
                    this.children.remove(i--);
                }
                flag = true;
            }
        }
        return flag;
    }

    /**
     * <p>Removes all the elements from the specified collection that aren't contained in the tree.</p>
     *
     * @param collection Which elements to be retained from the tree.
     * @return <code>true</code> if tree changed as a result of the call or <code>false</code> otherwise.
     * @throws ClassCastException If the types of one or more elements in the specified collection are incompatible with this tree.
     */
    @Override
    public boolean retainAll(Collection<?> collection) throws ClassCastException {
        if(!collection.contains(this.key)) {
            this.children.clear();
            this.key = null;
            return true;
        }
        boolean flag = false;
        for(int i = 0; i < this.children.size(); i++) {
            if(this.children.get(i).retainAll(collection)) {
                if(this.children.get(i).key == null) {
                    this.children.remove(i--);
                }
                flag = true;
            }
        }
        return flag;
    }

    /**
     * <p>Removes all the elements from the tree.</p>
     */
    public void clear() {
        this.children.forEach(Tree::clear);
        this.children.clear();
        this.parent = this;
        this.parentIndex = ROOT_PARENT_INDEX;
        this.key = null;
    }
}
