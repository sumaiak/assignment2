package org.example.Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise1 implements Runnable {
    private char startLetter;

    public Exercise1(char startLetter) {
        this.startLetter = startLetter;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        char startLetter = 'a';
        for (int i = 0; i < 4; i++) {
            executorService.submit(new Exercise1(startLetter));
            startLetter++;
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        for (char letter = startLetter; letter <= 'z'; letter++) {
            System.out.println(  "this is" + Thread.currentThread().getName()+"   " +letter);

        }


    }
}