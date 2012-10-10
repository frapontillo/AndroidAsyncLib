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
	
/*	public Method getByUrl(String Url, MethodListener listener, Entity updateThis) {
		GetByUrlMethod method = new GetByUrlMethod();
		method.setMethodListener(listener);
		try {
			method.start(Url, updateThis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return method;
	}
	
	public Method login(String username, String password, MethodListener listener, User updateThis) {
		LoginMethod method = new LoginMethod();
		method.setMethodListener(listener);
		try {
			method.start(username, password, updateThis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return method;
	}*/
	
}
