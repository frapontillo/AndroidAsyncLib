package net.draco.asynclib.androidasyncapp;

import net.draco.asynclib.service.ServiceBinder;

/**
 * Example binder which exposes some methods for a service.
 * @author Francesco Pontillo
 *
 */
public class MyServiceBinder extends ServiceBinder {
	/**
	 * Adds a couple of numbers.
	 * @param n1
	 * @param n2
	 * @return
	 */
	public int add(int n1, int n2) {
		return n1+n2;
	}
	
	/**
	 * Subtracts one number from another one.
	 * @param n1
	 * @param n2
	 * @return
	 */
	public int subtract(int n1, int n2) {
		return n1-n2;
	}
}
