package org.yqj.source.basic.base.classloader;


import java.util.Date;
import java.util.List;

public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {
//        parentClassLoader();
//        myClassLoader();
//        myClassLoaderParent();
    }

    private static void myClassLoaderParent(){
        ClassLoader classLoader1 = new MyClassLoader("classDir");
        while (classLoader1 != null){
            System.out.print(classLoader1.getClass().getName() + " -> ");
            classLoader1 = classLoader1.getParent();
        }
    }

    private static void myClassLoader(){
        try {
            ClassLoader classLoader1 = new MyClassLoader("classDir");
            Class classDate1 = classLoader1.loadClass("org.yqj.source.basic.base.classloader.MyDate");

            ClassLoader classLoader2 = new MyClassLoader("target/classes/org/yqj/source/basic/base/classloader");
            Class classDate2 = classLoader2.loadClass("MyDate");

            Date date = (Date) classDate1.newInstance();
            System.out.println("-----------------------------------------------------------");
            System.out.println(date.getClass().getClassLoader());
            System.out.println("date class name: " + date.getClass().getName());
            System.out.println("date ClassLoader: " + date.getClass().getClassLoader().getClass().getName());
            System.out.println("-----------------------------------------------------------");

            Date date2 = (Date) classDate2.newInstance();
            System.out.println(date.getClass().getClassLoader());
            System.out.println("date class name: " + date2.getClass().getName());
            System.out.println("date2 ClassLoader: "+date2.getClass().getClassLoader().getClass().getName());
            System.out.println("-----------------------------------------------------------");

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private static void parentClassLoader(){
        System.out.println("JVMClassLoader类的加载器的名称:" + ClassLoaderDemo.class.getClassLoader().getClass().getName());
        System.out.println("System类的加载器的名称:" + System.class.getClassLoader());
        System.out.println("List类的加载器的名称:" + List.class.getClassLoader());

        ClassLoader cl = ClassLoaderDemo.class.getClassLoader();
        while(cl != null){
            System.out.print(cl.getClass().getName()+"->");
            cl = cl.getParent();
        }
        System.out.println(cl);
    }
}
