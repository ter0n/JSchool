package com.sbt.school;

public class Main {

    public static void main(String[] args) {
        Person jack = new Person(true, "Jack");
        Person kurt = new Person(true, "Kurt");
        Person ann = new Person(false, "Ann");
        Person liza = new Person(false, "Liza");

        boolean jl = jack.marry(kurt);
        System.out.println(jl);

        jl = jack.marry(liza);
        System.out.println(jl);

        jl = jack.divorce();
        System.out.println(jl);

    }
}
