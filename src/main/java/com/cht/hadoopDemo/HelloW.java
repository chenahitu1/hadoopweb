package com.cht.hadoopDemo;

public class HelloW {
    /**
     * 普通函数，需要主动调用
     */
    public static void print(){
        System.out.println("我是陈海土");
    }

    /**
     * 构造函数，在实例化对象的时候，自动调用
     */
    public HelloW() {
        System.out.println("调用构造函数");
    }
    public static void main(String[] args) {
        HelloW hellow=new HelloW();
        print();
    }
}
