package io.github.dtolmachev1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TreeTest {
    @BeforeAll
    static void setUp() {
        random = new Random();
    }

    @BeforeEach
    void prepare() {
        List<Integer> arrayList = new ArrayList<>(IntStream.rangeClosed(0, SIZE - 1).boxed().toList());
        preInit = new Tree<>(-1, arrayList);
    }

    @Test
    @DisplayName("Clone")
    void clone_Test() {
        Tree<Integer> tree = preInit.clone();
        for(int i = 0; i < SIZE; i++) {
            tree.get(i).add(random.nextInt());
        }
        Assertions.assertNotEquals(tree.size(), preInit.size());
    }

    @Test
    @DisplayName("Iterator")
    void iterator_Test() {
        Iterator<Integer> iterator = preInit.iterator();
        for(int i = 0; iterator.hasNext(); i++) {
            int item = iterator.next();
            Assertions.assertEquals(item, i - 1);
            Assertions.assertTrue(preInit.contains(item));
            iterator.remove();
            Assertions.assertFalse(preInit.contains(item));
        }
    }

    @Test
    @DisplayName("Depth-first search iterator")
    void depthFirstSearchIterator_Test() {
        empty = new Tree<>(0, Arrays.asList(1, 4));
        empty.get(1).addAll(Arrays.asList(2, 3));
        empty.get(4).addAll(Arrays.asList(5, 6));
        int[] src = new int[]{0, 1, 2, 3, 4, 5, 6};
        int[] exp = new int[7];
        Iterator<Integer> iterator = empty.depthFirstSearchIterator();
        for(int i = 0; iterator.hasNext(); i++) {
            exp[i] = iterator.next();
        }
        Assertions.assertArrayEquals(exp, src);
    }

    @Test
    @DisplayName("Breadth first search iterator")
    void breadthFirstSearchIterator_Test() {
        empty = new Tree<>(0, Arrays.asList(1, 2));
        empty.get(1).addAll(Arrays.asList(3, 4));
        empty.get(2).addAll(Arrays.asList(5, 6));
        int[] src = new int[]{0, 1, 2, 3, 4, 5, 6};
        int[] exp = new int[7];
        Iterator<Integer> iterator = empty.breadthFirstSearchIterator();
        for(int i = 0; iterator.hasNext(); i++) {
            exp[i] = iterator.next();
        }
        Assertions.assertArrayEquals(exp, src);
    }

    @Test
    @DisplayName("Size")
    void size_Test() {
        empty = new Tree<>(-1);
        for(int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(empty.size(), i + 1);
            empty.add(i);
            Assertions.assertEquals(empty.size(), i + 2);
        }
        for(int i = SIZE - 1; i >= 0; i--) {
            Assertions.assertEquals(preInit.size(), i + 2);
            preInit.remove(i);
            Assertions.assertEquals(preInit.size(), i + 1);
        }
    }

    @Test
    @DisplayName("Is empty")
    @SuppressWarnings("ConstantConditions")
    void isEmpty_Test() {
        empty = new Tree<>();
        Assertions.assertTrue(empty.isEmpty());
        Assertions.assertFalse(preInit.isEmpty());
    }

    @Test
    @DisplayName("To newly created array")
    @SuppressWarnings("SuspiciousMethodCalls")
    void toArray_Test() {
        Object[] array = preInit.toArray();
        Assertions.assertEquals(array.length, preInit.size());
        for(Object object : array) {
            Assertions.assertTrue(preInit.contains(object));
        }
    }

    @Test
    @DisplayName("To already preexisting array")
    void toArray_WithArray() {
        Integer[] array = new Integer[preInit.size()];
        preInit.toArray(array);
        Assertions.assertEquals(array.length, preInit.size());
        for(Integer element : array) {
            Assertions.assertTrue(preInit.contains(element));
        }
    }

    @Test
    @DisplayName("Get")
    void get_Test() {
        for(int i = 0; i < SIZE; i++) {
            int item = random.nextInt(SIZE);
            Assertions.assertEquals(preInit.get(item).get(), item);
        }
    }

    @Test
    @DisplayName("Set")
    void set_Test() {
        for(int i = 0; i < SIZE; i++) {
            int item = random.nextInt(SIZE);
            preInit.get(item).set(item + SIZE);
            Assertions.assertFalse(preInit.contains(item));
            preInit.get(item + SIZE).set(item);
        }
    }

    @Test
    @DisplayName("Contains")
    void contains_Test() {
        empty = new Tree<>(-1);
        for(int i = 0; i < SIZE; i++) {
            Assertions.assertFalse(empty.contains(i));
            empty.add(i);
            Assertions.assertTrue(empty.contains(i));
        }
    }

    @Test
    @DisplayName("Add")
    void add_Test() {
        empty = new Tree<>(-1);
        for(int i = 0; i < SIZE; i++) {
            empty.add(i);
            Assertions.assertEquals(empty.get(i).get(), i);
        }
    }

    @Test
    @DisplayName("Remove")
    void remove_Test() {
        for(int i = SIZE - 1; i >= 0; i--) {
            Assertions.assertTrue(preInit.contains(i));
            preInit.remove(i);
            Assertions.assertFalse(preInit.contains(i));
        }
    }

    @Test
    @DisplayName("Contains all")
    void containsAll_Test() {
        int size = random.nextInt(SIZE);
        List<Integer> arrayList = random.ints(0, SIZE).limit(size).boxed().collect(Collectors.toList());
        Assertions.assertTrue(preInit.containsAll(arrayList));
        arrayList.add(SIZE);
        Assertions.assertFalse(preInit.containsAll(arrayList));
    }

    @Test
    @DisplayName("Contains any")
    void containsAny_Test() {
        int size = random.nextInt(SIZE);
        List<Integer> arrayList = random.ints(0, SIZE).limit(size).boxed().collect(Collectors.toList());
        arrayList.add(SIZE);
        Assertions.assertTrue(preInit.containsAny(arrayList));
        arrayList.retainAll(List.of(arrayList.get(arrayList.size() - 1)));
        Assertions.assertFalse(preInit.containsAny(arrayList));
    }

    @Test
    @DisplayName("Add all")
    void addAll_Test() {
        int size = random.nextInt(SIZE);
        List<Integer> arrayList = random.ints(0, SIZE).limit(size).boxed().toList();
        empty = new Tree<>(-1);
        empty.addAll(arrayList);
        Assertions.assertEquals(empty.size(), size + 1);
        for(int i = 0; i < size; i++) {
            Assertions.assertTrue(empty.contains(arrayList.get(i)));
        }
    }

    @Test
    @DisplayName("Remove all")
    void removeAll_Test() {
        int size = random.nextInt(SIZE);
        List<Integer> arrayList = random.ints(0, SIZE).limit(size).boxed().toList();
        preInit.removeAll(arrayList);
        for(int i = 0; i < size; i++) {
            Assertions.assertFalse(preInit.contains(arrayList.get(i)));
        }
    }

    @Test
    @DisplayName("Retain all")
    void retainAll_Test() {
        int size = random.nextInt(SIZE);
        List<Integer> arrayList = random.ints(0, SIZE).limit(size).boxed().collect(Collectors.toList());
        arrayList.add(-1);
        boolean[] flags = new boolean[SIZE];
        arrayList.subList(0, arrayList.size() - 1).forEach(item -> flags[item] = true);
        preInit.retainAll(arrayList);
        for(int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(preInit.contains(i), flags[i]);
        }
    }

    @Test
    @DisplayName("Clear")
    @SuppressWarnings("ConstantConditions")
    void clear_Test() {
        Assertions.assertFalse(preInit.isEmpty());
        preInit.clear();
        Assertions.assertTrue(preInit.isEmpty());
    }

    private static Random random;
    private static Tree<Integer> empty;
    private static Tree<Integer> preInit;
    private static final int SIZE = 1000;
}
