package ru.academits.agishev.mylist_main;

import ru.academits.agishev.mylist.MyList;

public class MyListExample {
    public static void main(String[] args) {
        MyList<Integer> list1 = new MyList<>(10);
        MyList<Integer> list2 = new MyList<>();
        System.out.println(list1.getFirstElement());
        System.out.println(list2.getSize());

        list1.insertElementByIndex(0, 11);
        int value = list1.getElementByIndex(0);
        System.out.println(value);

        list1.setElementByIndex(2, 1);
        System.out.println(list1.getElementByIndex(1));

        value = list1.setElementByIndex(2, 0);
        System.out.println(value);

        list1.removeElementByIndex(0);
        list1.insertElementToStart(10);
        System.out.println(list1.toString());

        list2.insertElementToStart(12);
        System.out.println(list2.toString());

        boolean b = list2.remove(12);
        System.out.println(b);

        list1.removeFirstElement();
        System.out.println(list1.toString());

        list1.turn();
        list1.insertElementToStart(32);
        list1.turn();
        System.out.println(list1.toString());

        list1.insertElementByIndex(1, 15);
        list1.insertElementToStart(52);
        list1.insertElementToStart(5);
        System.out.println(list1.toString());

        list1.turn();
        System.out.println(list1.toString());

        value = list1.removeElementByIndex(2);
        System.out.println(list1);
        System.out.println(value);

        MyList<Integer> list3 = list1.copy();
        System.out.println(list3.toString());

        MyList<Integer> list4 = new MyList<>();
        System.out.println(list4);

        MyList<Integer> list5 = list4.copy();
        System.out.println(list5);
    }
}
