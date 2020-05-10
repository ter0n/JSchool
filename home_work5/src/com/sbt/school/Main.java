package com.sbt.school;

import java.lang.reflect.Modifier;

public class Main {

    /*
        Для класса String получить и вывести на экран:
            1) Все модификаторы типа;
            2) Имя пакета;
            3) Классы иерархии
            4) Реализуемые иентерфейсы для класса и для его родителей
     */
    public static void task1(){
        Class strClass = String.class;
//        Class strClass = Integer.class;

        System.out.println("Задание 1: Для класса String получить и вывести на экран");

        //1) Все модификаторы типа;
        int modifiers = strClass.getModifiers();
        System.out.println("\t1) Все модификаторы типа:");
        System.out.println("\t\t" + Modifier.toString(modifiers));

        //2) Имя пакета;
        System.out.println("\t2) Имя пакета:");
        Package strClassPackage = strClass.getPackage();
        System.out.println("\t\t" + strClassPackage.getName());

        //3) Классы иерархии
        System.out.println("\t3) Классы иерархии:");
        String allHierarchy = "";
        Class superClass = strClass.getSuperclass();
        while (superClass != null){
            allHierarchy = allHierarchy + superClass.getName() + " ";
            superClass = superClass.getSuperclass();
        }
        System.out.println("\t\t" + allHierarchy);

        //4) Реализуемые иентерфейсы для класса и для его родителей
        System.out.println("\t4) Реализуемые иентерфейсы для класса и для его родителей:");
        Class [] interfaces = strClass.getInterfaces();
        for (Class inrfc: interfaces) {
            System.out.println("\t\t" + inrfc.getName());
        }
        Class superClass2 = strClass.getSuperclass();
        while (superClass2 != null){
            Class [] interfaces2 = superClass2.getInterfaces();
            for (Class inrfc: interfaces2) {
                System.out.println("\t\t" + inrfc.getName());
            }
            superClass2 = superClass2.getSuperclass();
        }

    }

    public static void main(String[] args) {
	    task1();
    }
}
