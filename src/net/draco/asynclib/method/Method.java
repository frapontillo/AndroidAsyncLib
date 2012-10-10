package net.draco.asynclib.method;

import net.draco.asynclib.exception.MethodException;
import net.draco.asynclib.exception.NoException;
import net.draco.asynclib.exception.NoListenerAttached;
import net.draco.asynclib.exception.TimeoutException;
import net.draco.asynclib.util.Timer;

/**
 * Superclass for all of the methods.
 * Each real method will have to reimplement some functions.
 * The standard behavior for calling a method is:
 * <code>
 * 		RealListener listener = new RealListener();
 * 		Method m = new RealMethod(listener);
 * 		m.start(params);
 * </code>
 * When the calls is completed, the appropriate listener methods
 * will be called.
 * 
 * @author Francesco Pontillo
 *
 */
public abstract class Method {
	boolean isStopped;
	MethodListener listener = null;
	Object result = null;
	boolean isTerminated;
	protected Timer timer = null;
	
	/**
	 * The only function which HAS to be implemented, it executes
	 * the different operation requested for the Method.
	 * Reimplement this method with real parameters and a real output.
	 * It can't be called directly but only by using <a>start(params)</a>.
	 * 
	 * @param params Input parameters
	 * @throws Exception In case of some exception
	 */
	protected abstract Object executeOperation(Object... params) throws Exception;
	
	/**
	 * Metodo che controlla alcune precondizioni.
	 * Nella sua implementazione standard non controlla nulla.
	 * Se si vuole ridefinire il metodo, eseguire alcuni controlli personalizzati,
	 * quindi restituire una particolare eccezione di classe MethodExcepion.
	 * 
	 * @return returnedException A <a>MethodException</a>
	 */
	private MethodException checkForPreconditions() {
		MethodException returnedException = new NoException();
		return returnedException;
	}
	
	/**
	 * Constructor, it initializes the timer and the method itself.
	 */
	public Method() {
		timer = new Timer();
		isStopped = false;
		isTerminated = false;
	}
	
	/**
	 * Constructor, it takes a <a>MethodListener</a> as input.
	 * 
	 * @param listener <a>MethodListener</a> which will handle the callbacks.
	 */
	public Method(MethodListener listener) {
		this();
		setMethodListener(listener);
	}
	
	/**
	 * Sets the current listener, whose methods will be called
	 * during the several phases of the execution.
	 * 
	 * @param listener <a>MethodListener</a> which will handle the callbacks.
	 */
	public void setMethodListener(MethodListener listener) {
		this.listener = listener;
		this.listener.setMethod(this);
	}
	
	/**
	 * Starts the real execution of the method.
	 *
	 * @param params List of parameters
	 * @throws Exception in case of an exception
	 */
	public void start(Object... params) throws Exception {
		onPreExecution();
		timer.startTimer();
		try {
			result = executeOperation(params);
		} catch (Exception e) {
			result = e;
		}
		timer.stopTimer();
		onPostExecution(result);
	}
	
	/**
	 * To be called to avoid the returning of the result to the caller.
	 */
	public void stop() {
		isStopped = true;
		onPostExecution(null);
	}
	
	/**
	 * Returns the execution time of the method.
	 * If the method hasn't returned a result yet,
	 * the execution time will be different for each call.
	 * 
	 * @return float representing the elapsed time since the method started (in milliseconds)
	 */
	public float getExecutionTime() {
		try {
			return timer.getDuration();
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Checks for all of the preconditions before starting the method.
	 * @throws MethodException in case of an exception
	 */
	protected void onPreExecution() throws MethodException {
		// If there's no listener attached, throw a proper Exception
		if (listener == null) {
			throw new NoListenerAttached();
		}
		// If the method has already been stopped, let's not execute it at all
		if (isStopped) {
			onPostExecution(null);
		}
		// In the end, let's check for other custom preconditions
		MethodException methodException = checkForPreconditions();
		if ((methodException != null) && (!(methodException instanceof NoException))) {
			throw methodException;
		}
		listener.onStarted();
	}
	
	/**
	 * Called when the result is returned.
	 * @param result Value returned from the method execution
	 */
	protected void onPostExecution(Object result) {
		if (!isTerminated) {
			isTerminated = true;
			if (isStopped) {
				listener.onOperationCancelled();
			}
			else if (result instanceof NoException) {
				listener.onResult(result);
			}
			else if (result instanceof TimeoutException) {
				listener.onTimeout((TimeoutException)result);
			}
			else if (result instanceof Exception) {
				listener.onError((Exception)result);
			}
			else {
				listener.onResult(result);
			}
		}
	}
	
}
