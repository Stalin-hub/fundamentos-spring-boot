package com.fundamentos.springboot.fundamentos.bean;

public class MyOperationImplement implements MyOperation {

    @Override
    public int sum(int number) {
        return number + 1;
    }

    @Override
    public int subst(int number) {
        return number - 1;
    }

    @Override
    public int multi(int num1, int num2) {
        return num1 * num2;
    }

    @Override
    public float div(float num1, float num2) {
        return num1 / num2;
    }
}
