package com.dev.prepcarapplication.utilities;

import android.widget.ImageView;

import com.dev.prepcarapplication.R;

public class GPSAccuracy {
	public static final float GOOD = 20;
	public static final float FAIR = 50;
	public static final float POOR  = 100;
	public static final float NONE  = 101;
	
	public static final String GOOD_TYPE = "Good GPS";
	public static final String FAIR_TYPE = "Fair GPS";
	public static final String POOR_TYPE  = "Poor GPS";
	public static final String NONE_TYPE  = "None Gps";
	
	/*public static String setGpsAccuracy(ImageView imageView , float f){
		String ret = "" ;
		if (f <= GOOD) {
			imageView.setImageResource(R.drawable.green_network);
			ret =  GOOD_TYPE;
		}else if (f > GOOD && f < FAIR) {
			imageView.setImageResource(R.drawable.fare);
			ret =  FAIR_TYPE;
		}else if (f > FAIR && f < POOR ) {
			imageView.setImageResource(R.drawable.poor);
			ret =  POOR_TYPE;
		}else if (f > POOR) {
			imageView.setImageResource(R.drawable.white_network);
			ret =  NONE_TYPE;
		}
		return ret;
	}*/
//	public static String setGpsAccuracy(ImageView imageView , float f){
//		String ret = "" ;
//		if (f <= GOOD) {
//			imageView.setImageResource(R.drawable.green_network);
//			ret =  GOOD_TYPE;
//		}else  {
//			imageView.setImageResource(R.drawable.white_network);
//			ret =  NONE_TYPE;
//		}
//		return ret;
//	}

}
