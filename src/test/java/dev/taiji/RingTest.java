package dev.taiji;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RingTest {

    @Test
    public void testClockwise() {
        Ring r31 = new Ring(new int[]{1, 2, 3});
        Ring r32 = new Ring(new int[]{2, 3, 1});
        Ring r33 = new Ring(new int[]{3, 1, 2});
        assertTrue(r32.alignWith(r31));
        assertTrue(r33.alignWith(r31));
        assertEquals(r31, r32);
        assertEquals(r31, r33);
        assertEquals(r32, r33);
        Ring r3p = new Ring(new int[]{1, 3, 2});
        assertNotEquals(r31, r3p);
    }

    @Test
    public void testAdd() {
//        Ring r = new Ring(new int[]{1, 2, 3});
        Ring r = new Ring();
        r.add(-1, 1);
        r.add(-1,2);
        r.add(-1,3);
        r.add(0, 11);
        assertEquals(Arrays.toString(new int[]{1, 11, 2, 3}), r.toString());
        r.add(0, 9);
        r.add(0, 10);
        r.add(1, 99);
        assertEquals(7, r.length);
        assertEquals(Arrays.toString(new int[]{1, 10, 99, 9, 11, 2, 3}), r.toString());
        r.add(6, 88);
        assertEquals(Arrays.toString(new int[]{1, 10, 99, 9, 11, 2, 3, 88}), r.toString());
        r.add(-1, 77);
        assertEquals(Arrays.toString(new int[]{1, 10, 99, 9, 11, 2, 3, 88, 77}), r.toString());
    }
}
