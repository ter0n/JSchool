package com.sbt.school;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        String pluginRootDirectory = "G:/java_projects/JSchool/home_work6/out/production/home_work6/com/sbt/Plugins";

        PluginManager pm = new PluginManager(pluginRootDirectory);

        String pluginName1 = "Plugin1";
        String pluginClassName = "MyPlugin";

        pm.load(pluginName1, pluginClassName);

    }
}
