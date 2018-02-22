//author: wangjf
package com.qiyi.hwmessenger;

import android.os.IBinder;
import android.os.ServiceManager;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public final class  HWMessenger{
    private static final String TAG = "HWMessenger";
    private static final String EXT = "[JAVA] ";
    private static HWMessenger mMessenger;

    int REGISTER_CALLBACK = IBinder.FIRST_CALL_TRANSACTION;
    int UNREGISTER_CALLBACK = IBinder.FIRST_CALL_TRANSACTION + 1;
    int CREATE_HW_CONTROLLER_CLIENT = IBinder.FIRST_CALL_TRANSACTION + 2;

    private HWMessenger() {
    }

    public static HWMessenger getInstance() {
        synchronized (HWMessenger.class) {
            if (mMessenger == null) {
                mMessenger = new HWMessenger();
            }
            return mMessenger;
        }
    }
    public int registerCallback(IHWMessengerCallback callback){
        try{
            IBinder flinger = ServiceManager.getService("HWMessenger");
            if (flinger != null) {
                Parcel data = Parcel.obtain();
                data.writeInterfaceToken("com.qiyi.hwmessenger.IHWMessenger");
                data.writeStrongBinder(callback.asBinder());
                flinger.transact(REGISTER_CALLBACK, data, null, 0);
                Log.d(TAG, EXT + "register callback ");
                data.recycle();
            }
        } catch (RemoteException ex) {
        }
        return 0;
    }
    public int unregisterCallback(IHWMessengerCallback callback){
        try{
            IBinder flinger = ServiceManager.getService("HWMessenger");
            if (flinger != null) {
                Parcel data = Parcel.obtain();
                data.writeInterfaceToken("com.qiyi.hwmessenger.IHWMessenger");
                data.writeStrongBinder(callback.asBinder());
                flinger.transact(UNREGISTER_CALLBACK, data, null, 0);
                Log.d(TAG, EXT + "unregister callback ");
                data.recycle();
            }
        } catch (RemoteException ex) {
        }
        return 0;
    }
    public IHWControllerClient createHWControllerClient(){
        IHWControllerClient client = null;
        try{
            IBinder flinger = ServiceManager.getService("HWMessenger");
            if (flinger != null) {
                Parcel data = Parcel.obtain();
                data.writeInterfaceToken("com.qiyi.hwmessenger.IHWMessenger");
                Parcel reply = Parcel.obtain();
                flinger.transact(CREATE_HW_CONTROLLER_CLIENT, data, reply, 0);
                client =  IHWControllerClient.Stub.asInterface(reply.readStrongBinder());
                Log.d(TAG, EXT + "createHWControllerClient ");
                data.recycle();
            }
        } catch (RemoteException ex) {
        }
        return client;
    }




}
