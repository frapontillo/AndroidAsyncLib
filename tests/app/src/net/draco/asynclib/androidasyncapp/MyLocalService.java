package net.draco.asynclib.androidasyncapp;

import net.draco.asynclib.service.*;

/**
 * Example service which exposes some methods from a binder.
 * @author Francesco Pontillo
 *
 */
public class MyLocalService extends LocalService {
	/**
	 * Returns a custom implementation of the binder.
	 */
	@Override
	public ServiceBinder getBinder() {
		return new MyServiceBinder();
	}
}
