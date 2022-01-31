package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public Mybean beanOperation(){
        return new MyBean2Implement();
    }

    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }
    @Bean
    public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
    @Bean
    public MyOperation2 beanOperation2(MyBeanWithDependency myBeanWithDependency){
        return new MyOperation2Implement(myBeanWithDependency);
    }
}
