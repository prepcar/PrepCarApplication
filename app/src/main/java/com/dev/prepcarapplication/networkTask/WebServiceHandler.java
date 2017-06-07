package com.dev.prepcarapplication.networkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dev.prepcarapplication.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Mukesh Kumawat on 15-Aug-16.
 * Designation Android Team Leader
 * Organization Parasme Software And Technology
 * Email mukeshkmtskr@gmail.com
 * Mobile +917737556190
 */

public class WebServiceHandler {
    private OkHttpClient okHttpClient;
    private RequestBody requestBody;
    private Request request;
    private static Context context;
    private ProgressDialog progressDialog;
    public WebServiceListener serviceListener;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static final String TEMP_FILE_NAME=System.currentTimeMillis()+"";
    public WebServiceHandler(Context context) {
        this.context= context;
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(80, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
                .build();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public void post(String url, FormBody.Builder builder) throws IOException {
        if (MyApplication.isConnectingToInternet()) {
            Log.e("POSTURL", url);

            RequestBody requestbody = builder.build();
            request = new Request.Builder()
                    .url(url)
                    .post(requestbody)
                    .build();


            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    serviceListener.onResponse(response.body().string());
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("ERROR", e.toString());
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                }


            });
        }else{
            Toast.makeText(context, "NO Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    public void postJson(String url, String builder) throws IOException {
        if (MyApplication.isConnectingToInternet()) {
            Log.e("POSTURL", url);
            RequestBody requestbody = RequestBody.create(JSON, builder);
            request = new Request.Builder()
                    .url(url)
                    .post(requestbody)
                    .build();


            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    serviceListener.onResponse(response.body().string());
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("ERROR", e.toString());
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                }


            });
        }else{
            Toast.makeText(context, "NO Internet Connection", Toast.LENGTH_LONG).show();
        }
    }
   /* public void patch(String url, FormBody.Builder builder) throws IOException {
        Log.e("PATCHURL",url);
        request =  new Request.Builder()
                .url(url)
                .patch(builder.build())
                .addHeader("authenticationtoken", AppConstants.AUTH_TOKEN)
                .addHeader("userid", AppConstants.USER_ID)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                serviceListener.onResponse(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

            }
        });
    }*/

    public void loginUser(String url, String email, String password, String deviceType, String deviceId) throws IOException {
        Log.e("LoginPost",url);

        FormBody.Builder builder=new FormBody.Builder();
        builder.add("email",email);
        builder.add("password",password);
        builder.add("device_token",deviceId);
        builder.add("device_type",deviceType);
        String credential = Credentials.basic(email, password);
        Log.e("CRED",credential);

        request =  new Request.Builder()
                .url(url)
                //.addHeader("Authorization",credential)
                .post(builder.build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                serviceListener.onResponse(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("ERROR",e.toString());
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }


        });
    }
    public void get(String url) throws IOException {
        Log.e("GETURL",url);
        if (MyApplication.isConnectingToInternet()) {
            request =  new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    serviceListener.onResponse(response.body().string());

                }

                @Override
                public void onFailure(Call call, IOException e) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            });
        }else{
            Toast.makeText(context, "NO Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    public void postMultiPart(String url, MultipartBody.Builder builder) throws IOException {
        Log.e("POSTMPARTURL",url);
        request =  new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                serviceListener.onResponse(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("ERROR",e.toString());
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

            }
        });
    }

    public static FormBody.Builder createBuilder(String [] paramsName, String [] paramsValue){
        FormBody.Builder builder=new FormBody.Builder();
        Log.e("kuch", "createBuilder: "+paramsName.toString() );
        Log.e("kuch", "createBuilder: "+paramsValue.toString() );
        for(int i=0;i<paramsName.length;i++){
            Log.e("values", "createBuilder: "+paramsValue[i] );
            builder.add(paramsName[i],paramsValue[i]);
        }

        Log.e("builder", "createBuilder: "+builder.toString() );
        return builder;
    }

    public static MultipartBody.Builder createMultiPartBuilder(String [] paramsName, String [] paramsValue, List<String> imageArray, boolean isMultiple, String imageParam){

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for(int i=0;i<paramsName.length;i++){
            builder.addFormDataPart(paramsName[i],paramsValue[i]);
            Log.e("TAG", paramsName[i] +" : "+paramsValue[i] );
        }

        if (imageArray.size()>0) {
            if (isMultiple) {
                for (int i = 0; i < imageArray.size(); i++) {
                    File f = new File(imageArray.get(i));
                    if (f.exists()) {
                        builder.addFormDataPart(imageParam, TEMP_FILE_NAME + i + ".png", RequestBody.create(MediaType.parse("image/png"), f));
                    }
                }
            }else{
                File f = new File(imageArray.get(0));
                if (f.exists()) {
                    builder.addFormDataPart("image", TEMP_FILE_NAME  + ".png", RequestBody.create(MediaType.parse("image/png"), f));
                }
            }
        }
        Log.e("builder", "createBuilder: "+builder.toString() );
        return builder;
    }


}