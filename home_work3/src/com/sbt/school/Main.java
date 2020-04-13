package com.sbt.school;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
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


        myLinkedList.remove(4);
        myLinkedList.add(0, 7164);
        myLinkedList.add(6, 4);
        System.out.println("After remove");
        Iterator iterator2 = myLinkedList.iterator();
        while(iterator2.hasNext()){
            System.out.println(iterator2.next());
        }

    }
}
