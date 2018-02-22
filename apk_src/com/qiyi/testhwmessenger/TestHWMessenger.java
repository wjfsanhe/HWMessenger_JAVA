package com.qiyi.testhwmessenger ;

import com.qiyi.hwmessenger.HWMessenger;
import com.qiyi.hwmessenger.IHWMessengerCallback;
import com.qiyi.hwmessenger.IHWControllerClient;
import android.os.IBinder;
import android.util.Log;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;




public final class TestHWMessenger {
static final String TAG = "HWMessenger" ;
static final String EXT = "[TEST] " ;

private class TestCallback extends IHWMessengerCallback.Stub
    {
        public void onKey(String deviceName, int keyCode, int value, int flags) throws android.os.RemoteException
        {
            Log.d(TAG,EXT + "device[" + deviceName + "]" + "----" + keyCode + ", " + value + ", " + flags );
        }
        @Override
        public IBinder asBinder() {
            return this;
        }

    }

public void run(String args) {
    try {
        HWMessenger  mMessenger=HWMessenger.getInstance();
        TestCallback callback = new TestCallback();
        int ret = mMessenger.registerCallback(callback);
        Log.d(TAG,EXT + "ret = " + ret);
        IHWControllerClient client = mMessenger.createHWControllerClient();
        if (client == null) {
            Log.d(TAG,EXT + "binder is null ");
            return ;
        }
        String result = client.readSysfs(new String("/sys/class/graphics/fb0/mod"));
        Log.d(TAG,EXT + "result: " + result);
        result = client.readSysfs(new String("/sys/class/graphics/fb0/modes"));
        Log.d(TAG,EXT + "result: " + result);

        result = client.writeSysfs(new String("/sys/class/leds/red/brightness"), new String("0"));
        Log.d(TAG,EXT + ": led brightness : " + result);
        result = client.writeSysfs(new String("/sys/class/leds/red/brightness"), new String("1"));
        Log.d(TAG,EXT + ": led brightness : " + result);
        System.out.println(TAG+": Start send message");
    } catch (RemoteException ex) {
    }
}

public static void main(String[] args) {
    TestHWMessenger test = new TestHWMessenger();
    test.run("test");
    while (true) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

}
