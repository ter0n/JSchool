package com.company;

public class Factorial implements Runnable{

    private int num;

    public Factorial(int num){
        this.num = num;
    }

    public long computeFactorial(int number){
        long numFactorial = 1;
        int n = number;
        if(number > 1){
            numFactorial = number * computeFactorial(number - 1);
        } else {
            numFactorial = 1;
        }
        return numFactorial;
    }

    @Override
    public void run() {
        long numFactorial = computeFactorial(num);
        System.out.println("factorial(" +  num + ") = "+ numFactorial);
    }
}
