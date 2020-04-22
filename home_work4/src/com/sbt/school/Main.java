package com.sbt.school;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void readContent(String siteUrl) throws IOException, MalformedURLException{
        URL url = null;
        BufferedReader br = null;
        String s;
        try {
            url = new URL(siteUrl);
            br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            while((s = br.readLine())!=null){
                System.out.println(s);
            }
            br.close();
        } catch (MalformedURLException e) {
            throw new MalformedURLException();
//            System.out.println("Что-то пошло не так 1");
        } catch (IOException e) {
            throw new IOException();
//            System.out.println("Что-то пошло не так 2");
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the website url: ");
        while(true) {
            String address = in.nextLine();
            try {
                readContent(address);
                break;
            } catch (MalformedURLException e) {
                System.out.println("Please enter another website url: ");
            }catch (IOException e) {
                System.out.println("Please enter another website url: ");
            }
        }
    }
}
