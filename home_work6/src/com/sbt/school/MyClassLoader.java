package com.sbt.school;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    private String classPath;

    public MyClassLoader(String classPath){
        this.classPath = classPath;
    }

    @Override
    public Class findClass(String name){
        byte[]classBytes = new byte[0];
        try {
            classBytes = loadClassFromFile(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[]loadClassFromFile(String fileName) throws FileNotFoundException {

        String filePAth;
        if(fileName.endsWith(".class")){
            filePAth = classPath + "/" + fileName;
        } else {
            filePAth = classPath + "/" + fileName + ".class";
        }

        InputStream inputStream = new FileInputStream(new File(filePAth));
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }



}
