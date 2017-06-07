package com.dev.prepcarapplication.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.WebViewActivity;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.utilities.CameraGallery;
import com.dev.prepcarapplication.utilities.ToastCustomClass;
import com.dev.prepcarapplication.utilities.UtilsClass;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import eu.janmuller.android.simplecropimage.CropImage;

public class FragmentEditProfile extends BaseFragment {
	public static String TAG = "EDITPROFILE";
	EditText profile_frstname,profile_lastname,profile_email,profile_phoneno,profile_state,profile_city,
			profile_streetadd,profile_zipcode,profile_dealership,profile_dealership_code;
	TextView tv_street_address,tv_zipcode,text_about_us,text_terms,text_privacy,text_contact_us;
	Button profile_submit;
	String path="";
	static String roleId;
	int REQUEST_STORAGE = 1;
	ImageView profile_image;
	LinearLayout layout_dealership,layout_dealership_code;
	boolean returnImage;

	public static FragmentEditProfile newInstanse(Bundle bundle) {
		FragmentEditProfile fragment = new FragmentEditProfile();
		if (bundle != null) {
			fragment.setArguments(bundle);
			if(bundle.containsKey(Constants.type)){
				roleId=bundle.getString(Constants.type);
			}
			else{
				roleId="";
			}
		}else{
			roleId="";
		}
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_edit_profile, null);
		initUi(view);
		setListener();
		callingServicegetProfile();
		return view;
	}

	private void callingServicegetProfile() {
		// TODO Auto-generated method stub
		ApiManager.getInstance().getgetProfile(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.profile_submit:
				if (profile_frstname.getText().toString().trim().equals("")) {
					profile_frstname.setError("Please enter first name");
				} else if (profile_lastname.getText().toString().trim().equals("")) {
					profile_lastname.setError("Please enter last name");
				} else if (profile_email.getText().toString().trim().equals("")) {
					profile_email.setError("Please enter email address");
				} else if (profile_phoneno.getText().toString().trim().equals("")) {
					profile_phoneno.setError("Please enter phone number");
				} else if (profile_state.getText().toString().trim().equals("")) {
					profile_state.setError("Please enter state name");
				} else if (profile_city.getText().toString().trim().equals("")) {
					profile_city.setError("Please enter city name");
				} else if (!roleId.equals("3") && profile_streetadd.getText().toString().trim().equals("")) {
					profile_streetadd.setError("Please enter street address");
				} else if (!roleId.equals("3") && profile_zipcode.getText().toString().trim().equals("")) {
					profile_zipcode.setError("Please enter zipcode");
				} else {
					callingServiceEdoitProfile();
				}
				break;
			case R.id.profile_image1:
				requestStoragePermission();
				break;
			case R.id.text_about_us:
				startActivity(new Intent(getActivity(), WebViewActivity.class)
						.putExtra(Constants.weburl,"http://autonana.com/admin/pages/aboutus"));
				break;
			case R.id.text_terms:
				startActivity(new Intent(getActivity(), WebViewActivity.class)
						.putExtra(Constants.weburl,"http://autonana.com/admin/pages/terms"));
				break;
			case R.id.text_privacy:
				startActivity(new Intent(getActivity(), WebViewActivity.class)
						.putExtra(Constants.weburl,"http://autonana.com/admin/pages/privacy"));
				break;
			case R.id.text_contact_us:
				replaceFragment(new FragmentContactUs(),null);
				break;
			default:
				break;
		}
	}

	private void requestStoragePermission() {
		if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED ||
				ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
						!= PackageManager.PERMISSION_GRANTED ) {
			ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, REQUEST_STORAGE);
		} else {
			new CameraGallery().openImageSource(getActivity() , this);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == REQUEST_STORAGE) {
			// If request is cancelled, the result arrays are empty.
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
				new CameraGallery().openImageSource(getActivity() , this);
			} else {
				Toast.makeText(getActivity(), "Allow app to access camera and Media storage", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void callingServiceEdoitProfile() {
		String byteImage="",sendpath="";
		if(returnImage){}
		else{
			if(!path.equals("")&&path!=null){
				sendpath=path;
				byteImage=getStringImage(BitmapFactory.decodeFile(path));
			}}
		ApiManager.getInstance().getEditProfile(this, profile_frstname.getText().toString().trim(), profile_lastname.getText().toString().trim(),
				profile_email.getText().toString().trim(), profile_phoneno.getText().toString().trim(),
				profile_state.getText().toString().trim(), profile_city.getText().toString().trim(),
				profile_streetadd.getText().toString()+"", profile_zipcode.getText().toString()+"",
				byteImage,roleId);
	}

	@Override
	protected void initUi(View view) {
		// TODO Auto-generated method stub
		profile_image=(ImageView)view.findViewById(R.id.profile_image1);
		profile_city=(EditText)view.findViewById(R.id.profile_city);
		profile_email=(EditText)view.findViewById(R.id.profile_email);
		profile_frstname=(EditText)view.findViewById(R.id.profile_frstname);
		profile_lastname=(EditText)view.findViewById(R.id.profile_lastname);
		profile_phoneno=(EditText)view.findViewById(R.id.profile_phoneno);
		profile_state=(EditText)view.findViewById(R.id.profile_state);
		profile_streetadd=(EditText)view.findViewById(R.id.profile_streetadd);
		profile_zipcode=(EditText)view.findViewById(R.id.profile_zipcode);
		profile_dealership=(EditText)view.findViewById(R.id.profile_dealership);
		profile_dealership_code=(EditText)view.findViewById(R.id.profile_dealership_code);
		profile_submit=(Button)view.findViewById(R.id.profile_submit);
		tv_street_address=(TextView) view.findViewById(R.id.tv_street_address);
		tv_zipcode=(TextView)view.findViewById(R.id.tv_zipcode);
		text_about_us=(TextView)view.findViewById(R.id.text_about_us);
		text_terms=(TextView)view.findViewById(R.id.text_terms);
		text_privacy=(TextView)view.findViewById(R.id.text_privacy);
		text_contact_us=(TextView)view.findViewById(R.id.text_contact_us);
		layout_dealership=(LinearLayout)view.findViewById(R.id.layout_dealership);
		layout_dealership_code=(LinearLayout)view.findViewById(R.id.layout_dealership_code);

		if (roleId.equals("3")){
			profile_zipcode.setVisibility(View.GONE);
			profile_streetadd.setVisibility(View.GONE);
			tv_street_address.setVisibility(View.GONE);
			tv_zipcode.setVisibility(View.GONE);
			layout_dealership.setVisibility(View.VISIBLE);
			layout_dealership_code.setVisibility(View.VISIBLE);

		}else{
			profile_zipcode.setVisibility(View.VISIBLE);
			profile_streetadd.setVisibility(View.VISIBLE);
			tv_street_address.setVisibility(View.VISIBLE);
			tv_zipcode.setVisibility(View.VISIBLE);
			layout_dealership.setVisibility(View.GONE);
			layout_dealership_code.setVisibility(View.GONE);
		}
	}

	@Override
	protected void setValueOnUi() {
	}

	@Override
	protected void setListener() {
		profile_submit.setOnClickListener(this);
		profile_image.setOnClickListener(this);
		text_about_us.setOnClickListener(this);
		text_terms.setOnClickListener(this);
		text_privacy.setOnClickListener(this);
		text_contact_us.setOnClickListener(this);
	}

	@Override
	public boolean onBackPressedListener() {
		return false;
	}

	public void setrespponce(JSONObject jObject) {
		// TODO Auto-generated method stub
		if(jObject.optInt("status")==1 && jObject.optInt("dataflow")==1){
			profile_frstname.setText(jObject.optString("firstName"));
			profile_lastname.setText(jObject.optString("lastName"));
			profile_email.setText(jObject.optString("email"));
			profile_phoneno.setText(jObject.optString("phone"));
			profile_state.setText(jObject.optString("state"));
			profile_city.setText(jObject.optString("city"));
			profile_streetadd.setText(jObject.optString("address"));
			profile_zipcode.setText(jObject.optString("zip"));
			String image=jObject.optString("profilePicture");
			profile_dealership.setText(jObject.optString("dealer_name"));
			profile_dealership_code.setText(jObject.optString("unicode"));

			if(image!=null&&!image.isEmpty()){
				Picasso.with(getActivity()).load(image)
						.into(profile_image);
				returnImage=true;
			}
		}
	}

	public void setrespponceEditprofile(JSONObject jObject) {
		// TODO Auto-generated method stub
		if(jObject.optInt("status")==1 && jObject.optInt("dataflow")==1){
			showDialog(jObject.optString("message"))	;
		}
	}
	public void showDialog(String msg){
		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(msg)
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						AlertDialog alert = builder.create();
						alert.dismiss();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("onActivityResult", "resultCode==" + resultCode
				+ "  requestCode==" + requestCode + " Activity.RESULT_OK="
				+ Activity.RESULT_OK);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {

				case CameraGallery.REQUEST_CODE_GALLERY: {
					Log.i("REQUEST_CODE_GALLERY", "REQUEST_CODE_GALLERY");
					returnImage = false;
					try {
						CameraGallery cameraAct = new CameraGallery();
						String realpath;
						realpath = FilePath.getPath(getActivity(), data.getData());
						Log.i("PATHSSSSSSSS", "===" + realpath);
						cameraAct.startCropImage(getActivity(), this, realpath);
					} catch (Exception e) {
						Log.e(TAG, "Error while creating temp file", e);
					}
					break;
				}
				case CameraGallery.REQUEST_CODE_TAKE_PICTURE: {
					returnImage = false;
					Log.i("CODE_TAKE_PICTURE", "true");
					CameraGallery cameraAct = new CameraGallery();
					if (CameraGallery.mFileTemp != null) {
						cameraAct.startCropImage(getActivity(), this,
								CameraGallery.mFileTemp.getPath());
					}
					break;
				}
				case CameraGallery.REQUEST_CODE_CROP_IMAGE: {
					returnImage = false;
					Log.i("REQUEST_CODE_CROP_IMAGE", "REQUEST_CODE_CROP_IMAGE");
					String path = data.getStringExtra(CropImage.IMAGE_PATH);
					if (path == null) {
						return;
					} else {
						try {
							this.path = path;
							profile_image.setImageBitmap(UtilsClass
									.getCroppedBitmap(BitmapFactory
											.decodeFile(path)));
							profile_image.setScaleType(ScaleType.CENTER_CROP);
							CameraGallery.mFileTemp = null;
						} catch (Exception e) {
							e.printStackTrace();
							ToastCustomClass.showToast(getActivity(),
									"Something went wrong!");
						}
					}
					break;
				}}}
	}
	public String getStringImage(Bitmap bmp){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] imageBytes = baos.toByteArray();
		String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
		return encodedImage;
	}
}
