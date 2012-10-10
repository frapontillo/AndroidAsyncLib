package net.draco.asynclib.exception;

/**
 * Exception class for returning when no listener is attached to a Method,
 * which is still started afterwards.
 * 
 * @author Francesco Pontillo
 *
 */
public class NoListenerAttached extends MethodException {
	@Override
	public String getMessage() {
		return "No listener attached to method. Method won't work with no listener attached to it.";
	}
}
