package core.impl;

import core.PrimeCalculator;

/**
 * This class will reduce the number of loops
 */
public class ReduceLoopCountPrimeCalculator implements PrimeCalculator {
    @Override
    public int sum(int number) {
        int sum = 2 + 3 + 5 + 7;
        // eliminate even Numbers, so the length is less than half
        int[] arr = new int[number / 2];
        int count = 0;
        // eliminate numbers that are divisible by 2/3/5/7
        // After filtering out these divisible Numbers, the number of loops will be greatly reduced
        for(int i = 8; i <= number; i++){
            boolean flag = true;
            if(i % 2 == 0 || i % 3 == 0 || i % 5 == 0 || i % 7 == 0){
                flag = false;
            }
            if(flag){
                arr[count] = i;
                count++;
            }
        }
        // Iterate over the new array
        for(int i = 0; i < count; i++){
            boolean flag = true;
            for (int j = 9; j <= Math.sqrt(arr[i]); j += 2) {
                if (0 == (arr[i]) % j) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sum += arr[i];
            }
        }
        return sum;
    }
}
