/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\workspace\\RemoteBindDemo\\src\\com\\demo\\bindservice\\IMyCountBeanService.aidl
 */
package com.demo.bindservice;
public interface IMyCountBeanService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.demo.bindservice.IMyCountBeanService
{
private static final java.lang.String DESCRIPTOR = "com.demo.bindservice.IMyCountBeanService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.demo.bindservice.IMyCountBeanService interface,
 * generating a proxy if needed.
 */
public static com.demo.bindservice.IMyCountBeanService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.demo.bindservice.IMyCountBeanService))) {
return ((com.demo.bindservice.IMyCountBeanService)iin);
}
return new com.demo.bindservice.IMyCountBeanService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getCount:
{
data.enforceInterface(DESCRIPTOR);
com.demo.bindservice.CountBean _result = this.getCount();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.demo.bindservice.IMyCountBeanService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public com.demo.bindservice.CountBean getCount() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.demo.bindservice.CountBean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCount, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.demo.bindservice.CountBean.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public com.demo.bindservice.CountBean getCount() throws android.os.RemoteException;
}
