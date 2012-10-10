package net.draco.asynclib.method;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;

import net.draco.asynclib.exception.NoException;
import net.draco.asynclib.util.Timer;

/**
 * Specialized class of <a>Method</a>, it executes all of the
 * operations in an asynchronous thread, allowing the calling
 * thread not to block.
 * 
 * @author Francesco Pontillo
 *
 */
public abstract class AsyncMethod extends Method {
	
	private MethodAsyncTask curTask = null;
	private TimeoutAsyncTask curTimeoutTask = null;
	private float timeout = 0;
	
	Method curMethod = this;
	
	/**
	 * Constructor, sets the method timeout.
	 * 
	 * @param timeout float, timeout (in milliseconds) for this method
	 */
	public AsyncMethod(float timeout) {
		super();
		setTimeout(timeout);
	}
	
	public AsyncMethod() {
		super();
		setTimeout(0);
	}

	/**
	 * Executes the method operation asynchronously.
	 *
	 * @param params List of input parameters
	 * @throws Exception thrown
	 */
	public void start(Object... params) throws Exception {
		onPreExecution();
		curTask = new MethodAsyncTask();
		curTask.execute(params);
		// Creo un task che si occupa di gestire un eventuale timeout
		curTimeoutTask = new TimeoutAsyncTask();
		curTimeoutTask.execute();
	}
	
	/**
	 * Starts the asynchronous method in a synchronous (blocking) way.
	 * It is useful for doing some things in an ordered way.
	 * 
	 * @param params Input parameters
	 * @return Object Returned value from the method
	 * @throws Exception
	 */
	public Object startAsBlockingMethod(Object... params) throws Exception {
		return this.executeOperation(params);
	}
	
	/**
	 * Sets the timeout for the current method.
	 * 
	 * @param timeout float, timeout (in milliseconds) for this method
	 */
	public void setTimeout(float timeout) {
		if (timeout > 0) {
			this.timeout = timeout;
		}
	}
	
	protected class MethodAsyncTask extends AsyncTask<Object, Void, Object> {
		
		public MethodAsyncTask() {
			timer = new Timer();
		}
		
		@Override
	    protected Object doInBackground(Object... params) {
			timer.startTimer();
			// Wait for the debugger
			// Un-comment the following line if you'd like to debug AsyncTasks
	    	// android.os.Debug.waitForDebugger();
			// Before deploying the app, remove/comment the previous line because of performance issues.
			try {
				result = executeOperation(params);
			} catch (Exception e) {
				result = e;
			}
			timer.stopTimer();
			return result;
	    }
		
		@Override
		protected void onPostExecute(Object result) {			
			onPostExecution(result);
		}
	}
	
	protected class TimeoutAsyncTask extends AsyncTask<Void, Void, Exception> {

		@Override
		protected Exception doInBackground(Void... params) {
			try {
				if (timeout > 0)
					curTask.get((long) timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				return e;
			} catch (ExecutionException e) {
				return e;
			} catch (java.util.concurrent.TimeoutException e) {
				return new net.draco.asynclib.exception.TimeoutException(timeout);
			}
			
			return new NoException();
		}
		
		@Override
		protected void onPostExecute(Exception result) {
			// If there was a timeout
			// let's call the postExecution method
			if (!(result instanceof NoException))
				curMethod.onPostExecution(result);
			super.onPostExecute(result);
		}
	}
	
}

