package com.sbt.school;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/*
    ищет скомпелированные классы в pluginRootDirectory\pluginName\
 */
public class PluginManager {
    private final String pluginRootDirectory;
    private ArrayList<String> classesName;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        String pluginDir = pluginRootDirectory + "/" + pluginName;

        MyClassLoader mcl = new MyClassLoader(pluginDir);

        Class newPluginClass = mcl.loadClass( pluginClassName);
        Method method = newPluginClass.getDeclaredMethod("doUsefull");
        try {
            method.invoke(newPluginClass.newInstance());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ClassLoader scl = ClassLoader.getSystemClassLoader();

        return null;
    }

    private URL[] getURLFromPath(String path){
        File f = new File(path);
        File[] pluginsList = f.listFiles();
        ArrayList<URL> urls = new ArrayList<>(pluginsList.length);
        try {
            for(File plugDir: pluginsList){
                urls.add(plugDir.toURI().toURL());
            }
            return urls.toArray(new URL[urls.size()]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setClassesName(String path){
        File f = new File(path);
        File[] pluginsList = f.listFiles();
        classesName = new ArrayList<>();
        for(File plugDir: pluginsList){
            File[] pluginClassesList = plugDir.listFiles();
            for(File plugClassFile: pluginClassesList){
                if(plugClassFile.getName().endsWith(".class"))
                    classesName.add(plugClassFile.getName());
            }
        }
    }

}
