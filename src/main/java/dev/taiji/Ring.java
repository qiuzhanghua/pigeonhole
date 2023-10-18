package dev.taiji;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import com.google.common.primitives.Ints;
public class Ring {
    public int length;
    public int[] array;

    public static Ring init(int length) {
        assert length > 0;
        Ring r = new Ring();
        r.length = length;
        r.array = new int[length];
        return r;
    }

    public void setValue(int index, int value) {
        array[index % length] = value;
    }

    public void insert(int index, int value) {
        index = index % length;
        int[] newArray = new int[length + 1];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, length - index);
        array = newArray;
        length++;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ring r)) return false;
        if (r.length != length) return false;
        int index = 0; int max = array[0];
        for (int i = 1; i < length; i++) {
            if (array[i] > max) {
                index = i;
                max = array[i];
            }
        }
        int index2 = Ints.indexOf(r.array, max);
        if (index2 < 0) return false;
        if (array[(index + 1) % length] == r.array[(index2 + 1) % length]) {
            for (int i = 2; i < length; i++) {
                if (array[(index + i) % length] != r.array[(index2 + i) % length]) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < length; i++) {
                if (array[(index - i) % length] != r.array[(index2 - i) % length]) {
                    return false;
                }
            }
        }
        return true;
    }
}
