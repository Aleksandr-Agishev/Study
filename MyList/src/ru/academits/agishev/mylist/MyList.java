package ru.academits.agishev.mylist;

public class MyList<T> {
    private int count; // нумерация с нуля. Однако, count = количеству элементов, а не индексу последнего.
    private ListItem<T> head;

    private ListItem<T> getItemByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index must be >=0");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("index must be <= " + (count - 1));
        }

        int elementNumber = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (elementNumber == index) {
                return p;
            }
            elementNumber++;
        }

        throw new IndexOutOfBoundsException("index must be <= " + elementNumber);
    }

    public MyList() {
    }

    public MyList(T data) {
        head = new ListItem<>(data);
        count = 1;
    }

    public int getSize() {
        return count;
    }

    public T getFirstElement() {
        if (count == 0) {
            return null;
        }
        return head.getData();
    }

    public T getElementByIndex(int index) { //получение значения по указанному индексу
        return getItemByIndex(index).getData();
    }

    public T setElementByIndex(T element, int index) { //установка нового значения и получение изменяемого значения по указанному индексу
        ListItem<T> p = getItemByIndex(index);
        T data = p.getData();
        p.setData(element);
        return data;
    }

    public T removeElementByIndex(int index) { // удаление элемента по идексу
        if (index < 0) {
            throw new IndexOutOfBoundsException("index must be >=0");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("index must be <= " + count);
        }

        if (index == 0) {
            return removeFirstElement();
        }

        ListItem<T> linkPrev = getItemByIndex(index - 1);
        ListItem<T> link = linkPrev.getNext();
        T data = link.getData();
        linkPrev.setNext(link.getNext());
        count--;
        return data;
    }

    public void insertElementToStart(T element) {
        head = new ListItem<>(element, head);
        count++;
    }

    public void insertElementByIndex(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index must be >=0");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("index must be <= " + count);
        }

        if (index == 0) {
            insertElementToStart(element);
            return;
        }

        ListItem<T> prevLink = getItemByIndex(index - 1);
        ListItem<T> newLink = new ListItem<>(element, prevLink.getNext());
        prevLink.setNext(newLink);
        count++;
    }

    public boolean remove(T value) {
        int elementIndex = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData().equals(value) || p.getData() == value) {
                removeElementByIndex(elementIndex);
                return true;
            }
            elementIndex++;
        }
        return false;
    }

    public T removeFirstElement() {
        if (count == 0) {
            throw new IllegalArgumentException("list is empty");
        }

        if (count > 1) {
            T data = head.getData();
            head = head.getNext();
            count--;
            return data;
        }

        return null;
    }

    public void turn() {
        if (count <= 1) {
            return;
        }

        ListItem<T> iteratorNext = head.getNext();
        ListItem<T> iteratorPrev = head;
        head.setNext(null);
        for (ListItem<T> p = iteratorNext.getNext(); p != null; p = p.getNext()) {
            iteratorNext.setNext(iteratorPrev);
            iteratorPrev = iteratorNext;
            iteratorNext = p;
        }
        iteratorNext.setNext(iteratorPrev);
        head = iteratorNext;
    }

    public MyList<T> copy() {
        if (count == 0) {
            return new MyList<>();
        }

        MyList<T> newList = new MyList<>(head.getData());
        ListItem<T> newElement = newList.head;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> nextElement = new ListItem<>(p.getData());
            newElement.setNext(nextElement);
            newElement = nextElement;
            newList.count++;
        }
        return newList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData() != null) {
                sb.append(p.getData().toString())
                        .append(";");
            } else {
                sb.append("null")
                        .append(";");
            }
        }
        sb.append("=")
                .append(count);
        return sb.toString();
    }
}
