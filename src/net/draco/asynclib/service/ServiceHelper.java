package net.draco.asynclib.service;

import android.content.Context;
import android.content.Intent;

/**
 * Support class for the LocalService.
 * 
 * @author Francesco Pontillo
 *
 */
public class ServiceHelper<T extends ServiceBinder> {
	/**
	 * Connection to the service for executing the methods exposed by its binder
	 */
	private MethodServiceConnection<T> mConnection = null;
	
	/**
	 * Creates the LocalService which exposes the callable methods, then executes the binding.
	 * If the service had already been started, it only binds to it.
	 * 
	 * @param mContext Context to bind from.
	 * @param serviceClass Class extended from LocalService
	 * @return MethodServiceConnection Connection, useful for executing the methods exposed by the LocalService
	 */
	public MethodServiceConnection<T> connectToService(
			Context mContext,
			Class<? extends LocalService> localServiceClass,
			IServiceConnectionListener listener) {
		mConnection = new MethodServiceConnection<T>(listener);
		mContext.bindService(
				new Intent(mContext, localServiceClass),
				mConnection,
				Context.BIND_AUTO_CREATE);
		return mConnection;
	}
}
