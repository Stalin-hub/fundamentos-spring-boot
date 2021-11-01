package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependencySum() {
        LOGGER.info("Hemos ingresado al método printWithDependency");
        int number = 5;
        LOGGER.debug("El número enviado a la dependencia es: " + number);
        System.out.println(myOperation.sum(number));
        System.out.println("Hola desde la implementación de un Bean con dependencia.");
        System.out.println("Suma: ");
        System.out.println(myOperation.sum(number));
    }

    @Override
    public void printWithDependencySubs() {
        int number = 5;
        System.out.println("Resta: ");
        System.out.println(myOperation.subst(number));
    }

    @Override
    public void printWithDependencyMulti() {
        System.out.println("Multiplicación: ");
        System.out.println(myOperation.multi(7, 5));
    }

    @Override
    public void printWithDependencyDiv() {
        System.out.println("División: ");
        System.out.println(myOperation.div(56.5F, 7.1F));
    }
}
