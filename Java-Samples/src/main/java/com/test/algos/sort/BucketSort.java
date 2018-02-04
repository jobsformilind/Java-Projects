package com.test.algos.sort;

import java.util.Arrays;

public class BucketSort {
	
	public static void main(String[] args) {
		int maxVal = 5;
		int[] data = { 85, 73, 10, 24, 64, 71, 90, 35, 62, 33, 31, 46};

		System.out.println("Before: " + Arrays.toString(data));
		bucketSort(data, maxVal);
		System.out.println("After:  " + Arrays.toString(data));
	}

	public static void bucketSort(int[] numbers, int maxVal) {
		int[] bucket = new int[maxVal + 1];

		for (int cnt = 0; cnt < bucket.length; cnt++) {
			bucket[cnt] = 0;
		}

		for (int cnt = 0; cnt < numbers.length; cnt++) {
			bucket[numbers[cnt]]++;
		}

		int outPos = 0;
		for (int cnt = 0; cnt < bucket.length; cnt++) {
			for (int j = 0; j < bucket[cnt]; j++) {
				numbers[outPos++] = cnt;
			}
		}
	}
}
