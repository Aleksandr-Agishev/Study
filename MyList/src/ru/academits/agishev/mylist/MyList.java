package ru.academits.agishev.mylist;

class MyList<T> {
    private int count; // нумерация с нуля. Однако, count = количеству элементов, а не индексу последнего.
    private ListItem<T> head;

    private ListItem<T> getLinkOfIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be >=0");
        }

        if (index>=count){
            throw new IllegalArgumentException("index must be <= " + (count-1));
        }

        int elementNumber = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (elementNumber == index) {
                return p;
            }
            elementNumber++;
        }
        throw new IllegalArgumentException("index must be <= " + elementNumber);
    }

    MyList() {
        count = 0;
    }

    MyList(T data){
        head = new ListItem<>(data);
        count = 1;
    }
    int getSize() {
        return count;
    }

    T getFirstElement() {
        return head.getData();
    }

    T getElementOfIndex(int index) { //получение значения по указанному индексу
        return getLinkOfIndex(index).getData();
    }

    T setElementOfIndex(T element, int index) { //установка нового значения и получение изменяемого значения по указанному индексу
        ListItem<T> p = getLinkOfIndex(index);
        T data = p.getData();
        p.setData(element);
        return data;
    }

    T removeElementOfIndex(int index) { // удаление элемента по идексу
        if (index >= count) {
            throw new IllegalArgumentException("index must be <= " + count);
        }

        if (index == 0) {
            return removeFirstElement();
        }
        if (index == count - 1) {
            ListItem<T> linkPrev = getLinkOfIndex(index - 1);
            T data = linkPrev.getNext().getData();
            getLinkOfIndex(index - 1).setNext(null);
            count--;
            return data;
        }

        ListItem<T> linkPrev = getLinkOfIndex(index - 1);
        ListItem<T> link = linkPrev.getNext();
        T data = link.getData();
        linkPrev.setNext(link.getNext());
        link.setNext(null);
        count--;
        return data;
    }

    void insertElementToStart(T element) {
        if (count == 0) {
            head = new ListItem<>(element, null);
            count++;
            return;
        }
        head = new ListItem<>(element, head);
        count++;
    }

    void insertElementOfIndex(T element, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be >=0");
        }

        if (index == 0) {
            insertElementToStart(element);
            return;
        }

        if (index == count) {
            ListItem<T> p = new ListItem<>(element, null);
            getLinkOfIndex(index - 1).setNext(p);
            count++;
            return;
        }

        ListItem<T> prevLink = getLinkOfIndex(index - 1);
        ListItem<T> newLink = new ListItem<>(element, prevLink.getNext());
        prevLink.setNext(newLink);
        count++;
    }

    Boolean removeFirstElementOfValue(T value) {
        int elementIndex = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData().equals(value)) {
                removeElementOfIndex(elementIndex);
                return true;
            }
        }
        return false;
    }

    T removeFirstElement() {
        if (count > 1) {
            T data = head.getData();
            head = head.getNext();
            count--;
            return data;
        }
        if (count == 1) {
            T data = head.getData();
            head = null;
            count--;
            return data;
        }
        return null;
    }

    void turn() {
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

    MyList<T> copy() {
        MyList<T> newMyList = new MyList<>(head.getData());
        ListItem<T> newElement = newMyList.head;
        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> nextElement = new ListItem<>(p.getData());
            newElement.setNext(nextElement);
            newElement = nextElement;
            newMyList.count++;
        }
        return newMyList;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(ListItem<T> p = head; p!=null;p=p.getNext()){
            sb.append(p.getData().toString())
            .append(";");
        }
        sb.append("=")
        .append(count);
        return sb.toString();
    }
}
