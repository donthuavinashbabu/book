package com.practice.multi.threading;

import lombok.Getter;

/**
 * Model to replicate critical section
 * @see Synchronized#criticalSectionExample()
 *
 */
public class Model1 {

	@Getter
	private long count = 0;

	public void increment() {
		count++;
	}

}
