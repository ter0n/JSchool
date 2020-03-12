package com.sbt.school;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


class strLengthComparator implements Comparator<String>{

    @Override
    public int compare(String s, String t1) {
        int comp = 1;
        if(s.equals(t1)){
            comp = 0;
        } else {
            if (s.length() > t1.length()) {
                comp = 1;
            } else if (s.length() < t1.length()) {
                comp = -1;
            }
        }
        return comp;
    }
}


public class Main {

    public static void main(String[] args) {

        File file = new File("some_words.txt");
        try(FileReader fr = new FileReader(file)){

            //читаем файл в список строк
            List<String> fileLines = Files.readAllLines(file.toPath());

            //компаратор для контенера
            strLengthComparator strComp = new strLengthComparator();
            //контейнер для слов
//            Map<String, Integer> wordsHashMap = new HashMap<String, Integer>();
            Map<String, Integer> wordsHashMap = new TreeMap<String, Integer>(strComp);

            //разбиваем строки на отдельные слова
            for (String line: fileLines) {
                String[] words = line.split(" ");
                int startKey = 1;
                for (String oneWord: words) {
                    if(wordsHashMap.containsKey(oneWord)){
                        int newKey = wordsHashMap.get(oneWord) + 1;
                        wordsHashMap.put(oneWord, newKey);
                    } else{
                      wordsHashMap.put(oneWord, startKey);
                    }
                }
            }
            //Задание 1: подсчитать количество различных слов в файле
            System.out.println("Number of different words: " + wordsHashMap.size());

            //Задание 2: вывод слов по возрастанию их длины
            Set<String> keys = wordsHashMap.keySet();
            System.out.println("Слова: " + keys);

            //Задание 3: вывести сколько раз каждое слово встречалось в файле
            for (Map.Entry elem : wordsHashMap.entrySet()) {
                System.out.println(elem.getKey() + " : " + elem.getValue());
            }

            //Задание 4: вывести строки файла в обратном порядке
            System.out.println("Строки в обратном порядке");
            for(int i = fileLines.size() - 1; i >= 0; i--){
                System.out.println(fileLines.get(i));
            }

            //Задание 5: реализация своего итератора для обхода списка в обратном порядке
            Iterator<String> reverseIterator = new Iterator<String>(){

                private int currentIndex = fileLines.size() - 1;

                @Override
                public boolean hasNext() {
                    return currentIndex >= 0 && fileLines.get(currentIndex) != null;
                }

                @Override
                public String next() {
                    return fileLines.get(currentIndex--);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
            System.out.println("Использование своего итератора");
            while(reverseIterator.hasNext()){
                System.out.println(reverseIterator.next());
            }

            //Задание 6: вывод строки, номер которой задает пользователь
            Scanner in = new Scanner(System.in);
            int userIndex = 0;
            if(!fileLines.isEmpty()){
                while(true){
                    System.out.println("Input line number: (anything else for exit)");
                    userIndex = in.nextInt();
                    if((userIndex >= 0) && (userIndex < fileLines.size())){
                        System.out.println(fileLines.get(userIndex));
                    } else {
                        break;
                    }
                }
            }

        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }
}
