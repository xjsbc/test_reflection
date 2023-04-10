package com.bc;

public class Car {
    public String namePub="黄哥";

    protected String namePri="拉腚";

    public Car(){}

    private Car(String namePub,String namePri){
        this.namePub=namePub;
        this.namePri=namePri;
    }

    public void m01(){
        System.out.println("共有方法被调用");
    }

    private void m02(String namePri){

        System.out.println(namePri+"私有方法被调用");
    }

    @Override
    public String toString() {
        return "Car{" +
                "namePub='" + namePub + '\'' +
                ", namePri='" + namePri + '\'' +
                '}';
    }
}
