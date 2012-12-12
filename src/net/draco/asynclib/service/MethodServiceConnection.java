package net.draco.asynclib.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * ServiceConnection implementation for handling a Service.
 *
 * @author Francesco Pontillo
 */
public class MethodServiceConnection<T extends ServiceBinder> implements ServiceConnection {
    public T serviceBinder = null;
    private boolean isConnected = false;
    private IServiceConnectionListener mListener = null;

    public MethodServiceConnection(IServiceConnectionListener listener) {
        this.mListener = listener;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onServiceConnected(ComponentName className, IBinder rawBinder) {
        serviceBinder = (T) rawBinder;
        isConnected = true;
        Log.i("MethodServiceConnection", "Service connected!");
        // Calls the connection callback
        if (mListener != null) mListener.onServiceConnected();
        // Random code for overridden method service connections
        performOnConnection();
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

    public void performOnConnection() {}
}
