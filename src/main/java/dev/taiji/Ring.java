package dev.taiji;

import com.google.common.primitives.Ints;

import java.util.Arrays;

public class Ring {
    public int length;
    public int[] array;

    public Ring() {
        this.length = 0;
        this.array = new int[0];
    }

    public Ring(int length) {
        this.length = length;
        this.array = new int[length];
    }

    public Ring(int[] elements) {
        this.length = elements.length;
        this.array = new int[length];
        System.arraycopy(elements, 0, array, 0, length);
    }

    public Ring set(int index, int value) {
        array[reminder(index)] = value;
        return this;
    }

    public int get(int index) {
        return array[reminder(index)];
    }

    public Ring add(int index, int value) {
        if (length == 0) {
            array = new int[1];
            array[0] = value;
            length = 1;
            return this;
        }
        int pos = reminder(index) + 1;
        int[] newArray = new int[length + 1];
        System.arraycopy(array, 0, newArray, 0, pos);
        newArray[pos] = value;
        System.arraycopy(array, pos, newArray, pos + 1, length - pos);
        array = newArray;
        length++;
        return this;
    }

    public Ring remove(int index) {
        int pos = reminder(index);
        int[] newArray = new int[length - 1];
        System.arraycopy(array, 0, newArray, 0, pos);
        System.arraycopy(array, pos + 1, newArray, pos, length - pos - 1);
        array = newArray;
        length--;
        return this;
    }

    public Ring header(int index) {
        int pos = reminder(index);
        int[] newArray = new int[length];
        System.arraycopy(array, pos, newArray, 0, length - pos);
        System.arraycopy(array, 0, newArray, length - pos, pos);
        array = newArray;
        return this;
    }

    public int indexOf(int value) {
        return Ints.indexOf(array, value);
    }

    public boolean alignWith(Ring r) {
        if (r.length != length) return false;
        if (length == 0) return true;
        int val = r.array[0];
        int index2 = Ints.indexOf(array, val);
        if (index2 < 0) return false;
        this.header(index2);
        return true;
    }

    public Ring copy() {
        Ring r = new Ring(length);
        System.arraycopy(array, 0, r.array, 0, length);
        return r;
    }

    public boolean deepEquals(Ring r) {
        if (r.length != length) return false;
        if (length == 0) return true;  // empty list
        if (this.alignWith(r)) {
            return Arrays.equals(array, r.array);
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ring r)) return false;
        if (r.length != length) return false;
        if (length == 0) return true;  // empty list
        return Arrays.equals(array, r.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
    @Override
    public String toString() {
        return Arrays.toString(array);
    }


    private int reminder(int pos) {
        if (this.length == 0) {
            return 0;
        }
        int x = pos % this.length;
        if (x < 0) {
            x += this.length;
        }
        return x;
    }

}
