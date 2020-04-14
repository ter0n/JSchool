package com.sbt.school;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T>{
    private Node<T> first;
    private Node<T> last;
    private int size;

    MyLinkedList(){
        first = new Node<T>(null, null, null);
        last = first;
        size = 0;
    }

    MyLinkedList(T newItem){
        first = new Node<T>(newItem, null, null);
        last = first;
        size = 1;
    }
    /*
    Добавляем новый элемент в конец списка
    element -- значение нового элемента
     */
    void add(T element){
        if(size == 0){
            first.item = element;
            last = first;
        } else if(size == 1) {
            Node<T> node = new Node<T>(element, null, first);
            last = node;
            first.next = node;
        } else {
            Node<T> node = new Node<T>(element, null, last);
            last.next = node;
            last = node;
        }
        size++;
    }
    /*
    Добавляет элемент на заданную позицию в список
    index -- номер позиции в списке, на которю вставим новый элемеент
    element -- значение нового элемента
     */
    void add(int index, T element){
        if((index > size) || (index < 0)){
            //возвращаем экзепшен/сообщение об ошибке
            return;
        } else {
            if(size == 0){
                first.item = element;
                last = first;
            } else if(size == 1) {
                if(index == 0){
                    Node<T> node = new Node(first.item, null, first);
                    last = node;
                    first.item = element;
                    first.next = node;
                } else {
                    Node<T> node = new Node(element, null, first);
                    last = node;
                    first.next = node;
                }
            } else {
                if(index == size){
                    Node<T> node = new Node(element, null, last);
                    last.next = node;
                    last = node;
                }else {
                    //ищем элемент, стоящий на заданном индексе
                    Node<T> curElem = getNode(index);
                    //создаем новый узел
                    Node<T> node = new Node(element, curElem, curElem.previous);
                    //вставляем узел в список
                    if (index > 0) {
                        Node<T> prev = curElem.previous;
                        prev.next = node;
                    } else {
                        first = node;
                    }

                    curElem.previous = node;
                }
            }
            size++;
        }
    }
    /*
    Возвращает элемент, стоящий на заданной позиции в списке
    index -- номер позиции в списке
     */
    T get(int index){
        if((index > size) || (index < 0)){
            return null;
            //надо бы вставить экзепшен какой-то
        } else {
            //ищем элемент, стоящий на заданном индексе
            Node<T> node = getNode(index);
            //возвращаем значение из найденного узла
            return (T)node.item;
        }
    }

    /*
    Метод, возвращающий узел, стоящий на заданной позиции в списке
    index -- номер позиции в списке
     */
    private Node getNode(int index){
        if((index > (size - 1)) || (index < 0)){
            return null;
            //надо бы вставить экзепшен какой-то
        } else {
            int curIndex = 0;
            Node<T> curElem = first;
            while(curIndex < index){
                curIndex++;
                curElem = curElem.next;
            }
            return curElem;
        }
    }

    /*
    Удаляет элемент, стоящий на указанной позиции в списке
    index -- номер позиции в списке
     */
    void remove(int index){
        if((index > (size - 1)) && (index > 0)){
            return;
            //надо бы вставить экзепшен какой-то
        } else {
            //ищем элемент, стоящий на заданном индексе
            Node<T> node = getNode(index);
            //удвляем найденный узел узел
            Node<T> next = node.next;
            Node<T> prev = node.previous;
            if(index < (size - 1))
                next.previous = prev;
            if(index > 0)
                prev.next = next;
            size--;
        }
    }
    /*
    Реализация итератора
     */
    @Override
    public Iterator<T> iterator() {
        Iterator myLLIterator = new Iterator<T>() {

            Node curNode = first;
            int index = 0;

            @Override
            public boolean hasNext() {
                boolean canGoNext;
                if(index < size){
                    canGoNext = true;
                } else {
                    canGoNext = false;
                }
                return canGoNext;
            }

            @Override
            public T next() {
                T item = (T) curNode.item;
                curNode = curNode.next;
                index++;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return myLLIterator;
    }

    int getSize(){
        return size;
    }

    boolean addAll(MyLinkedList<? extends T> adderList){
        boolean addComplete;
        Node<T> node;
        Iterator addListIter = adderList.iterator();
        while(addListIter.hasNext()){
             this.add((T)addListIter.next());
        }
        addComplete = true;
        return addComplete;
    }

    boolean copy (MyLinkedList<? extends T> copList){
        //если удалить ссылки с первого и последнего элемента, удалятся ли промежуточные элементы?
        size = 0;
        first.next = null;
        last.previous = null;
        Iterator addListIter = copList.iterator();
        while(addListIter.hasNext()){
            this.add((T)addListIter.next());
        }
        return true;
    }


}
