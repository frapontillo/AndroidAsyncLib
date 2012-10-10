package net.draco.asynclib.exception;

/**
 * Exception for no exception.
 * 
 * @author Francesco Pontillo
 *
 */
public class NoException extends MethodException {
	@Override
	public String getMessage() {
		return "No Exception.";
	}
}
