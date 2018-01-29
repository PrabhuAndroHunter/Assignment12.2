package com.assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by prabhu on 28/1/18.
 */

/*
	* This class will do all SharedPreferences activities
	* */
public class SharedPrefManager {

	// this will return SharedPreferences object
	public static SharedPreferences getSharedPref(Context mContext) {
		SharedPreferences pref = mContext.getSharedPreferences("settings", Context.MODE_PRIVATE);
		return pref;
	}

	// this will set String type value
	public static void setStringPrefVal(Context mContext, String key, String value) {
		Editor edit = getSharedPref(mContext).edit();
		edit.putString(key, value);
		edit.commit();
	}

	// this will set Int type value
	public static void setIntPrefVal(Context mContext, String key, int value) {
		Editor edit = getSharedPref(mContext).edit();
		edit.putInt(key, value);
		edit.commit();
	}

	// this will set Boolean type value
	public static void setBooleanPrefVal(Context mContext, String key, boolean value){
		Editor edit = getSharedPref(mContext).edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

	// this will get Boolean type value
	public static boolean getBooleanPrefVal(Context mContext, String key) {
		SharedPreferences pref = getSharedPref(mContext);
		boolean val = false;
		if(pref.contains(key))
			val = pref.getBoolean(key,false);
		else
			val = false;
		return val;
	}
	// this will get String type value
	public static String getPrefVal(Context mContext, String key) {
		SharedPreferences pref = getSharedPref(mContext);
		String val = "";
		if(pref.contains(key))
			val = pref.getString(key, "");
		else
			val = "";
		return val;
	}

	// this will get Int type value
	public static int getIntPrefVal(Context mContext, String key) {
		SharedPreferences pref = getSharedPref(mContext);
		int val = 0;
		if(pref.contains(key)) val = pref.getInt(key, 0);
		return val;
	}
}
