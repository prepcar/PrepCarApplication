package com.dev.prepcarapplication.utilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.Is_Internet_Available_Class;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsClass {
	
	public static String HEART_METER_DEVICE_NAME = "";
	public static String WIND_METER_DEVICE_NAME = "";
	public static boolean IS_HEART_CONNECTED = false;
	
	private static boolean flag;
	
	
	
	public static String getDecimalFormatValue(double value) {
		// TODO Auto-generated method stub
		return new DecimalFormat("##.##").format(value);
	}
	public static String getDecimalFormatValuefour(double value) {
		// TODO Auto-generated method stub
		return new DecimalFormat("##.####").format(value);
	}
	public static String getDecimalFormatValueThree(double value) {
		// TODO Auto-generated method stub
		return new DecimalFormat("##.###").format(value);
	}
	
	public static String getDecimalFormatValue(String value) {
		// TODO Auto-generated method stub
		return new DecimalFormat("##.##").format(Double.valueOf(value));
	}
	public static String getDecimalFormatValueTHREE(String value) {
		// TODO Auto-generated method stub
		return new DecimalFormat("##.###").format(Double.valueOf(value));
	}
	
/*	
	public static CharSequence getDecimalFormatValueString(String elevation) {
		// TODO Auto-generated method stub
		return new DecimalFormat("##.##").format(elevation);
	}*/

	public static boolean isEmpty(String string) {
		if (string == null || string.length() == 0) {
			return true;
		}
		return false;
	}

	public static int GetUnixTime() {
		Calendar calendar = Calendar.getInstance();
		long now = calendar.getTimeInMillis();
		int utc = (int) (now / 1000);
		return (utc);

	}
	public static String getMINUTE(String time) {
		String[] units = time.split(":"); 
		float hours = Float.parseFloat(units[0]); // first element
		float minutes = Float.parseFloat(units[1]);
		float seconds = Float.parseFloat(units[2]);// second element
		System.out.println("minutes========="+hours+"    "+minutes+"    "+seconds);
		float duration=  (float) ((hours*60) + (minutes) + (seconds/60));
	//	int duration = 60 * minutes + seconds;
		System.out.println("minlast======"+duration);
		return UtilsClass.getDecimalFormatValue(duration);
	}
	public static String getHOURS(String time) {
		// String time = "02:30"; //mm:ss
		String[] units = time.split(":"); // will break the string up into an
											// array
		float hours = Float.parseFloat(units[0]); // first element
		float minutes = Float.parseFloat(units[1]);
		float seconds = Float.parseFloat(units[2]);// second element
		System.out.println("minutes========="+hours+"    "+minutes+"    "+seconds);
		float duration=  (float) ((hours*60) + (minutes) + (seconds/60));
	//	int duration = 60 * minutes + seconds;
		float finalsend=duration/60;
		System.out.println("hourslast======"+finalsend);
		return UtilsClass.getDecimalFormatValue(finalsend);
	}
	public static String getSecond(String time) {
		// String time = "02:30"; //mm:ss
		String[] units = time.split(":"); // will break the string up into an
											// array
		int hours = Integer.parseInt(units[0]); // first element
		int minutes = Integer.parseInt(units[1]);
		int seconds = Integer.parseInt(units[2]);// second element
		int duration = 60 * minutes + seconds;
		return String.valueOf(duration);
	}
	public static double calculateTimeHours(long seconds) {
        int day = (int)TimeUnit.SECONDS.toDays(seconds);        
   //     double hours = TimeUnit.SECONDS.toHours(seconds) - TimeUnit.SECONDS.toHours(TimeUnit.SECONDS.toDays(seconds));
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toMinutes(TimeUnit.SECONDS.toHours(seconds));
        long second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toSeconds(TimeUnit.SECONDS.toMinutes(seconds));
        long  hours = second  / 3600 ;
        System.out.println("Day " + day + " Hour " + hours + " Minute " + minute + " Seconds " + second);
		return hours;

    }
	public static double calculateTimeMin(long seconds) {
        int day = (int)TimeUnit.SECONDS.toDays(seconds);        
        long hours = TimeUnit.SECONDS.toHours(seconds) - TimeUnit.SECONDS.toHours(TimeUnit.SECONDS.toDays(seconds));
      //  double minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toMinutes(TimeUnit.SECONDS.toHours(seconds));
        long second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toSeconds(TimeUnit.SECONDS.toMinutes(seconds));
        double minute = second / 60 ;
        System.out.println("Day " + day + " Hour " + hours + " Minute " + minute + " Seconds " + second);
		return minute;

    }

	public static String gmtTime(String time) {
		Calendar c = Calendar.getInstance();
		// Date date=new Date(time);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String formattedDate = df.format(c.getTime());
		System.out.println("gmtcurent====" + formattedDate);
		return formattedDate;
	}

	public static String startTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = df.format(c.getTime());
		System.out.println("curent====" + formattedDate);
		return formattedDate;
	}

	public static double getDistance(double latA, double lngA, double latB, double lngB, char in) {
		Location locationA = new Location("point A");

		locationA.setLatitude(latA);
		locationA.setLongitude(lngA);
		Log.e("disfunA==", "locA"+locationA);

		Location locationB = new Location("point B");

		locationB.setLatitude(latB);
		locationB.setLongitude(lngB);
		Log.e("disfunB==", "locB"+locationB);

		float distance = locationA.distanceTo(locationB);
		Log.e("distance",String.valueOf(distance));
		double actualDis = distance;

		switch (in) {
		case 'M': //miles
			double roundOff = (double) Math.round(actualDis * 100) / 100;
			Log.e("distance",String.valueOf(roundOff));
			roundOff = roundOff / Double.valueOf("1609.344");  //Double.valueOf("0.000621371");
			roundOff = Double.valueOf(UtilsClass.getDecimalFormatValue(roundOff)) ;
			return roundOff;
		case 'K': //kilometer
			actualDis = distance * 0.001;
			roundOff = (double) Math.round(actualDis * 100) / 100;
			return roundOff;
		}

		return actualDis;
	}
	
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	       return (dist);
	    }
	public static double getDistance(Location locationA, Location locationB, char in,String previousDistance) {
		float distance = locationA.distanceTo(locationB);
		Log.e("distance",String.valueOf(distance));
		double actualDis = distance;

		switch (in) {
		case 'M': // mile
			double roundOff=0.0;
		//	double roundOff = (double) Math.round(actualDis * 100) / 100;
			Log.e("meter distance",String.valueOf(roundOff));
			roundOff = actualDis / Double.valueOf("1609.344");  //Double.valueOf("0.000621371");
		//	roundOff = Double.valueOf(UtilsClass.getDecimalFormatValue(roundOff)) + Double.valueOf(previousDistance);
			roundOff = Double.valueOf(roundOff) + Double.valueOf(previousDistance);
			return roundOff;
		case 'K': //kilometer
			actualDis = distance * 0.001;
			roundOff = (double) Math.round(actualDis * 100) / 100;
			Log.e("kilometer distance",String.valueOf(roundOff));
			roundOff = roundOff+Double.valueOf(previousDistance);
			return roundOff;
		}
		return actualDis;
	}
	public static double distance(double lat1, double lon1, double lat2,
			double lon2, char unit,String previousDistance) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
			dist=dist + Double.valueOf(previousDistance);
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		else if(unit=='M'){
			dist=dist;
			dist=dist + Double.valueOf(previousDistance);
		}
		
		dist = round(dist, 3);
		return (dist);
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
public static double getCurrentDistance(Location locationA, Location locationB, char in) {
		

		float distance = locationA.distanceTo(locationB);
		Log.e("distance",String.valueOf(distance));
		double actualDis = distance;

		switch (in) {
		case 'M': // mile
			double roundOff = (double) Math.round(actualDis * 100) / 100;
			Log.e("meter distance",String.valueOf(roundOff));
			roundOff = roundOff / Double.valueOf("1609.344");  //Double.valueOf("0.000621371");
		//	roundOff = Double.valueOf(UtilsClass.getDecimalFormatValue(roundOff)) + Double.valueOf(previousDistance);
			return roundOff;
		case 'K': //kilometer
			actualDis = distance * 0.001;
			roundOff = (double) Math.round(actualDis * 100) / 100;
			Log.e("kilometer distance",String.valueOf(roundOff));
		//	roundOff = roundOff+Double.valueOf(previousDistance);
			return roundOff;
		}
         
		return actualDis;
	}

	/*public static String getGCMRegistrationID(Context context) {
		// Make sure the device has the proper dependencies.
		// Get GCM registration id
		String registrationId = "";
		try {
			GCMRegistrar.checkDevice(context);

			// Make sure the manifest was properly set - comment out this line
			// while developing the app, then uncomment it when it's ready.
			GCMRegistrar.checkManifest(context);

			registrationId = GCMRegistrar.getRegistrationId(context);
			Log.i("Registration id1", "==" + registrationId);

			// Check if regid already presents
			if (registrationId.equals("")) {
				// Registration is not present, register now with GCM

				GCMRegistrar.register(context, CommonUtilities.SENDER_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registrationId;
	}*/

	public static void hideKeyBoard(Context context, EditText myEditText) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
	}

	@SuppressLint("NewApi")
	public static void hideKeyBoard(Context context, SearchView myEditText) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
	}

	public static long getCurrentTimeInMili() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());

		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		System.out.println("Day " + day + " Month: " + month + " year:" + year);
		return System.currentTimeMillis();
	}

	public static boolean isEmailValid(String email) {
		boolean isValid = false;
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	public static boolean isConnectingToInternet(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
		}
		return false;
	}

	public static Boolean checkGPS(Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Toast.makeText(context, "GPS is Enabled in your devide",
					Toast.LENGTH_SHORT).show();
			return true;
		} else {
			showGPSDisabledAlertToUser(context);
			return false;
		}
	}

	private static void showGPSDisabledAlertToUser(final Context context) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder
				.setMessage(
						"GPS is disabled in your device. Would you like to enable it?")
				.setCancelable(false)
				.setPositiveButton("Goto Settings Page To Enable GPS",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent callGPSSettingIntent = new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								((Activity) context)
										.startActivity(callGPSSettingIntent);
							}
						});
		alertDialogBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	public static void alertToUser(final Context context) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setMessage("Something is wrong?").setCancelable(
				false);
		alertDialogBuilder.setNegativeButton("Ok",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	public static String getImgListString(
			ArrayList<HashMap<String, Object>> listOfBitmap) {
		StringBuffer img_urls = new StringBuffer();
		for (int i = 0; i < listOfBitmap.size(); i++) {
			if (!UtilsClass.isEmpty((String) listOfBitmap.get(i).get(
					Constants.userImageURL))) {
				if (i == listOfBitmap.size() - 1)
					img_urls.append((String) listOfBitmap.get(i).get(
							Constants.userImageURL));
				else
					img_urls.append((String) listOfBitmap.get(i).get(
							Constants.userImageURL)
							+ ",");
			}
		}
		return img_urls.toString();
	}

	public static Spanned getRedText(String normalText, String colortext) {
		return Html.fromHtml(normalText + "<font color='#FF0000'>" + colortext
				+ "</font>");
	}

	public static Spanned setTitle(CharSequence mTitle) {
		return Html.fromHtml("<font color='#FFFFFF'>" + mTitle + "</font>");
		// return
		// Html.fromHtml("<font color='#009f3c'><b>"+mTitle+"</b></font>");
	}

	public static String setTitle(String colorCode, CharSequence mTitle) {
		return "<font color='" + colorCode + "'>" + mTitle + "</font>";
		// return
		// Html.fromHtml("<font color='#009f3c'><b>"+mTitle+"</b></font>");
	}

	public static String getDate(String string) {
		long time = Long.parseLong(string);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int date = cal.get(Calendar.DATE);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		String fullDate = date + "/" + month + "/" + year;
		return fullDate;
	}

	public static long getMilisecondsFromDate(String string) {
		String sp[] = string.split("-");

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sp[0]));
		cal.set(Calendar.MONTH, getMonthNumber(sp[1]));
		cal.set(Calendar.YEAR, Integer.parseInt(sp[2]));

		return cal.getTimeInMillis();
	}

	public static String getDate(Long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int date = cal.get(Calendar.DATE);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		String fullDate = date + "-" + getMonthName(month) + "-" + year;
		return fullDate;
	}

	public static String getMonthYear(Long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int date = cal.get(Calendar.DATE);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		return getMonthName(month) + "-" + year;
	}

	public static String getTime(String string) {
		long time = Long.parseLong(string);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		String fullTime = hour + ":" + min;
		return fullTime;
	}

	public static String getDateTime(long time) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int date = cal.get(Calendar.DATE);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		String fullDate = date + "-" + getMonthName(month) + "-" + year;

		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		String fullTime = hour + ":" + min;

		return fullDate + " " + fullTime;
	}

	public static void showDatePickerDialog(Context context,
			DatePickerDialog.OnDateSetListener callBack) {
		long mili = getCurrentTimeInMili();
		String dateStr = getDate(String.valueOf(mili));
		String date[] = dateStr.split("/");
		// DatePickerDialog d = new DatePickerDialog(context, callBack, year,
		// monthOfYear, dayOfMonth)
		DatePickerDialog dialog = new DatePickerDialog(context, callBack,
				Integer.parseInt(date[2]), Integer.parseInt(date[1]),
				Integer.parseInt(date[0]));
		dialog.show();
	}

	public static String getMonthName(int month) {
		String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };
		return months[month];
	}

	public static Integer getMonthNumber(String month) {
		String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };
		for (int i = 0; i < months.length; i++) {
			if (month.equalsIgnoreCase(months[i]))
				return i;

		}
		return -1;
	}

	public static void showTimePickerDialog(Context context,
			TimePickerDialog.OnTimeSetListener callBack) {
		long mili = getCurrentTimeInMili();
		String timeStr = getTime(String.valueOf(mili));
		String time[] = timeStr.split(":");
		// DatePickerDialog d = new DatePickerDialog(context, callBack, year,
		// monthOfYear, dayOfMonth)
		TimePickerDialog dialog = new TimePickerDialog(context, callBack,
				Integer.parseInt(time[0]), Integer.parseInt(time[1]), true);
		dialog.show();
	}

	/*
	 * public static void editBoxWarning(Context context, View view, String
	 * string){ YoYo.with(Techniques.Shake).playOn(view);
	 * ToastCustomClass.showToast(context, string); }
	 */

	public static void animationLittleUp(Context context, final View view[],
			int ANIMATION_DURATION, int ANIMATION_OFFSET) {
		final Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.splash_guide_logo);
		animation.setDuration(ANIMATION_DURATION);
		animation.setStartOffset(ANIMATION_OFFSET);
		if (view.length > 1) {
			animation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation arg0) {

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {

				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					animation.setAnimationListener(null);
					arg0.setAnimationListener(null);
					view[1].startAnimation(animation);
					view[1].setVisibility(View.VISIBLE);
				}
			});
		}
		view[0].startAnimation(animation);
	}

	public static void animationZoomIn(Context context, final View view[],
			int ANIMATION_DURATION, int ANIMATION_OFFSET) {
		final Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.splash_guide_items);
		animation.setDuration(ANIMATION_DURATION);
		animation.setStartOffset(ANIMATION_OFFSET);
		if (view.length > 1) {
			animation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation arg0) {

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {

				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					animation.setAnimationListener(null);
					arg0.setAnimationListener(null);
					view[1].startAnimation(animation);
					view[1].setVisibility(View.VISIBLE);
				}
			});
		}
		view[0].startAnimation(animation);

	}

	public static void animationSlide_out_up(Context context,
			final View view[], int ANIMATION_DURATION, int ANIMATION_OFFSET) {
		final Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.slide_out_up);
		animation.setDuration(ANIMATION_DURATION);
		animation.setStartOffset(ANIMATION_OFFSET);
		if (view.length > 1) {
			animation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation arg0) {

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {

				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					animation.setAnimationListener(null);
					arg0.setAnimationListener(null);
					view[1].startAnimation(animation);
					view[1].setVisibility(View.VISIBLE);
				}
			});
		}
		view[0].startAnimation(animation);

	}

	public static void animationSlide_in_down(Context context,
			final View view[], int ANIMATION_DURATION, int ANIMATION_OFFSET) {
		final Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.slide_in_down);
		animation.setDuration(ANIMATION_DURATION);
		animation.setStartOffset(ANIMATION_OFFSET);
		if (view.length > 1) {
			animation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation arg0) {

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {

				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					animation.setAnimationListener(null);
					arg0.setAnimationListener(null);
					view[1].startAnimation(animation);
					view[1].setVisibility(View.VISIBLE);
				}
			});
		}
		view[0].startAnimation(animation);

	}

	public static void animationSlide_in_right(Context context,
			final View view[], int ANIMATION_DURATION, int ANIMATION_OFFSET) {
		final Animation animation = AnimationUtils.loadAnimation(context,
				R.anim.slide_in_right);
		animation.setDuration(ANIMATION_DURATION);
		animation.setStartOffset(ANIMATION_OFFSET);
		if (view.length > 1) {
			animation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation arg0) {

				}

				@Override
				public void onAnimationRepeat(Animation arg0) {

				}

				@Override
				public void onAnimationEnd(Animation arg0) {

					animation.setAnimationListener(null);
					arg0.setAnimationListener(null);
					view[1].startAnimation(animation);
					view[1].setVisibility(View.VISIBLE);
				}
			});
		}
		view[0].startAnimation(animation);
	}

	public static String getAddress(Context context, Double latitude,
			Double longitude) {
		String add = "";
		Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
		try {
			List<Address> addresses = geoCoder.getFromLocation(latitude,
					longitude, 1);

			if (addresses.size() > 0) {
				System.out.println(addresses.get(0).getLocality());
				System.out.println(addresses.get(0).getCountryName());
				add = add + addresses.get(0).getLocality() + ","
						+ addresses.get(0).getCountryName();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return add;
	}

	public static String getFormattedDate(int dayOfMonth, int monthOfYear,
			int year) {
		return dayOfMonth + "-" + UtilsClass.getMonthName(monthOfYear) + "-"
				+ year;
	}

	public static void plsStartInternet(Context context) {
		// ToastCustomClass.showToast(context, "Please start your internet.");
		Is_Internet_Available_Class.alertWarningMsg(context);
	}

	// shiv prakash
	/** A method to download json data from url */
	public static String downloadUrls(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	// shiv prakash
	// remove string lastcharacher
	public static String removeStringLastCharColn(String str) {
		if (str.length() > 0 && str.charAt(str.length() - 1) == '|') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	

	public static double roundMyData(double Rval, int numberOfDigitsAfterDecimal) {
		double p = (float) Math.pow(10, numberOfDigitsAfterDecimal);
		Rval = Rval * p;
		double tmp = Math.floor(Rval);
		// System.out.println("~~~~~~tmp~~~~~"+tmp);
		return (double) tmp / p;
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	/*public static String[] getCountryName(Context context) {

		String[] locales = Locale.getISOCountries();
		String[] countryName = new String[locales.length];
		ArrayList<Country> country = new ArrayList<Country>();

		int i = 0;
		for (String countryCode : locales) {

			Locale obj = new Locale("", countryCode);
			countryName[i++] = obj.getDisplayCountry();
			Log.i("shiv prakash",
					"Country Code = " + countryCode + " "
							+ obj.getISO3Language() + ", Country Name = "
							+ obj.getDisplayCountry());

		}
		return countryName;

	}*/

	public static String getCountry(Context context) {

		Locale locale = Locale.getDefault();
		// locale.getCountry();
		return locale.getCountry();

	}

	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	public static AlertDialog showAlertDialog(Context context, String msg,
			String title) {

		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
		alertBuilder.setTitle(title);
		alertBuilder.setMessage(msg);

		alertBuilder.setNeutralButton("OK", null);

		AlertDialog alertDialog = alertBuilder.create();

		alertDialog.show();
		return alertDialog;

	}

	public static void showCurrentDatePickerDialog(Context context,
			DatePickerDialog.OnDateSetListener callBack) {
		long mili = getCurrentTimeInMili();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(mili);
		// DatePickerDialog d = new DatePickerDialog(context, callBack, year,
		// monthOfYear, dayOfMonth)
		DatePickerDialog dialog = new DatePickerDialog(context, callBack,
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH));

		dialog.show();
	}

	public static String removeStringLastChar(StringBuffer str, Character chr) {
		String result = "";
		if (str.length() > 0 && str.charAt(str.length() - 1) == chr) {
			result = str.substring(0, str.length() - 1);
		}
		return result;
	}

	public static String getAndroidI(Context context) {

		return Secure
				.getString(context.getContentResolver(), Secure.ANDROID_ID);
	}
//////////////////////////////////////////////////////////////////////////////////////////////
	public static String getAndroidDevic(Context myActivity) {
		TelephonyManager tManager = (TelephonyManager) myActivity
				.getSystemService(Context.TELEPHONY_SERVICE);
		String uid = tManager.getDeviceId();
		return uid;

	}

	public static String getDeviceModel(Context myActivity) {
		String str = android.os.Build.MODEL;
		return str;
	}

	public static String getDeviceSDKVersion(Context myActivity) {
		String str = android.os.Build.VERSION.SDK;
		Log.i("getDeviceSDKVersion", str + "\n codename=  "
				+ android.os.Build.VERSION.CODENAME + "\n Increment =  "
				+ android.os.Build.VERSION.INCREMENTAL + "\n RELEASE =  "
				+ android.os.Build.VERSION.RELEASE + "\n SDK INT =  "
				+ android.os.Build.VERSION.SDK_INT);
		return str;
	}

	public static String getDeviceSDKVersionName(Context myActivity) {
		PackageInfo pInfo = null;
		try {
			pInfo = myActivity.getPackageManager().getPackageInfo(
					myActivity.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		String version = pInfo.versionName;
		Log.i("version name ", version);
		return version;

	}

	public static String getAppVersion(Context context) {
		String app_ver = "";
		try {
			app_ver = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
			Log.i("App Version", " " + app_ver);
		} catch (NameNotFoundException e) {
			Log.i("App Version", "Exception=" + e.getMessage());
		}
		return app_ver;
	}

	public static void underConstruction(Context context) {
		ToastCustomClass.showToast(context, "This is under construction.");
	}

	/*public static void setTabTextColor(Context context, TextView tv, View v) {
		XmlResourceParser xrp = context.getResources().getXml(
				R.drawable.text_tab_bg);
		try {
			ColorStateList csl = ColorStateList.createFromXml(
					context.getResources(), xrp);
			tv.setTextColor(csl);
		} catch (Exception e) {
		}

		// tv.setBackgroundResource(R.drawable.tab_bg_selector);
		tv.setGravity(Gravity.CENTER);

		v.setBackgroundResource(android.R.color.transparent);
	}*/

	public static void loadImageNoRound(Context context, ImageView view,
			String url) {
		if (url != null && !url.isEmpty())
			Picasso.with(context).load(url).placeholder(null).into(view);
		else
			view.setImageResource(0);
	}

	public static void loadImageRound(Context context, ImageView view,
			String url) {
		if (url != null && !url.isEmpty())
			Picasso.with(context).load(url).into(view);
		else
			view.setImageResource(0);
	}

	public static String withoutZeroPH(String ph_no) {
		ph_no = ph_no.replaceAll("[- ]+", "");
		return ph_no;
	}

	public static String removeLeadingZero(String ph_no) {
		ph_no = ph_no.replaceAll("^0+(?!$)", "");
		return ph_no;
	}

	public static String withZeroPH(String ph_no) {
		ph_no = ph_no.replaceAll("[- ]+", "");
		int length = ph_no.length();
		if (length == 9)
			ph_no = "0" + ph_no;
		return ph_no;
	}

	public static void openGooglePlayStore(Context context, String link) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(link));
		context.startActivity(i);
	}

	public static void deviceVersionName() {

		StringBuilder builder = new StringBuilder();
		builder.append("android : ").append(Build.VERSION.RELEASE);

		Field[] fields = Build.VERSION_CODES.class.getFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			int fieldValue = -1;

			try {
				fieldValue = field.getInt(new Object());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			if (fieldValue == Build.VERSION.SDK_INT) {
				builder.append(" : ").append(fieldName).append(" : ");
				builder.append("sdk=").append(fieldValue);
			}
		}

		Log.e("DEVIC", "OS: " + builder.toString());
	}

	public static String getIpAddress(Context context) {
		WifiManager wm = (WifiManager) context
				.getSystemService(context.WIFI_SERVICE);
		String ipAddress = Formatter.formatIpAddress(wm.getConnectionInfo()
				.getIpAddress());
		Log.e("IPADDRESS", ipAddress);

		return ipAddress;
	}

	public static String getMacAddress(Context context) {

		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		String macAddress = wInfo.getMacAddress();
		return macAddress;
	}

	public static void socialSharing(Context context, String msg) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, msg);
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "GAPPSS");
		context.startActivity(Intent.createChooser(intent, "Share"));
	}

	public static String splitDateFromString(String time) {
		try {
			time = time.split(" ")[0];
		} catch (Exception e) {
			// TODO: handle exception
			time = "";
		}
		return time;

	}

	// GPS ON OFF CODE
	private static LocationManager manager;
	private static boolean statusOfGPS;
	

//	public static void enableGPS(Context context, ImageView view) {
//		manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//		statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//		if (statusOfGPS) {
//			view.setImageResource(R.drawable.white_network);
//		} else {
//			view.setImageResource(R.drawable.white_network);
//			showSettingsAlert(context);
//		}
//	}

//	public static ScheduledExecutorService checkGpsStatus(
//			final Context context, final ImageView view) {
//		ScheduledExecutorService exec = Executors
//				.newSingleThreadScheduledExecutor();
//		exec.scheduleAtFixedRate(new Runnable() {
//			@Override
//			public void run() {
//				Log.d("GPS CHECK", "gps");
//
//				statusOfGPS = manager
//						.isProviderEnabled(LocationManager.GPS_PROVIDER);
//				((BaseFragmentActivity) context).runOnUiThread(new Runnable() {
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						if (statusOfGPS) {
//							view.setImageResource(R.drawable.green_network);
//						} else {
//							view.setImageResource(R.drawable.white_network);
//
//						}
//					}
//				});
//
//			}
//		}, 0, 3, TimeUnit.SECONDS);
//		return exec;
//	}

	public static void showSettingsAlert(final Context context) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

		// Setting Dialog Title
		alertDialog.setTitle("GPS is settings");

		// Setting Dialog Message
		alertDialog
				.setMessage("GPS is not enabled. Do you want to go to settings menu?");

		// On pressing Settings button
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						// mContext.startActivityForResult(intent, "1");
						context.startActivity(intent);
						// network_iamge.setImageResource(R.drawable.green_network);

					}
				});

		// on pressing cancel button
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		// Showing Alert Message
		alertDialog.show();
	}
	public static  Bitmap getCroppedBitmap(Bitmap bitmap) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);

	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,bitmap.getWidth() / 2, paint);
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	    //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
	    //return _bmp;
	    return output;
	}
}
