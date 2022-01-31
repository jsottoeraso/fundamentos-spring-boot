package com.fundamentos.springboot.fundamentos.bean;

public class MyOperation2Implement implements MyOperation2{
    private MyBeanWithDependency myBeanWithDependency;

    public MyOperation2Implement(MyBeanWithDependency myBeanWithDependency) {
        this.myBeanWithDependency = myBeanWithDependency;
    }

    @Override
    public int multiplicacion(int num) {
        return num;
    }
}
