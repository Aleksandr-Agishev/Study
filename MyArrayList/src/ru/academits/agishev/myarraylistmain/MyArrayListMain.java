package ru.academits.agishev.myarraylistmain;

import ru.academits.agishev.myarraylist.MyArrayList;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        MyArrayList<Integer> list2 = new MyArrayList<>(100);

        list1.ensureCapacity(20);
        for (int i = 0; i < 15; i++) {
            list1.add(i);
        }
        list1.remove(5);
        list1.trimToSize();
        System.out.println(list1.size());

        for (int i = 0; i < 15; i++) {
            list2.add(2 * i);
        }
        System.out.println(list1.containsAll(list2));

        System.out.println(list1.addAll(list2));

        list1.set(1, 12);
        for (Integer i : list1) {
            System.out.print(i + ", ");
        }
        System.out.println();

        System.out.println(list1.isEmpty());
        System.out.println(list1.contains(null));
    }
}
