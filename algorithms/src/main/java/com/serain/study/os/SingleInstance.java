package com.serain.study.os;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SingleInstance {
    private static volatile SingleInstance instance;
    private static String name;

    private SingleInstance() {
        System.out.println("SingleInstance is created");
    }

    private SingleInstance(String name) {
        System.out.println("SingleInstance is created");
        SingleInstance.name = name;
    }

    public static SingleInstance getInstance() {
        if (instance == null) {
            synchronized (SingleInstance.class) {
                if (instance == null) {
                    instance = new SingleInstance("我是一个单例");
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingleInstance instance = SingleInstance.getInstance();
        System.out.println(instance.name);
        System.out.println(SingleInstance.getInstance().name);
    }
}
