package com.bc;

import java.util.Arrays;
//大根堆
public class Heap_ {
    private int[] value;
    private int useSize;

    public Heap_(){
        this.value=new int[11];
        this.useSize=0;
    }

    public Heap_(int capacity){
        this.value=new int[capacity];
        this.useSize=0;
    }

    public boolean isEmpty(){
        if (useSize==0)
            return true;
        return false;
    }
    public void offer(int e){
        if(useSize+1<value.length){
            capacity();
        }
        value[useSize++]=e;
        shiftUp(useSize);
    }

    public int poll(){
        if(isEmpty()){
            System.out.println("异常");
        }
        int res=value[0];
        shiftDown(useSize--);
        return res;
    }

    public void capacity(){
        value= Arrays.copyOf(value,value.length*2);
    }
    public void shiftUp(int useSize){
        int child=useSize-1;
        int parent=(child-1)/2;
        while (child>0){
            if(parent>=0&&value[child]>value[parent]){
                swap(child,parent,value);
            }else{
                break;
            }
            child=parent;
            parent=(child-1)>>1;
        }
    }
    public void shiftDown(int useSize){
        swap(0,useSize-1,value);
        useSize--;
        int parent=0;
        int child=parent*2+1;
        while (parent<useSize-1){
            if(child+1<useSize&&value[child]<value[child+1]){
                child++;
            }
            if(child<useSize&&value[child]>value[parent]){
                swap(child,parent,value);
            }else {
                break;
            }
            parent=child;
            child=parent*2+1;
        }
    }
    public void swap(int i,int j,int[] nums){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
