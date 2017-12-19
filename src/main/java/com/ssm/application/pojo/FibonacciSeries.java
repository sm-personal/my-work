package com.ssm.application.pojo;

public class FibonacciSeries {
	private int[] fibonacciSeries;

	public FibonacciSeries(final int[] fibonacciSeries) {
		super();
		this.fibonacciSeries = fibonacciSeries;
	}


	public int[] getFibonacciSeries() {
		return fibonacciSeries;
	}

	public void setFibonacciSeries(int[] fibonacciSeries) {
		this.fibonacciSeries = fibonacciSeries;
	}
}
