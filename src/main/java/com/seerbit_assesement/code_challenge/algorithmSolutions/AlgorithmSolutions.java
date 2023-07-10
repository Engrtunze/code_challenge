package com.seerbit_assesement.code_challenge.algorithmSolutions;

import java.util.HashSet;
import java.util.Set;

public class AlgorithmSolutions {

    private AlgorithmSolutions(){}
    public static boolean sumTwoNumbers(int[] numbers, int targetValue) {
        Set<Integer> targetSum = new HashSet<>();

        for (int num : numbers) {
            int target = targetValue - num;
            if (targetSum.contains(target)) {
                return true;
            }
            targetSum.add(num);
        }

        return false;
    }

    public static int[] numberRange(int[] numbers, int targetValue) {
        int high = -1;
        int left = 0;
        int right = numbers.length - 1;
        int low = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (numbers[mid] == targetValue) {
                low = mid;
                high = mid;
                break;
            } else if (numbers[mid] < targetValue) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (low != -1) {
            while (low > 0 && numbers[low - 1] == targetValue) {
                low--;
            }
            while (high < numbers.length - 1 && numbers[high + 1] == targetValue) {
                high++;
            }
        }

        return new int[]{low, high};
    }


}
