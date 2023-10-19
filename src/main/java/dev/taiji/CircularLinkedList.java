package dev.taiji;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;


// Elements need to be comparable and unique

public class CircularLinkedList<E extends Comparable<E>> {
    private final LinkedList<E> list = new LinkedList<>();

    public CircularLinkedList() {
    }

    public CircularLinkedList(E[] elements) {
        list.addAll(Arrays.asList(elements));
    }

    public int size() {
        return list.size();
    }

    private int reminder(int pos) {
        int x = pos % this.size();
        if (x < 0) {
            x += this.size();
        }
        return x;
    }

    public void add(int index, E element) {
        if (this.size() == 0) {
            list.add(element);
        } else {
            list.add(reminder(index), element);
        }
    }

    public E remove(int index) {
        if (this.size() == 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
        }
        return list.remove(reminder(index));
    }

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public E get(int index) {
        if (this.size() == 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
        }
        return list.get(reminder(index));
    }

    public void set(int index, E element) {
        if (this.size() == 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
        }
        list.set(reminder(index), element);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CircularLinkedList<?> r)) return false;
        if (r.size() != this.size()) return false;
        if (this.size() == 0) return true;  // empty list

        int index = 0;
        E max = list.get(0);
        for (int i = 1; i < this.size(); i++) {
            if (list.get(i).compareTo(max) > 0) {
                index = i;
                max = list.get(i);
            }
        }
        int index2 = r.indexOf(max);
        if (index2 < 0) return false;
        for (int i = 1; i < this.size(); i++) {
            if (!list.get(reminder(index + i)).equals(r.get(reminder(index2 + i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public int hashCode() {
        if (this.size() <= 1) return Objects.hash(list);
        return Objects.hash(this.normalize().list.toArray());
    }

    public CircularLinkedList<E> normalize() {
        CircularLinkedList<E> newCll = new CircularLinkedList<>();
        if (this.list.isEmpty()) return newCll;
        int index = 0;
        E min = list.get(0);
        for (int i = 1; i < this.size(); i++) {
            if (list.get(i).compareTo(min) < 0) {
                index = i;
                min = list.get(i);
            }
        }
        newCll.add(0, list.get(index));
        for (int i = 1; i < this.size(); i++) {
            newCll.add(i - 1, list.get(reminder(index + i)));
        }
        return newCll;
    }
}
