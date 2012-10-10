package net.draco.asynclib.androidasyncapp.test;

import net.draco.asynclib.androidasyncapp.MyLocalService;
import net.draco.asynclib.androidasyncapp.MyServiceBinder;
import net.draco.asynclib.service.MethodServiceConnection;
import net.draco.asynclib.service.ServiceHelper;
import net.draco.asynclib.service.IServiceConnectionListener;

import android.test.AndroidTestCase;

public class AndroidAsyncLibTest extends AndroidTestCase implements IServiceConnectionListener {

	public AndroidAsyncLibTest() {
		super();
	}

	MethodServiceConnection<MyServiceBinder> mConn;
    int n1 = 345;
    int n2 = 23;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ServiceHelper<MyServiceBinder> h = new ServiceHelper<MyServiceBinder>();
		mConn = h.connectToService(getContext(), MyLocalService.class, this);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	private void sum() {
        assertEquals(368, mConn.serviceBinder.add(n1, n2));
    }
	
    private void subtract() {
        assertEquals(322, mConn.serviceBinder.subtract(n1, n2));
    }

	@Override
	public void onServiceConnected() {
		sum();
		subtract();
	}
}
