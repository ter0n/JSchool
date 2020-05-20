package com.sbt.school;

import java.util.ArrayList;

public class TestB {
    private int intValue;
    private String stringValue;
    private float floatValue;
    private ArrayList<Integer> integerArrayList;

    TestB(){
        intValue = 0;
        stringValue = "empty";
        floatValue = (float) 0.0;
        integerArrayList = new ArrayList<>();
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void printField(){
        System.out.println("Fields of TestB:");
        System.out.println("intValue = " + intValue);
        System.out.println("stringValue = " + stringValue);
        System.out.println("floatValue = " + floatValue);
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
