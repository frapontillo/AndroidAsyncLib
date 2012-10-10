package net.draco.asynclib.exception;

/**
 * Abstract class which all of the methods' exceptions must inherit from.
 * 
 * @author Francesco Pontillo
 *
 */
public abstract class MethodException extends Exception {
	public abstract String getMessage();
}
