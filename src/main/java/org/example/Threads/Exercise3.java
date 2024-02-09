package org.example.Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
//har brugt Atomic integer

public class Exercise3 {
    public static void main(String[] args) {
        ExecutorService workingJack = Executors.newFixedThreadPool(17);
        System.out.println("Main starts");
        IntegerList integerList = new IntegerList();
        AtomicInteger counter = new AtomicInteger(0);
        for (int count = 0; count < 1000; count++) {
            workingJack.submit(new TaskToAddCount(integerList, counter.incrementAndGet()));
        }
        workingJack.shutdown();

        try {
            workingJack.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
        System.out.println("Main is done");
    }

    private static class IntegerList {
        private static List<Integer> list = new ArrayList<>();

        public void addCount(int count) {
            list.add(count);
            System.out.println("Task: " + "count:" + count + " thread name :" + Thread.currentThread().getName() + "" + ": List size = " + list.size());
        }
    }

    private static class TaskToAddCount implements Runnable {
        private IntegerList integerList;
        private int count;

        TaskToAddCount(IntegerList integerList, int count) {
            this.integerList = integerList;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int) (Math.random() * 800 + 200));
                integerList.addCount(count);
            } catch (InterruptedException ex) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}
