package com.dev.prepcarapplication;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Date;

public class MyApplication extends Application {

	private static boolean activityVisible;
	// private ApiResponse apiResponce;
	private double currentLatitude;
	private double currentLongitude;
	// Screen in Pixels
	private int widthPixel = 1280;
	private int heightPixel = 1920;
	public static long locationDetectionTimeInMin;
	private Date date = null;
	private static MyApplication myApplication;
	public static Typeface typeJACKPORT_REGULAR_NCV;
	public static Typeface typeFaceOrgano;
	public static Typeface typeFaceSkipLegDay;
	public static Typeface typeFaceStarzy_Darzy;
	private String curentTime, gmttime, endTime;

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	// private WeatherStationSDK sdk;

	public String getGmttime() {
		return gmttime;
	}

	public void setGmttime(String gmttime) {
		this.gmttime = gmttime;
	}

	public String getCurentTime() {
		return curentTime;
	}

	public void setCurentTime(String curentTime) {
		this.curentTime = curentTime;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// Get Date ----------------
		date = new Date();

		myApplication = this;

	}

	public static MyApplication getApplication() {
		return myApplication;
	}

	public double getCurrentLongitude() {
		return currentLongitude;
	}

	public void setCurrentLongitude(double currentLongitude) {
		this.currentLongitude = currentLongitude;
	}

	public double getCurrentLatitude() {
		return currentLatitude;
	}

	public void setCurrentLatitude(double currentLatitude) {
		this.currentLatitude = currentLatitude;
	}

	public int getWidthPixel() {
		return widthPixel;
	}

	public void setWidthPixel(int widthPixel) {
		this.widthPixel = widthPixel;
	}

	public int getHeightPixel() {
		return heightPixel;
	}

	public void setHeightPixel(int heightPixel) {
		this.heightPixel = heightPixel;
	}

	public static boolean isActivityVisible() {
		return activityVisible;
	}

	public static void activityResumed() {
		activityVisible = true;
	}

	public static void activityPaused() {
		activityVisible = false;
	}

	/*
	 * public WeatherStationSDK getWeatherSDK(){ return sdk;
	 * 
	 * }
	 * 
	 * public void setWeatherSDK(WeatherStationSDK sdk){ this.sdk = sdk;
	 * 
	 * }
	 */
	public static boolean isConnectingToInternet(){
		ConnectivityManager connectivity = (ConnectivityManager) myApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null)

		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}

		}
		return false;
	}
}
