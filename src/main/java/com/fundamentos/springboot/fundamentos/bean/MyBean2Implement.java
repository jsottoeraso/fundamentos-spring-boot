package com.fundamentos.springboot.fundamentos.bean;

public class MyBean2Implement implements Mybean{
    @Override
    public void print() {
        System.out.println("Hola Desde mi implementacion propia del bean2");
    }
}
