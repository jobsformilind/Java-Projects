package com.test.topjavatutorial.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysAsList {

	/*
	 * Output: [3,10,3], followed by exception Arrays.asList() returns a fixed-size list
	 * backed by the specified array. Therefore, the ArrayList can't grow. So,
	 * when add() is called, an exception is thrown.
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		Integer[] arr = { 2, 10, 3 };
		list = Arrays.asList(arr);
		list.set(0, 3);
		System.out.println(list);
		list.add(1);
		System.out.println(list);
	}

}