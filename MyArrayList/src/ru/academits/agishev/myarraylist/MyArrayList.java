package ru.academits.agishev.myarraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int length;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IndexOutOfBoundsException("capacity must be >0");
        }

        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int size) {
        if (items.length < size) {
            items = Arrays.copyOf(items, size);
        }
    }

    public void trimToSize() {
        if (items.length > length) {
            items = Arrays.copyOf(items, length);
        }
    }

    @Override // Реализовал
    public int size() {
        return length;
    }

    @Override // Реализовал
    public boolean isEmpty() {
        return length == 0;
    }

    @Override // Реализовал
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = 0;
        private int state = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("end of collection");
            }

            if (state != modCount) {
                throw new ConcurrentModificationException("collection was changed");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override // Реализовал
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override // Реализовал
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override // Реализовал
    public <T1> T1[] toArray(T1[] a) {
        if (a.length <= items.length) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, a.length);
        }
        System.arraycopy(items, 0, a, 0, items.length); //не могу исправить ворнинг - как здесь следует написать код?

        return a;
    }

    @Override // Реализовал
    public boolean add(T t) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = t;
        ++length;
        modCount++;

        return true;
    }

    @Override // Реализовал
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > -1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
            --length;
            modCount++;
            return true;
        }

        return false;
    }

    @Override // Реализовал
    public boolean containsAll(Collection<?> c) {
        boolean check = false;
        for (Object elementOfC : c) {
            if (contains(elementOfC)) {
                check = true;
                break;
            }
        }

        return check;
    }

    @Override // Реализовал
    public boolean addAll(Collection<? extends T> c) {
        return addAll(length, c);
    }

    @Override // Реализовал
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean check = false;
        int cSize = c.size();

        while (length + cSize >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length + cSize - index - 1);

        for (T element : c) {
            items[index] = element;
            length++;
            index++;
            check = true;
        }

        if (check) {
            modCount++;
        }

        return check;
    }

    @Override // Реализовал
    public boolean removeAll(Collection<?> c) {
        boolean check = false;
        for (Object elementOfC : c) {
            if (contains(elementOfC)) {
                check = remove(elementOfC);
            }
        }
        if (check) {
            modCount++;
        }

        return check;
    }

    @Override // Реализовал
    public boolean retainAll(Collection<?> c) {
        boolean check = false;
        for (T elementOfT : items) {
            if (c.contains(elementOfT)) {
                check = remove(elementOfT);
            }
        }

        if (check) {
            modCount++;
        }

        return check;
    }

    @Override // Реализовал
    public void clear() {
        //noinspection unchecked
        items = (T[]) new Object[10];
        length = 0;
        modCount++;
    }

    @Override // Реализовал
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index must be >=0");
        }

        if (index >= length) {
            throw new IndexOutOfBoundsException("index must be <= " + (length - 1));
        }

        return items[index];
    }

    @Override // Реализовал
    public T set(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index must be >=0");
        }

        if (index >= length) {
            throw new IndexOutOfBoundsException("index must be <= " + (length - 1));
        }

        T lastElement = items[index];
        items[index] = element;

        return lastElement;
    }

    @Override // Реализовал
    public void add(int index, T element) {
        if (index > length) {
            throw new IndexOutOfBoundsException("index must be <= " + length);
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("index must be >= 0");
        }

        if (index == length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = element;
        ++length;
        modCount++;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override // Реализовал
    public T remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index must be >=0");
        }

        if (index >= length) {
            throw new IndexOutOfBoundsException("index must be <= " + (length - 1));
        }

        T lastElement = items[index];
        System.arraycopy(items, index + 1, items, index, length - index - 1);
        --length;
        modCount++;

        return lastElement;
    }

    @Override // Реализовал
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override // Реализовал
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i > 0; i--) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override //не реализовываем
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override //не реализовываем
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override //не реализовываем
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
