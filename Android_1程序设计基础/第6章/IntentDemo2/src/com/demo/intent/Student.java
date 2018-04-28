package com.demo.intent;

import java.util.HashMap;
import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
	public Student(){
		name="unknown";
		scores=null;
	}
	public HashMap<String, String> scores = new HashMap<String, String>();
	public String name;
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeMap(scores);
		dest.writeString(name);
	}
	public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
		@Override
		public Student createFromParcel(Parcel source) {
			Student p = new Student();
			p.scores = source.readHashMap(HashMap.class.getClassLoader());
			p.name = source.readString();
			return p;
		}
		@Override
		public Student[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Student[size];
		}
	};
}
