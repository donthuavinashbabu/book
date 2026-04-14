package com.practice.multi.threading;

import lombok.Getter;

public class Model7 {

	@Getter
	private long count;

	public void increment() {
		synchronized (this) {
			count++;
		}
	}
}
