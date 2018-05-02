package com.test.algos.sort;

public class QuickSort {
	 private int array[];
	
    private void exchange(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
