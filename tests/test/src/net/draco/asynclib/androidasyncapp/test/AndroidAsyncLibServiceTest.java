package net.draco.asynclib.androidasyncapp.test;

import net.draco.asynclib.androidasyncapp.MyLocalService;
import net.draco.asynclib.androidasyncapp.MyServiceBinder;
import android.content.Intent;
import android.test.ServiceTestCase;

public class AndroidAsyncLibServiceTest extends ServiceTestCase<MyLocalService> {
    int n1 = 345;
    int n2 = 23;
    
	public AndroidAsyncLibServiceTest() {
		super(MyLocalService.class);
	}
	
	public AndroidAsyncLibServiceTest(Class<MyLocalService> serviceClass) {
		super(serviceClass);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		startService(new Intent(mContext, MyLocalService.class));
	}
	
	public void testSum() {
        assertEquals(368, ((MyServiceBinder) getService().getBinder()).add(n1, n2));
    }
	
	public void testSubtract() {
        assertEquals(322, ((MyServiceBinder) getService().getBinder()).subtract(n1, n2));
    }

}
