package io.github.dtolmachev1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

class StackTest {
    @BeforeAll
    static void setUp() {
        random = new Random();
    }

    @BeforeEach
    void prepare() {
        empty = new Stack<>();
        preAlloc = new Stack<>(COUNT);
        ArrayList<Integer> arrayList = new ArrayList<>(COUNT);
        for(int i = 0; i < COUNT; i++) {
            arrayList.add(i);
        }
        preInit = new Stack<>(arrayList);
    }

    @Test
    @DisplayName("Count")
    void count_Test() {
        for(int i = 0; i < COUNT; i++) {
            Assertions.assertEquals(empty.count(), i);
            empty.push(i);
            Assertions.assertEquals(empty.count(), i + 1);
        }
        for(int i = COUNT - 1; i >= 0; i--) {
            Assertions.assertEquals(preInit.count(), i + 1);
            preInit.pop();
            Assertions.assertEquals(preInit.count(), i);
        }
    }

    @Test
    @DisplayName("Empty")
    void empty_Test() {
        Assertions.assertTrue(empty.empty());
        Assertions.assertTrue(preAlloc.empty());
        Assertions.assertFalse(preInit.empty());
    }

    @Test
    @DisplayName("Push")
    void push_Test() {
    for(int i = 0; i < COUNT; i++) {
        Assertions.assertEquals(empty.count(), i);
        empty.push(i);
        Assertions.assertEquals(empty.count(), i + 1);
        Assertions.assertEquals(empty.peek(), i);
        }
    }

    @Test
    @DisplayName("Pop")
    void pop_Test() {
        for(int i = COUNT-1; i >= 0; i--) {
            Assertions.assertEquals(preInit.count(), i + 1);
            Assertions.assertEquals(preInit.pop(), i);
            Assertions.assertEquals(preInit.count(), i);
        }
    }

    @Test
    @DisplayName("Peek")
    void peek_Test() {
        for(int i = 0; i < COUNT; i++) {
            empty.push(i);
            Assertions.assertEquals(empty.peek(), i);
        }
        for(int i = COUNT - 1; i >= 0; i--) {
            Assertions.assertEquals(preInit.peek(), i);
            preInit.pop();
        }
    }

    @Test
    @DisplayName("Push stack")
    void pushStack_Test() {
        int count = random.nextInt(COUNT);
        Stack<Integer> stack = new Stack<>(count);
        for(int i = 0; i < count; i++) {
            empty.push(i);
            stack.push(count - i);
        }
        empty.pushStack(stack);
        for(int i = 0; i < count; i++) {
            Assertions.assertEquals(empty.pop(), i + 1);
        }
    }

    @Test
    @DisplayName("Pop stack")
    void popStack_Test() {
        int count = random.nextInt(COUNT);
        Stack<Integer> stack = preInit.popStack(count);
        for(int i = COUNT - 1; i >= COUNT - count; i--) {
            Assertions.assertEquals(stack.pop(), i);
        }
        for(int i = COUNT - count - 1; i >= 0; i--) {
            Assertions.assertEquals(preInit.pop(), i);
        }
    }

    @Test
    @DisplayName("Peek stack")
    void peekStack_Test() {
        int count = random.nextInt(COUNT);
        Stack<Integer> stack = preInit.peekStack(count);
        for(int i = COUNT - 1; i >= COUNT - count; i--) {
            Assertions.assertEquals(stack.pop(), i);
        }
        for(int i = COUNT - 1; i >= 0; i--) {
            Assertions.assertEquals(preInit.pop(), i);
        }
    }

    @Test
    @DisplayName("Search")
    void search_Test() {
        for(int i = 0; i < COUNT; i++) {
            int item = random.nextInt(COUNT);
            Assertions.assertEquals(preInit.search(item), item + 1);
        }
        Assertions.assertEquals(preInit.search(COUNT), -1);
    }

    @Test
    @DisplayName("Clone")
    void clone_Test() {
        Stack<Integer> stack = preInit.clone();
        for(int i = 0; i < COUNT; i++) {
            int index = random.nextInt(COUNT);
            stack.set(index, index + COUNT);
            Assertions.assertNotEquals(preInit.get(index), stack.get(index));
        }
    }

    @Test
    @DisplayName("Iterator")
    void iterator_Test() {
        Iterator<Integer> iterator = preInit.iterator();
        for(int i = 0; iterator.hasNext(); i++) {
            int item = iterator.next();
            Assertions.assertEquals(item, i);
            Assertions.assertTrue(preInit.contains(item));
            iterator.remove();
            Assertions.assertFalse(preInit.contains(item));
        }
    }

    @Test
    @DisplayName("List iterator (from 0-th index)")
    void listIterator_Test() {
        empty.add(0);
        ListIterator<Integer> listIterator = empty.listIterator();
        if(listIterator.hasNext()) {
            listIterator.next();
        }
        for(int i = 1; i < COUNT; i++) {
            Assertions.assertFalse(empty.contains(i));
            listIterator.add(i);
            Assertions.assertTrue(empty.contains(i));
        }
    }

    @Test
    @DisplayName("List iterator (from specified index")
    void testListIterator_WithIndex() {
        ListIterator<Integer> listIterator = preInit.listIterator(preInit.size() - 1);
        while(listIterator.hasPrevious()) {
            int item = listIterator.previous();
            Assertions.assertTrue(preInit.contains(item));
            listIterator.set(item + COUNT);
            Assertions.assertFalse(preInit.contains(item));
        }
    }

    @Test
    @DisplayName("Size")
    void size_Test() {
        for(int i = 0; i < COUNT; i++) {
            Assertions.assertEquals(empty.size(), i);
            empty.push(i);
            Assertions.assertEquals(empty.size(), i + 1);
        }
        for(int i = COUNT - 1; i >= 0; i--) {
            Assertions.assertEquals(preInit.size(), i + 1);
            preInit.pop();
            Assertions.assertEquals(preInit.size(), i);
        }
    }

    @Test
    @DisplayName("Is empty")
    void isEmpty_Test() {
        Assertions.assertTrue(empty.isEmpty());
        Assertions.assertTrue(preAlloc.isEmpty());
        Assertions.assertFalse(preInit.isEmpty());
    }

    @Test
    @DisplayName("To newly created array")
    void toArray_Test() {
        Object[] array = preInit.toArray();
        for(int i = 0; i < preInit.size(); i++) {
            Assertions.assertEquals(preInit.get(i), array[i]);
        }
    }

    @Test
    @DisplayName("To already preexisting array")
    void toArray_WithArray() {
        Integer[] array = new Integer[preInit.size()];
        preInit.toArray(array);
        for(int i = 0; i < preInit.size(); i++) {
            Assertions.assertEquals(preInit.get(i), array[i]);
        }
    }

    @Test
    @DisplayName("Get")
    void get_Test() {
        for(int i = 0; i < COUNT; i++) {
            int item = random.nextInt(COUNT);
            Assertions.assertEquals(preInit.get(item), item);
        }
    }

    @Test
    @DisplayName("Set")
    void set_Test() {
        for(int i = 0; i < COUNT; i++) {
            int item = random.nextInt(COUNT);
            preInit.set(item, item + COUNT);
            Assertions.assertFalse(preInit.contains(item));
        }
    }

    @Test
    @DisplayName("Contains")
    void contains_Test() {
        for(int i = 0; i < COUNT; i++) {
            Assertions.assertFalse(empty.contains(i));
            empty.add(i);
            Assertions.assertTrue(empty.contains(i));
        }
    }

    @Test
    @DisplayName("Append")
    void add_Append() {
        for(int i = 0; i < COUNT; i++) {
            empty.add(i);
            Assertions.assertEquals(empty.get(empty.size() - 1), i);
        }
    }

    @Test
    @DisplayName("Add (to the specified index)")
    void add_WithIndex() {
        int count = random.nextInt(COUNT);
        for(int i = 0; i < count; i++) {
            empty.add(i);
        }
        for(int i = 0; i < COUNT - count; i++) {
            int index = random.nextInt(empty.size());
            empty.add(index, i);
            Assertions.assertEquals(empty.get(index), i);
        }
    }

    @Test
    @DisplayName("Remove (first occurrence)")
    void remove_FirstOccurrence() {
        for(int i = COUNT - 1; i >= 0; i--) {
            Assertions.assertTrue(preInit.contains(i));
            preInit.remove(Integer.valueOf(i));
            Assertions.assertFalse(preInit.contains(i));
        }
    }

    @Test
    @DisplayName("Remove (by index)")
    void remove_ByIndex() {
        for(int i = 0; i < COUNT; i++) {
            int index = random.nextInt(preInit.size());
            int item = preInit.get(index);
            Assertions.assertTrue(preInit.contains(item));
            preInit.remove(index);
            Assertions.assertFalse(preInit.contains(item));
        }
    }

    @Test
    @DisplayName("Contains all")
    void containsAll_Test() {
        int count = random.nextInt(COUNT);
        Stack<Integer> stack = new Stack<>(count);
        for(int i = 0; i < count; i++) {
            stack.add(random.nextInt(COUNT));
        }
        Assertions.assertTrue(preInit.containsAll(stack));
        stack.add(COUNT);
        Assertions.assertFalse(preInit.containsAll(stack));
    }

    @Test
    @DisplayName("Append all")
    void addAll_Append() {
        int count = random.nextInt(COUNT);
        Stack<Integer> stack = new Stack<>(count);
        for(int i = 0; i < count; i++) {
            stack.add(random.nextInt(COUNT));
        }
        empty.addAll(stack);
        for(int i = 0; i < count; i++) {
            Assertions.assertEquals(stack.get(i), empty.get(i));
        }
    }

    @Test
    @DisplayName("Add all (to the specified index)")
    void addAll_WithIndex() {
        int count = random.nextInt(COUNT);
        for(int i = 0; i < COUNT; i++) {
            empty.add(i);
        }
        Stack<Integer> stack = new Stack<>(count);
        for(int i = 0; i < count; i++) {
            stack.add(random.nextInt(COUNT));
        }
        int index = random.nextInt(count);
        empty.addAll(index, stack);
        for(int i = 0; i < count; i++) {
            Assertions.assertEquals(stack.get(i), empty.get(index + i));
        }
    }

    @Test
    @DisplayName("Remove all")
    void removeAll_Test() {
        int count = random.nextInt(COUNT);
        Stack<Integer> stack = new Stack<>(count);
        for(int i = 0; i < COUNT; i++) {
            stack.add(random.nextInt(COUNT));
        }
        preInit.removeAll(stack);
        for(int i = 0; i < count; i++) {
            Assertions.assertFalse(preInit.contains(stack.get(i)));
        }
    }

    @Test
    @DisplayName("Retain all")
    void retainAll_Test() {
        int count = random.nextInt(COUNT);
        Stack<Integer> stack = new Stack<>(count);
        boolean[] flags = new boolean[COUNT];
        for(int i = 0; i < count; i++) {
            int index = random.nextInt(COUNT);
            stack.add(index);
            flags[index] = true;
        }
        preInit.retainAll(stack);
        for(int i = 0; i < COUNT; i++) {
            Assertions.assertEquals(preInit.contains(i), flags[i]);
        }
    }

    @Test
    @DisplayName("Index of")
    void indexOf_Test() {
        for(int i = 0; i < COUNT; i++) {
            int item = random.nextInt(COUNT);
            Assertions.assertEquals(preInit.indexOf(item), item);
        }
    }

    @Test
    void lastIndexOf() {
        int count = random.nextInt(COUNT);
        for(int i = 0; i < count; i++) {
            empty.add(i);
        }
        for(int i = 0; i < count; i++) {
            empty.add(i);
            Assertions.assertEquals(empty.lastIndexOf(i), count + i);
            Assertions.assertNotEquals(empty.indexOf(i), empty.lastIndexOf(i));
        }
    }

    @Test
    @DisplayName("Clear")
    void clear_Test() {
        Assertions.assertFalse(preInit.isEmpty());
        preInit.clear();
        Assertions.assertTrue(preInit.isEmpty());
    }

    private static Random random;
    private static Stack<Integer> empty;
    private static Stack<Integer> preAlloc;
    private static Stack<Integer> preInit;
    private static final int COUNT = 1000;
}