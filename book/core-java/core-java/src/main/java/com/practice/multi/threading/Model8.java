package com.practice.multi.threading;

import lombok.Getter;

public class Model8 {

	@Getter
	private volatile long count = 0;

	public void increment() {
		count++;
	}
}
