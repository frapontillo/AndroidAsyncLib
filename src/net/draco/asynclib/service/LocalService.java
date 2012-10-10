/***
	Copyright (c) 2010-2011 CommonsWare, LLC
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package net.draco.asynclib.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * LocalService class with a custom binder interaction.
 * 
 * @author CommonsWare
 * @author Francesco Pontillo
 */
public class LocalService extends Service {
	private ServiceBinder mBinder;
	
	public LocalService() {
		super();
		mBinder = getBinder();
	}
	
	/**
	 * To be overridden by returning the real binder for this service.
	 * @return ServiceBinder extension with the actual methods to be exposed
	 */
	public ServiceBinder getBinder() {
		return new ServiceBinder();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mBinder.onCreate();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return(mBinder);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mBinder.onDestroy();		
	}
}