package core.impl;

import core.PrimeCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is a quick way to sum prime numbers
 */
public class QuickPrimeCalculator implements PrimeCalculator {
    private static int threadPoolSize = 10; // the number of thread pools
    private static int numberParts = 10; // The number of data segments
    private static AtomicInteger value = new AtomicInteger(0);
    /**
     * Determine if the number is prime
     *
     * @param number
     * @return
     */
    public boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number) ; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * The sum of primes in the interval
     * @param start
     * @param end
     * @return
     */
    public int getPrimeNums(int start,int end) {
        for(int i = start; i <= end; i++) {
            if(isPrimeNumber(i)) {
                value.addAndGet(i);
            }
        }
        return value.get();
    }

    /**
     * calculate the sum of prime numbers
     * @param number
     * @return
     */
    public int sum(int number){
        int nums = number/numberParts;
        List<Callable<Integer>> callableList = new ArrayList<Callable<Integer>>();
        for(int i = 0; i < numberParts; i++) {
            // Calculate the start and end coordinates of the interval Numbers
            final int start = i * nums + 1;
            final int end = (numberParts - i == 1) ? number : start + nums - 1;
            callableList.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    // each task process executes a sum of prime Numbers for the corresponding interval
                    return getPrimeNums(start,end);
                }
            });
        }
        ExecutorService executor= Executors.newFixedThreadPool(threadPoolSize);
        try {
            // batch execute task, task failed over 50 seconds
            executor.invokeAll(callableList,50, TimeUnit.SECONDS );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Close the thread pool
        executor.shutdown();
        return value.get();
    }
}
