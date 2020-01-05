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

    public MyArrayList(int numberOfItems) {
        //noinspection unchecked
        items = (T[]) new Object[numberOfItems];
    }

    public void ensureCapacity(int size) {
        if (items.length <= size) {
            items = Arrays.copyOf(items, size);
        }
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, length);
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
        for (T element : items) {
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private int condition = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < length;
        }

        @Override
        public T next() {
            ++currentIndex;
            if (currentIndex >= length) {
                throw new NoSuchElementException("end of collection");
            }
            if (condition != modCount) {
                throw new ConcurrentModificationException("collection be chang");
            }
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
        //noinspection unchecked
        return (T1[]) Arrays.copyOf(items, length);
    }

    @Override // Реализовал
    public boolean add(T t) {
        if (length < items.length) {
            items[length] = t;
            ++length;
            modCount++;
            return true;
        }
        increaseCapacity();
        items[length] = t;
        ++length;
        modCount++;
        return true;
    }

    @Override // Реализовал
    public boolean remove(Object o) {
        for (int i = length - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
                java.lang.System.arraycopy(items, i + 1, items, i, length - i - 1);
                --length;
            }
        }
        modCount++;
        return true;
    }

    @Override // Реализовал
    public boolean containsAll(Collection<?> c) {
        for (Object elementOfC : c) {
            boolean check = false;
            for (T elementOfT : items) {
                if (elementOfC.equals(elementOfT)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                return false;
            }
        }
        return true;
    }

    @Override // Реализовал
    public boolean addAll(Collection<? extends T> c) {
        boolean check = false;
        for (T element : c) {
            check = add(element);
        }
        modCount++;
        return check;
    }

    @Override // Реализовал
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean check = false;
        for (T element : c) {
            add(index, element);
            index++;
            check = true;
        }
        modCount++;
        return check;
    }

    @Override // Реализовал
    public boolean removeAll(Collection<?> c) {
        boolean check = false;
        for (Object elementOfC : c) {
            for (T elementOfT : items) {
                if (elementOfC.equals(elementOfT)) {
                    check = remove(elementOfC);
                }
            }
        }
        modCount++;
        return check;
    }

    @Override // Реализовал
    public boolean retainAll(Collection<?> c) {
        boolean check = false;
        for (T elementOfT : items) {
            boolean contains = false;
            for (Object elementOfC : c) {
                if (elementOfC.equals(elementOfT)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                check = remove(elementOfT);
            }
        }
        modCount++;
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
            throw new IllegalArgumentException("index must be >=0");
        }
        if (index >= length) {
            throw new IllegalArgumentException("index must be <= " + (length - 1));
        }
        return items[index];
    }

    @Override // Реализовал
    public T set(int index, T element) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be >=0");
        }
        if (index >= length) {
            throw new IllegalArgumentException("index must be <= " + (length - 1));
        }
        T lastElement = items[index];
        items[index] = element;
        modCount++;
        return lastElement;
    }

    @Override // Реализовал
    public void add(int index, T element) {
        if (length >= items.length) {
            increaseCapacity();
        }
        java.lang.System.arraycopy(items, index, items, index + 1, length - index - 1);
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
            throw new IllegalArgumentException("index must be >=0");
        }
        if (index >= length) {
            throw new IllegalArgumentException("index must be <= " + (length - 1));
        }
        T lastElement = items[index];
        java.lang.System.arraycopy(items, index + 1, items, index, length - index - 1);
        --length;
        modCount++;
        return lastElement;
    }

    @Override // Реализовал
    public int indexOf(Object o) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override // Реализовал
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(o)) {
                index = i;
            }
        }
        return index;
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
