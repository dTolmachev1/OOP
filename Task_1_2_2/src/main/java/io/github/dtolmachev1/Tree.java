package io.github.dtolmachev1;

import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
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
            }

            /* returns the next element in the iteration */
            @Override
            public E next() throws NoSuchElementException {
            }

            /* removes subtree with  last element that was returned by next() or previous() as a root from the tree */
            @Override
            public void remove() throws IllegalStateException {
            }

            private Tree<E> cursor = Tree.this;  // current position
            private Tree<E> lastRet = null;  // last returned value

            private nextCursor() {
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
            }

            /* returns the next element in the iteration */
            @Override
            public E next() throws NoSuchElementException {
            }

            /* removes subtree with  last element that was returned by next() or previous() as a root from the tree */
            @Override
            public void remove() throws IllegalStateException {
            }

            private int cursor = index;  // current position
            private int lastRet = -1;  // last returned value

            private nextCursor() {
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
        Iterator<E> iterator = iterator();
        int i = 0;
        while(iterator.hasNext()) {
            iterator.next();
            i++;
        }
        return i;
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
        if(!(object.getClass().isInstance(((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]))) {
            throw new ClassCastException();
        }
        if(this.key.equals(object)) {
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
            if(this.parent != this) {
                this.parent.children.remove(this.parentIndex);
            }
            this.children.clear();
            return true;
        }
        for(Tree<E> child : this.children) {
            if(child.remove(object)) {
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
        int index = this.size();
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
            if(this.parent != this) {
                this.parent.children.remove(this.parentIndex);
            }
            this.children.clear();
            return true;
        }
        boolean flag = false;
        for(Tree<E> child : this.children) {
            if(child.removeAll(collection)) {
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
            if(this.parent != this) {
                this.parent.children.remove(this.parentIndex);
            }
            this.children.clear();
            return true;
        }
        boolean flag = false;
        for(Tree<E> child : this.children) {
            if(child.retainAll(collection)) {
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

    private Tree<E> parent;  // parent
    private int parentIndex;  // index of this node in parent's children list
    private List<Tree<E>> children;  // list of children
    private E key;  // actual data
    private static final int ROOT_PARENT_INDEX = -1;  // parent index for root of the tree
}
