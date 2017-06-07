package com.dev.prepcarapplication.networkTask;

public interface URLsClass {
	public final String reverseGeoCodingApi = "http://maps.googleapis.com/maps/api/geocode/json";
	public final String address = "address";
	public final String sensor = "sensor";
	public final static String p_fbid = "fbid";
	
	public final static String service_type_google = "https://maps.googleapis.com/maps/api/elevation/json?sensor=false&locations=";
	public final static String service_type_weather = "http://api.openweathermap.org/data/2.5/weather?APPID=9e864f7e8f6a20bd55ea862eb8e0f044&units=metric";

    //local URl
//	public final static String baseUrl = "http://dev-updates.com/mywindfit/webservice/";// new 52.42.79.112
    //amazon url
	//public final static String baseUrl = "http://dev-updates.com/2016/prepcarapp/admin/webservices/";
	public final static String baseUrl = "http://autonana.com/admin/admin/webservices/";
	//	public final static String baseUrl = "http://192.168.1.92/prepcar/admin/webservices/";
	// Service Type
	public final static String service_type_signUp = baseUrl+"register?";
	public final static String service_type_login = baseUrl+"login?";
	public final static String service_type_changePassword = baseUrl+"changePassword?";
	public final static String service_type_forgot = baseUrl+"forgotPassword?";
	public final static String service_type_getprofile = baseUrl+"profile?";
	//public final static String service_type_car_list = baseUrl+"car_list?";
	public final static String service_type_getyear = baseUrl+"year?";
	public final static String service_type_getmake = baseUrl+"make?";
	public final static String service_type_getmodel = baseUrl+"model?";
	public final static String service_type_new_matches = baseUrl+"new_matches?";
	public final static String service_type_favorites = baseUrl+"favorites?";

	public final static String service_type_rating = baseUrl+"rating?";
	public final static String service_type_messages = baseUrl+"messages?";
	public final static String service_type_buyer_request = baseUrl+"buyer_request?";
	public final static String service_type_create_deal = baseUrl+"create_deal?";
	public final static String service_type_buyer_list = baseUrl+"buyer_list?";
	public final static String service_type_car_list = baseUrl+"car_list?";
	public final static String service_find_my_matches = baseUrl+"find_my_matches?";
	public final static String service_new_matches = baseUrl+"new_matches?";
	public final static String service_count_my_matches = baseUrl+"count_my_matches?";
	public final static String service_type_car_plan_detail = baseUrl+"car_plan_detail?";
	public final static String service_type_deal_detail = baseUrl+"deal_detail?";
	public final static String service_type_deal_send = baseUrl+"send?";
	public final static String service_type_sent_deals = baseUrl+"sent?";
	public final static String service_type_dealer_favorites = baseUrl+"dealer_favorites?";
	public final static String service_type_dealer_activity = baseUrl+"activity?";
	public final static String service_type_contact_us = baseUrl+"contact?";
	public final static String service_type_test_drive = baseUrl+"testdrive?";
	public final static String service_type_all_test_drive = baseUrl+"testdrivelist?";
	public final static String service_type_test_drive_request = baseUrl+"testdrive_request?";
	public final static String service_type_test_drive_appointment_status = baseUrl+"appointment_status?";



	public final static String service_type_deletefavorite = baseUrl+"deletefavorite?";
	public final static String service_type_car_delete = baseUrl+"car_delete?";
	public final static String service_type_car_detail = baseUrl+"car_detail?";
	public final static String service_type_new_matches_list_detail = baseUrl+"new_matches_list_detail?";


	public final static String service_type_car_add = baseUrl+"car_add?";
	public final static String service_type_car_edit = baseUrl+"car_edit?";
	public final static String service_type_new_car_second_type = baseUrl+"new_car_second_type?";
	public final static String service_type_new_car_first_type = baseUrl+"new_car_first_type?";
	public final static String service_type_current_car = baseUrl+"current_car?";
	public final static String service_type_finances = baseUrl+"finances?";
	public final static String service_type_fblogin = baseUrl+"fblogin?";
	public final static String service_type_userlast_activity_data = baseUrl+"last_activity_data?";
	public final static String service_type_user_save_route = baseUrl+"SaveRoute?";
	public final static String service_type_userRecentActivity = baseUrl+"get_maxResult?";
	public final static String service_type_userActivity = baseUrl+"activity_list?";
	public final static String service_type_userroute_list = baseUrl+"route_list?";
	public final static String service_type_usersubroute_list = baseUrl+"getRoute?";
	public final static String service_type_editprofile = baseUrl+"editProfile?";
	public final static String service_type_getNotification = baseUrl+"getDriverRequestList?";
	public final static String service_type_getNotificationaceept = baseUrl+"driverAcceptRequest?";
	public final static String service_type_discovery = baseUrl+"discovery?";
	public final static String service_type_buyer_detail = baseUrl+"buyer_detail?";
	public final static String service_type_notifications = baseUrl+"notifications?";
	public final static String service_type_deal_expire = baseUrl+"deal_expire?";


	public final String and = "&";

	public final static int POST = 1;
	public final static int GET = 2;

	public final static String status = "status";
	public final static String results = "response";
	public final static int OK = 1;
	public final static int NOTOK = 0;

	public final int fromAddressToLatLng = 101;

}
