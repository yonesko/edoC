package com.company;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Tits carina = new Tits();
        Poc
            a = new Poc(carina),
            b = new Poc(carina);

        new Thread(a).start();
        System.out.println("1 go");
        new Thread(b).start();
        System.out.println("2 go");

    }
}

class Tits {
    private int count = 2;
    public  int getCount() {
        System.out.println(Thread.currentThread().getName() + " Засыпаю...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Woke up!");
        return count;
    }
}

class Poc implements Runnable {
    private Tits t;
    public Poc(Tits t) {
        this.t = t;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + t.getCount());
    }
}