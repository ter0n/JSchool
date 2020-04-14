package com.sbt.school;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Number> myLinkedList = new MyLinkedList<>();
        myLinkedList.add(543);
        myLinkedList.add(17);
        myLinkedList.add(24793);
        myLinkedList.add(3004);
        myLinkedList.add(9241);
        myLinkedList.add(9242);

        System.out.println("list size : " + myLinkedList.getSize());

        Iterator iterator = myLinkedList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


//        myLinkedList.remove(4);
//        myLinkedList.add(0, 7164);
//        myLinkedList.add(6, 4);
//        System.out.println("After remove");
//        Iterator iterator2 = myLinkedList.iterator();
//        while(iterator2.hasNext()){
//            System.out.println(iterator2.next());
//        }

        MyLinkedList<Double> myLinkedList2 = new MyLinkedList<>();
        myLinkedList2.add(1.25);
        myLinkedList2.add(549.154);
        myLinkedList2.add(0.14779);
        myLinkedList2.add(48.123);
        myLinkedList2.add(111111.0);
        System.out.println("Вывод элементов второго списка");
        Iterator iterator2 = myLinkedList2.iterator();
        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

//        myLinkedList.addAll(myLinkedList2);
//        System.out.println("Вывод элементов объединенного  списка");
//        Iterator iterator3 = myLinkedList.iterator();
//        while(iterator3.hasNext()){
//            System.out.println(iterator3.next());
//        }


        myLinkedList.copy(myLinkedList2);
        System.out.println("Вывод элементов скопированного списка");
        Iterator iterator3 = myLinkedList.iterator();
        while(iterator3.hasNext()){
            System.out.println(iterator3.next());
        }
    }
}
