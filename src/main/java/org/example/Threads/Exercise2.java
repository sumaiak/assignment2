package org.example.Threads;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Formattable;
import java.util.List;
import java.util.concurrent.*;

public class Exercise2 {
    private static class Counter {
        private int count = 0;

        // Method to increment the count, synchronized to ensure thread safety
        public synchronized void increment() {
            count++;
        }

        // Method to retrieve the current count value
        public int getCount() {
            return count;

        }
        public static void main (String[] args) throws Exception{
            Counter c = new Counter();
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i =1; i<= 1000;i++){
                        c.increment();
                        System.out.println(i);
                    }

                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i =1; i<= 1000;i++){
                        c.increment();
                    }

                }

            });
         thread1.start();
         thread2.start();

         thread1.join();
         thread2.join();
         System.out.println("Count: " + c.getCount());



        }




    }
}