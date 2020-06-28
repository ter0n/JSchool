package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Дан файл, содержащий несколько случайных чисел от 1 до 50.
//        Необходимо написать многопоточное приложение, которое параллельно рассчитает и выведет в консоль факториал для каждого числа из файла.


public class Main {

    public static ArrayList<Integer> arrListFromFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        Scanner scanner = new Scanner(fr);
        int i = 0;
        ArrayList<Integer> numList = new ArrayList<>();
        while(scanner.hasNextInt()){
            numList.add(scanner.nextInt());
        }
        fr.close();
        return numList;
    }

    public static void main(String[] args) throws IOException {

        //забираем числа с файла
        String fileName = "numbers.txt";
        ArrayList<Integer> numList = arrListFromFile(fileName);


        long start = System.currentTimeMillis();
        for (Integer num: numList) {

            Thread t = new Thread(new Factorial(num));
            t.start();
        }
        long finish = System.currentTimeMillis();
        System.out.println("time for parallel: " + (finish - start));

//        long start = System.currentTimeMillis();
//        for (Integer num: numList) {
//            Factorial factorial = new Factorial(num);
//            factorial.run();
//        }
//        long finish = System.currentTimeMillis();
//        System.out.println("time for sungle thread: " + (finish - start));

    }
}
