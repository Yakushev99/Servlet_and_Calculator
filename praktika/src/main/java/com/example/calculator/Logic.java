package com.example.calculator;

public class Logic {

    private double a, b;
    private String math;

    public Logic(double a, double b, String math){
        this.a = a;
        this.b = b;
        this.math = math;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }
}
