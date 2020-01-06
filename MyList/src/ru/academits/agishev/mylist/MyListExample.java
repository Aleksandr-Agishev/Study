package ru.academits.agishev.mylist;

public class MyListExample {
    public static void main(String[] args) {
        MyList<Integer> list1 = new MyList<>(10);
        MyList<Integer> list2 = new MyList<>();
        int i = list2.getSize();
        System.out.println(list1.getFirstElement());
        list1.insertElementOfIndex(11,1);
        int n = 1;
        i+= list1.getElementOfIndex(n);
        System.out.println(list1.getElementOfIndex(1));
        int p = list1.setElementOfIndex(n+1,n);
        System.out.println(list1.getElementOfIndex(1));
        p+= list1.setElementOfIndex(n+1,n-1);
        n=list1.removeElementOfIndex(0);
        list2.insertElementToStart(12);
        list1.insertElementToStart(10);
        System.out.println(n);
        n = 1;
        p+= list1.removeElementOfIndex(0);
        list2.insertElementOfIndex(1,0);
        boolean b = list2.removeFirstElementOfValue(n);
        System.out.println(b);
        System.out.println(list2.removeFirstElementOfValue(15));
        p+= list1.removeFirstElement();
        System.out.println(list1.toString());
        list1.turn();
        list1.insertElementToStart(32);
        list1.turn();
        System.out.println(list1.toString());
        list1.insertElementOfIndex(1,1);
        list1.insertElementToStart(52);
        list1.insertElementToStart(5);
        System.out.println(list1.toString());
        list1.turn();
        System.out.println(list1.toString());
        MyList<Integer> list3 = list1.copy();
        System.out.println(list3.toString());
        i+=list3.getElementOfIndex(0);
        System.out.println(i+p+n);
    }
}
