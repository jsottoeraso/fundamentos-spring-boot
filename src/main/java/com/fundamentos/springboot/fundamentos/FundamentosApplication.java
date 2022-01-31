package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.Mybean;
import com.fundamentos.springboot.fundamentos.component.ComponetDepency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponetDepency componetDepency;
	private Mybean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	public FundamentosApplication(@Qualifier("componenTwoImplement") ComponetDepency componetDepency, Mybean myBean,MyBeanWithDependency myBeanWithDependency){
		this.componetDepency = componetDepency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}

	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componetDepency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
	}
}
