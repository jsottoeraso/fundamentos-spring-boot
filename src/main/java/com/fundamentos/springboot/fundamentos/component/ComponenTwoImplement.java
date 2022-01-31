package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponenTwoImplement implements ComponetDepency {
    @Override
    public void saludar() {
        System.out.println("Hola Mundo Desde mi componente 2");
    }
}
