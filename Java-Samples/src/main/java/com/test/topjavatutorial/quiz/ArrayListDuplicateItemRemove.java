package com.test.topjavatutorial.quiz;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDuplicateItemRemove {

	/**
	 * Output: 21
	 * ArralyList can contain duplicate elements. ArrayList remove()
	 * method only removes the first occurrence of a matching element.
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(10);

		System.out.print(list.size());
		list.remove(new Integer(10));
		System.out.print(list.size());
	}

}
