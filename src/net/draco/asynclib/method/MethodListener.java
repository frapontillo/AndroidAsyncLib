package net.draco.asynclib.method;

import android.util.Log;

import net.draco.asynclib.exception.TimeoutException;

/**
 * Abstract class that defines a common interface for any listener
 * to be used in <a>Method</a>s.
 * 
 * @author Francesco Pontillo
 *
 */
public abstract class MethodListener {
	private Method method = null;
	
	/**
	 * Sets the <a>Method</a> for this listener.
	 * 
	 * @param attachedMethod <a>Method</a> for the listener
	 */
	public void setMethod(Method attachedMethod) {
		method = attachedMethod;
	}
	
	/**
	 * Gets the <a>Method</a> for this listener.
	 * 
	 * @return <a>Method</a> for the listener
	 */
	public Method getMethod() {
		return method;
	}
	
	/**
	 * Called when there's a returned result.
	 * 
	 * @param object Returned value
	 */
	public void onResult(Object object) {
		try {
			Log.i(this.getClass().getName(), "Operation completed in " + method.getExecutionTime() + "ms with result:\n" + object.toString());
		} catch (Exception e) {
			
		}
	}
	/**
	 * Called when the operation is stopped.
	 */
	public void onOperationCancelled() {
		Log.w(this.getClass().getName(), "Operation interrupted by user.");
	}
	
	/**
	 * Called when there's been an error in the execution of the method.
	 * @param exception Exception raised
	 */
	public void onError(Exception exception) {
		Log.e(this.getClass().getName(), "Error during the operation!\n" + exception.getMessage());
	}
	
	/**
	 * Called when the method has a timeout exception.
	 * @param timeoutException the <a>TimeoutException</a> thrown
	 */
	public void onTimeout(TimeoutException timeoutException) {
		Log.e(this.getClass().getName(), "Operation timeout!");
	}
	
	/**
	 * Method to be overwritten in order to execute something after the call has started.
	 */
	public void onStarted() {
		Log.i(this.getClass().getName(), "Method started");
	}
}
