package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanImplement implements Mybean{
    @Override
    public void print() {
        System.out.println("Hola Desde mi implementacion propia del bean");
    }
}
