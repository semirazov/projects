package com.seven.uf;

public class MainTime {
    public static void main(String[] args) throws InterruptedException {
        while(true){
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis()/1000);
        }
    }
}
