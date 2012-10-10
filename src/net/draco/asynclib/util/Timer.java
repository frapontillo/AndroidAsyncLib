package net.draco.asynclib.util;

import android.os.SystemClock;

/**
 * Util class to handle timers.
 * 
 * @author Francesco Pontillo
 *
 */
public class Timer {
	float startTime = 0;
	float stopTime = 0;
	float duration = 0;
	
	public Timer() {
		startTime = 0;
		stopTime = 0;
		duration = 0;
	}
	
	/**
	 * Starts the timer.
	 * 
	 * @return a number representing the thread milliseconds
	 */
	public float startTimer() {
		startTime = SystemClock.currentThreadTimeMillis();
		return startTime;
	}
	
	/**
	 * Stops the timer.
	 * 
	 * @return a number representing the thread milliseconds
	 */
	public float stopTimer() {
		stopTime = SystemClock.currentThreadTimeMillis();
		return stopTime;
	}
	
	/**
	 * Gets the calculated elapsed time since <a>startTimer()</a> was called.
	 * @return a number representing the elapsed time (in milliseconds)
	 */
	public float getDuration() {
		duration = stopTime - startTime;
		return duration;
	}
}
