package core;

import core.impl.QuickPrimeCalculator;
import core.impl.ReduceLoopCountPrimeCalculator;

public class ExecutePrimeSum {
    public static void main(String[] args){
        PrimeCalculator primeCalculator = new QuickPrimeCalculator();
        long startTime = System.currentTimeMillis();
        System.out.println("----- quickPrimeCalculator sum----- : " + primeCalculator.sum(200000));
        System.out.println("----- quickPrimeCalculator time---- : " + (System.currentTimeMillis() - startTime) + "ms");

        primeCalculator = new ReduceLoopCountPrimeCalculator();
        startTime = System.currentTimeMillis();
        System.out.println("----- ReduceLoopCountPrimeCalculator sum----- : " + primeCalculator.sum(200000));
        System.out.println("----- ReduceLoopCountPrimeCalculator time---- : " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
