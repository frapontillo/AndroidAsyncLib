package net.draco.asynclib.service;

// TODO: Removed, for now
// import com.itasa.droitasa.ServiceFragmentActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * ServiceConnection implementation for handling a Service.
 * 
 * @author Francesco Pontillo
 *
 */
public class MethodServiceConnection implements ServiceConnection {
	public ServiceBinder serviceBinder = null;
	// TODO: Removed, for now
	// public ServiceFragmentActivity serviceFragmentActivity = null;
	private boolean isConnected = false;

	// TODO: Removed, for now
	/*
	public MethodServiceConnection(ServiceFragmentActivity serviceFragmentActivity) {
		this.serviceFragmentActivity = serviceFragmentActivity;
	}
	*/
	
	@Override
	public void onServiceConnected(ComponentName className, IBinder rawBinder) {
		serviceBinder = (ServiceBinder)rawBinder;
		isConnected = true;
		Log.i("MethodServiceConnection", "Service connected!");
		performOnConnection();
		// TODO: Removed, for now
		// serviceFragmentActivity.onServiceConnected();
	}

	@Override
	public void onServiceDisconnected(ComponentName className) {
		Log.i("MethodServiceConnection", "Service disconnected!");
		isConnected = false;
		serviceBinder = null;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public void performOnConnection() {};
}
