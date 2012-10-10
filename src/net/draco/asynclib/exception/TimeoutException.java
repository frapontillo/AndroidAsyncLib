package net.draco.asynclib.exception;

/**
 * Exception class which handles timeout exceptions in methods.
 * 
 * @author Francesco Pontillo
 *
 */
public class TimeoutException extends MethodException {
	private float timeout;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return "Timeout error (" + getTimeout() + "ms)! Aborted.";
	}
	
	/**
	 * Constructor, sets the occurred timeout.
	 * @param timeout time in milliseconds after which the timeout occurred
	 */
	public TimeoutException(float timeout) {
		this.timeout = timeout;
	}

	/**
	 * Returns the timeout occurred during a method execution.
	 * 
	 * @return float, time in milliseconds after which the timeout occurred
	 */
	public float getTimeout() {
		return timeout;
	}
}
