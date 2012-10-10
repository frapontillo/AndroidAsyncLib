package net.draco.asynclib.service;

import android.content.Context;
import android.content.Intent;

/**
 * Support class for the LocalService.
 * 
 * @author Francesco Pontillo
 *
 */
public class ServiceHelper {
	/**
	 * Connection to the service for executing the methods exposed by its binder
	 */
	private static MethodServiceConnection mConnection = null;
	
	/**
	 * Creates the LocalService which exposes the calleable methods, then executes the binding.
	 * If the service had already been started, it only binds to it.
	 * 
	 * @param mContext Context to bind from.
	 * @return MethodServiceConnection Connection, useful for executing the methods exposed by the LocalService
	 */
	public static MethodServiceConnection connectToService(Context mContext) {
		mConnection = new MethodServiceConnection();
		mContext.bindService(new Intent(mContext, LocalService.class), mConnection, Context.BIND_AUTO_CREATE);
		return mConnection;
	}
}
