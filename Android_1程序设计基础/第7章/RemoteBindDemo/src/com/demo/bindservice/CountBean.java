package com.demo.bindservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CountBean implements Parcelable {

	public static final Parcelable.Creator<CountBean> CREATOR = new Creator<CountBean>() {
		@Override
		public CountBean createFromParcel(Parcel source) {
			CountBean bean = new CountBean();
			bean.count = source.readInt();
			return bean;
		}

		@Override
		public CountBean[] newArray(int size) {
			return new CountBean[size];
		}
	};

	public CountBean() {

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int count;

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.count);
	}

	@Override
	public int describeContents() {
		return 0;
	}
}
