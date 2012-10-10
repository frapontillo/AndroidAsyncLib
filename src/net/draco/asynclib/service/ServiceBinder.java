package net.draco.asynclib.service;

import android.os.Binder;
import android.util.Log;

/**
 * Binder whose implementations have to expose the methods to be called by the service.
 * 
 * @author Francesco Pontillo
 *
 */
public class ServiceBinder extends Binder {
	private final static String TAG = "ServiceBinder";
	
	public void onCreate() {
		Log.i(TAG, "Binder created.");
	}
	
	public void onDestroy() {
		Log.i(TAG, "Binder destroyed.");
	}	
}
