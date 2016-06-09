package com.test.exception;

public class ExceptionFinallyReturnTest {
	public static void main(String[] args) {
		// test();
		System.out.println(test());
	}

	@SuppressWarnings("finally")
	public static int test() {
		try {
			System.out.println("inside try...");
			throw new NullPointerException("NPE Occured...");
		} catch (Exception ex) {
			System.out.println("inside catch...");
			return 1;
		} finally {
			System.out.println("inside finally...");
			return 2;
		}
	}
}
