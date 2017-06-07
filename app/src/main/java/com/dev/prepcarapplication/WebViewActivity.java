package com.dev.prepcarapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dev.prepcarapplication.baseClasses.Constants;


public class WebViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		String url="";
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);
		if (getIntent()!=null)
			url = getIntent().getStringExtra(Constants.weburl);

		WebView webView = (WebView)findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);

		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				dialog.dismiss();
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				dialog.show();
			}
		});





	}



}
