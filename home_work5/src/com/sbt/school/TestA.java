package com.sbt.school;

import java.util.ArrayList;

public class TestA {
    private int intValue;
    private String stringValue;
    private double doubleValue;
    private ArrayList<Integer> integerArrayList;

    TestA(int a, String s, double d, ArrayList<Integer> arrayList){
        intValue = a;
        stringValue = s;
        doubleValue = d;
        integerArrayList = arrayList;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public void printFields(){
        System.out.println("Fields of TestA:");
        System.out.println("intValue = " + intValue);
        System.out.println("stringValue = " + stringValue);
        System.out.println("doubleValue = " + doubleValue);
        System.out.println("integerArrayList = " + integerArrayList.toString());

        System.out.println();
    }

    public ArrayList<Integer> getIntegerArrayList() {
        return integerArrayList;
    }

    public void setIntegerArrayList(ArrayList<Integer> integerArrayList) {
        this.integerArrayList = integerArrayList;
    }
}
