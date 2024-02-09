package org.example.Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise4 {

    public static class Task implements Runnable {
        int task;

        public Task(int task) {
            this.task = task;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() );
        }
    }

    public static void main(String[] args) {
        int numCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number  cores  " + numCores);

        ExecutorService e = Executors.newFixedThreadPool(numCores);

        for (int i = 0; i < numCores; i++) {
            e.submit(new Task(i));

        }

        // Shutdown the executor service after all tasks are completed
        e.shutdown();
    }
}
