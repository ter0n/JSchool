package com.sbt.school;

import java.lang.reflect.*;
import java.util.ArrayList;

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

    /*
        получить все возможные типы-параметры в объекте класса Runtime
     */
    public static void task2(){
        Runtime runtime = new Runtime();
        Class runtimeClass = runtime.getClass();
        try {

            System.out.println("Задание 2: получить все возможные типы-параметры в объекте класса Runtime\n");



            //Поле integers
            System.out.println("Поле integers");
            Field intgrs = runtimeClass.getDeclaredField("integers");
            Type genericFieldType = intgrs.getGenericType();

            if(genericFieldType instanceof ParameterizedType){
                ParameterizedType aType = (ParameterizedType) genericFieldType;
                Type[] fieldArgTypes = aType.getActualTypeArguments();
                for(Type fieldArgType : fieldArgTypes){
                    Class fieldArgClass = (Class) fieldArgType;
                    System.out.println("integers field type = " + fieldArgClass);
                }
                System.out.println();
            }

            //метод numbers
            System.out.println("Метод numbers");
            Method numbersMethod = runtimeClass.getMethod("numbers", null);

            Type numbersReturnType = numbersMethod.getGenericReturnType();

            if(numbersReturnType instanceof ParameterizedType){
                ParameterizedType type = (ParameterizedType) numbersReturnType;
                System.out.println("numbers return type = " + type.getTypeName());
                Type[] typeArguments = type.getActualTypeArguments();
                for(Type typeArgument : typeArguments){
                    System.out.println("numbers return type parameter = " + typeArgument);
                }
                System.out.println();
            }





            //метод strings
            System.out.println("Метод strings");
            Method stringsMethod = runtimeClass.getMethod("strings", null);

            Type stringsReturnType = stringsMethod.getGenericReturnType();

            if(stringsReturnType instanceof ParameterizedType){
                ParameterizedType type = (ParameterizedType) stringsReturnType;
                System.out.println("strings return type = " + type.getTypeName());
                Type[] typeArguments = type.getActualTypeArguments();
                for(Type typeArgument : typeArguments){
                    System.out.println("strings return type parameter = " + typeArgument);
                }
                System.out.println();
            }

            //метод call
            System.out.println("Метод call");
            Type[] genericInterfaces =runtimeClass.getGenericInterfaces();
            for (Type genericInterface : genericInterfaces) {
                if (genericInterface instanceof ParameterizedType) {
                    Type[] genericTypes = ((ParameterizedType) genericInterface).getActualTypeArguments();
                    for (Type genericType : genericTypes) {
                        System.out.println("Call return type: " + genericType);
                    }
                }
            }

        } catch (NoSuchFieldException e) {
            System.out.println("Field integers is not found!");
        } catch (NoSuchMethodException e) {
            System.out.println("Some method is not found!");
        }
    }

    public static void main(String[] args) {
//	    task1();
//	    task2();
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(5);
        integerArrayList.add(478);
        integerArrayList.add(654);
        integerArrayList.add(1);
        integerArrayList.add(0);
        TestA testA = new TestA(145, "something", 329.215, integerArrayList);
        testA.printFields();
        TestB testB = new TestB();
        testB.printField();

        try {
            BeanUtils.assign(testB,testA);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        testB.printField();
    }
}
