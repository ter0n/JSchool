package com.sbt.school;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {

        Class fromClass = from.getClass();
        Class toClass = to.getClass();

        //сканируем from на getters
        Method[] fromMethods = fromClass.getMethods();
        ArrayList<Method> gettersFrom = findGetters(fromMethods);

        //сканируем to на setters
        Method[] toMethods = toClass.getMethods();
        ArrayList<Method> settersFrom = findSetters(toMethods);

        //ищем соответствие геттера и сеттера и вызываем сеттер
        String setterName, setterSubName;
        String getterName, getterSubName;
        for(Method method: settersFrom){
            setterName = method.getName();
            setterSubName = setterName.substring(3, setterName.length());
            for(Method getterMethod: gettersFrom){
                getterName = getterMethod.getName();
                getterSubName = getterName.substring(3, getterName.length());
                if(setterSubName.equals(getterSubName)){
                    //проверяем соответствие типов
                    if(equalType(method,getterMethod)) {
                        //вызываем подходящие методы
                        Object returnValue = method.invoke(to, getterMethod.invoke(from));
                    }
                }
            }
        }
    }
    /*
    Проверяет, что method является геттером
     */
    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }

    /*
    Проверяет, что method является сеттером
     */
    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }
    /*
    Среди массива методов находит все геттеры
     */
    private static ArrayList<Method> findGetters(Method[] methods){
        ArrayList<Method> getters = new ArrayList<Method>();
        for(Method method: methods){
            if(isGetter(method))
                getters.add(method);
        }
        return getters;
    }
    /*
    Среди массива методов находит все сеттеры
     */
    private static ArrayList<Method> findSetters(Method[] methods){
        ArrayList<Method> setters = new ArrayList<Method>();
        for(Method method: methods){
            if(isSetter(method))
                setters.add(method);
        }
        return setters;
    }

    /*
        проверяет соответствие типов
     */
    private static boolean equalType(Method setter, Method getter){
        boolean equals = false;
        Type[] setterType = setter.getParameterTypes();
        Type[] setterGType = setter.getGenericParameterTypes();
        Type getterType = getter.getReturnType();
        Type getterGType = getter.getGenericReturnType();
        if(getterType != null){
            if(getterType.equals(setterType[0]))
                equals = true;
            else equals = false;
        } else {
            if(getterGType.equals(setterGType[0]))
                equals = true;
            else equals = false;
        }

        return equals;
    }

}
