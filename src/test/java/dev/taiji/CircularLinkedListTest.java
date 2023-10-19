package dev.taiji;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CircularLinkedListTest {

    @Test
    void testClockwise() {
        CircularLinkedList<Integer> r31 = new CircularLinkedList<>(new Integer[]{1, 2, 3});
        CircularLinkedList<Integer> r32 = new CircularLinkedList<>(new Integer[]{2, 3, 1});
        CircularLinkedList<Integer> r33 = new CircularLinkedList<>(new Integer[]{3, 1, 2});
        assertEquals(r31, r32);
        assertEquals(r31, r33);
        assertEquals(r32, r33);
        CircularLinkedList<Integer> r3p = new CircularLinkedList<>(new Integer[]{1, 3, 2});
        assertNotEquals(r31, r3p);
    }

    @Test
    void testNormalize() {
        CircularLinkedList<Integer> r31 = new CircularLinkedList<>(new Integer[]{1, 2, 3});
        CircularLinkedList<Integer> r32 = new CircularLinkedList<>(new Integer[]{2, 3, 1});
        CircularLinkedList<Integer> r33 = new CircularLinkedList<>(new Integer[]{3, 1, 2});
        CircularLinkedList<Integer> x32 = r32.normalize();
        assertEquals(r31, x32);
        CircularLinkedList<Integer> x33 = r33.normalize();
        assertEquals(r31, x33);

        CircularLinkedList<Integer> r41 = new CircularLinkedList<>(new Integer[]{1, 2, 3, 4});
        CircularLinkedList<Integer> r42 = new CircularLinkedList<>(new Integer[]{2, 3, 4, 1});
        CircularLinkedList<Integer> r43 = new CircularLinkedList<>(new Integer[]{3, 4, 1, 2});
        CircularLinkedList<Integer> r44 = new CircularLinkedList<>(new Integer[]{4, 1, 2, 3});
        CircularLinkedList<Integer> y42 = r42.normalize();
        assertEquals(r41, y42);
        CircularLinkedList<Integer> y43 = r43.normalize();
        assertEquals(r41, y43);
        CircularLinkedList<Integer> y44 = r44.normalize();
        assertEquals(r41, y44);
    }

    // This test fails because the hashCode() method is not overridden
    @Test
    void testHash() {
        CircularLinkedList<Integer> r31 = new CircularLinkedList<>(new Integer[]{1, 2, 3});
        int h31 = r31.hashCode();
        CircularLinkedList<Integer> r32 = new CircularLinkedList<>(new Integer[]{2, 3, 1});
        int h32 = r32.hashCode();
        CircularLinkedList<Integer> r33 = new CircularLinkedList<>(new Integer[]{3, 1, 2});
        int h33 = r33.hashCode();
        assertEquals(h31, h32);
        assertEquals(h31, h33);
        Set<CircularLinkedList<Integer>> set = new LinkedHashSet<>();
        set.add(r31);
        set.add(r32);
        set.add(r33);
        assertEquals(set.size(), 1);
    }
}
