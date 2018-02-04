package com.test.algos.sort;

import java.util.Arrays;

// O(n) 	- (Best case)
// O(n^2) 	- (Worst case)
public class BubbleSort {

	public static void main(String[] args) {
		int[] intArray = new int[] { 95, 35, 56, 47, 38 };
		bubbleSort(intArray);
	}

	public static void bubbleSort(int[] numbers) {
		System.out.println("Before: " + Arrays.toString(numbers));
		boolean flag = true;
		int counter;
		int temp;

		while (flag) {
			flag = false;
			for (counter = 0; counter < numbers.length - 1; counter++) {
				if (numbers[counter] < numbers[counter + 1]) {
					temp = numbers[counter];
					numbers[counter] = numbers[counter + 1];
					numbers[counter + 1] = temp;
					flag = true;
				}
			}
		}
		System.out.println("After: " + Arrays.toString(numbers));
	}
}
