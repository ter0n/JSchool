package com.sbt.school;

public class Node<T>{
    T item;
    Node next;
    Node previous;

    Node(T newItem, Node itemNext, Node itemPrevious){
        this.item = newItem;
        this.next = itemNext;
        this.previous = itemPrevious;
    }
}
