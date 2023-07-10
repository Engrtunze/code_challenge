package com.seerbit_assesement.code_challenge.algorithmSolutions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.seerbit_assesement.code_challenge.algorithmSolutions.AlgorithmSolutions.numberRange;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class AlgorithmSolutionsTest {
    @Test
    void testHasTwoSum() {
        int[] nums = {1, 4, 6, 8, 9};
        int targetValue = 15;

        boolean result = AlgorithmSolutions.sumTwoNumbers(nums, targetValue);

        Assertions.assertTrue(result);
    }

    @Test
    void testFindRange() {
        int[] nums = {1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5};
        int targetValue = 3;

        int[] result = numberRange(nums, targetValue);

        int[] expected = {3, 5};
        assertArrayEquals(expected, result);
    }
}
