package com.dev.prepcarapplication.networkTask;

import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.BaseFragmentActivity;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.dev.prepcarapplication.utilities.UtilsClass;

import java.util.HashMap;

public class ApiManager implements URLsClass {

    private static ApiManager apiManager = null;

    public static ApiManager getInstance() {
        if (apiManager == null)
            apiManager = new ApiManager();

        return apiManager;
    }

    public void getLogin(BaseFragment fragment, String username, String pass, String roleid, String deviceToken) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_login};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(Constants.USER_Email, username.trim());
            params.put(Constants.PASSWORD, pass.trim());
            params.put(Constants.role_id, roleid);
            params.put(Constants.device_id, deviceToken);
            params.put(Constants.device_type, "1");
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.login, true);

        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getSignUp(BaseFragment fragment, String devidetoken, String email, String pass, String zipcode, String roleid, String code) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_signUp};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(Constants.USER_Email, email.trim());
            params.put(Constants.PASSWORD, pass.trim());
            params.put(Constants.zip, zipcode);
            params.put(Constants.role_id, roleid);
            params.put(Constants.device_id, devidetoken);
            params.put(Constants.device_type, "1");
            params.put(Constants.uniquecode, code);

            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.signup, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getForgotPass(BaseFragment fragment, String username, String roleid) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_forgot};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(Constants.USER_Email, username.trim());
            params.put(Constants.role_id, roleid);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.forgetPass, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getChangePassword(BaseFragment fragment, String oldpass, String newpass) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_changePassword};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(Constants.oldPassword, oldpass);
            params.put(Constants.newPassword, newpass);
            params.put(Constants.USERID, String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.changPasword, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getEditProfile(BaseFragment fragment,
                               String string, String string2, String string3, String string4,
                               String string5, String string6, String string7, String string8,
                               String string9, String string10) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_editprofile};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("first_name", string);
            params.put("last_name", string2);
            params.put("email", string3);
            params.put("phone", string4);
            params.put("state", string5);
            params.put("city", string6);
            params.put("address", string7);
            params.put("zip", string8);
            params.put("image", string9);
            params.put(Constants.role_id, string10);
            params.put(Constants.USERID, String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.editProfile, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getgetProfile(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_getprofile};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(Constants.USERID, String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getprofile, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getFbLogin(BaseFragment fragment, String string, String string2, String string3, String id, String email) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_fblogin};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("name", string);
            params.put("imageURL", string2);
            params.put("fb_id", string3);
            params.put(Constants.role_id, id);
            params.put(Constants.EMAIL, email);
            params.put(Constants.device_type, "1");
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.fbLogin, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getDiscovery(BaseFragment fragment, String gender, String frstcar, String uses, String hright,
                             String children, String discount, String zipcode, String shopnote) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_discovery};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("identify", gender);
            params.put("first_car", frstcar);
            params.put("main_use", uses);
            params.put("height", hright);
            params.put("small_children", children);
            params.put("discount", discount);
            params.put("zipcode", zipcode);
            params.put("shopping_notes", shopnote);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.discovery, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getYear(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_getyear};
            HashMap<String, String> params = new HashMap<String, String>();
            fragment.serviceCaller(fragment, urlsArr, params, false, false,
                    Constants.year, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getMake(BaseFragment fragment, String year) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_getmake};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("year", year);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.make, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getModel(BaseFragment fragment, String year, String make) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_getmodel};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("year", year);
            params.put("make", make);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.model, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getYear1(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_getyear};
            HashMap<String, String> params = new HashMap<String, String>();
            fragment.serviceCaller(fragment, urlsArr, params, false, false,
                    Constants.year1, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getMake1(BaseFragment fragment, String year) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_getmake};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("year", year);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.make1, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getModel1(BaseFragment fragment, String year, String make) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_getmodel};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("year", year);
            params.put("make", make);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.model1, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getCurrentCar(BaseFragment fragment, String year, String make, String model, String stillowd,
                              String milage, String carcondition, String kbbvalue, String image) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_current_car};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("year", year);
            params.put("make", make);
            params.put("model", model);
            params.put("still_owd", stillowd);
            params.put("mileage", milage);
            params.put("car_condition", carcondition);
            params.put("kbb_value_estimate", kbbvalue);
            params.put("image", image);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.currentcar, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getFiannaces(BaseFragment fragment, String est_monthly_income, String rent, String mortgage, String amount1,
                             String student_loans, String amount2, String est_credit_score, String suggested_monthly_payment,
                             String choose_your_own, String budget, String down_payment, String dob, String doyouneed, String rentamount) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_finances};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("est_monthly_income", est_monthly_income);
            params.put("rent", rent);
            params.put("mortgage", mortgage);
            params.put("amount1", amount1);
            params.put("student_loans", student_loans);
            params.put("amount2", amount2);
            params.put("est_credit_score", est_credit_score);
            params.put("suggested_monthly_payment", suggested_monthly_payment);
            params.put("choose_your_own", choose_your_own);
            params.put("budget", budget);
            params.put("down_payment", down_payment);
            params.put("dob", dob);
            params.put("rent_amount", rentamount);
            params.put("do_you_need_finance", doyouneed);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.finaces, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getUploadNewCar(BaseFragment fragment, String model_year, String make, String model_type, String transmission,
                                String exterior_color, String stock_number, String car_pic, String features) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_car_add};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("model_year", model_year);
            params.put("make", make);
            params.put("model_type", model_type);
            params.put("transmission", transmission);
            params.put("exterior_color", exterior_color);
            params.put("stock_number", stock_number);
            params.put("car_pic", car_pic);
            params.put("features", features);
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.carupload, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getEditNewCar(BaseFragment fragment, String model_year, String make, String model_type, String transmission,
                              String exterior_color, String stock_number, String car_pic, String carid, String features) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_car_edit};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("car_id", carid);
            params.put("model_year", model_year);
            params.put("make", make);
            params.put("model_type", model_type);
            params.put("transmission", transmission);
            params.put("exterior_color", exterior_color);
            params.put("stock_number", stock_number);
            params.put("car_pic", car_pic);
            params.put("features", features);
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.carupload, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getCarList(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_car_list};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.carlist, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getDiscoveryDetail(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_discovery};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getDiscovery, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getFinanceDetail(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_finances};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getFinance, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getCurrentcarDetail(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_current_car};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getCurrentcar, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getIAM(BaseFragment fragment, String year_range, String type, String styles, String mileage,
                       String color, String transmission, String engine_power, String preferences
            , String brand_americans, String brand_asians, String brand_europeans, String maxrange) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_new_car_second_type};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("year_range", year_range);
            params.put("type", type);
            params.put("styles", styles);
            params.put("mileage", mileage);
            params.put("color", color);
            params.put("transmission", transmission);
            params.put("engine_power", engine_power);
            params.put("preferences", preferences);
            params.put("brand_americans", brand_americans);
            params.put("brand_asians", brand_asians);
            params.put("brand_europeans", brand_europeans);
            params.put("year_range_max", maxrange);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getnewcarsecond, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getIAMDetail(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_new_car_second_type};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getIAM, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getIKNOW(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_new_car_first_type};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getIKNOWALL, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getIKNOW(BaseFragment fragment, String specific_model, String new1, String year, String make,
                         String model, String features, String comment, String car_search, String srColor) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_new_car_first_type};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("specific_model", specific_model);
            params.put("new", new1);
            params.put("year", year);
            params.put("make", make);
            params.put("model", model);
            params.put("features", features);
            params.put("comment", comment);
            params.put("car_search", car_search);
            params.put("color", srColor);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getIKNOW, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getNewMatches(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_new_matches};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getNewMATCHES, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getRating(BaseFragment fragment, String dealerid, String rating, String car_id, String carplanId, String deal_id) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_rating};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("dealer_id", dealerid);
            params.put("rating", rating);
            params.put("car_id", car_id);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            params.put("carplan_id", carplanId);
            params.put("deal_id", deal_id);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.setRating, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getFavorites(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            HashMap<String, String> params = new HashMap<String, String>();
            boolean isDealer = MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.role_id, 0) == 3;
            String urlsArr[] = {isDealer ? URLsClass.service_type_sent_deals : URLsClass.service_type_favorites};
            if (isDealer)
                params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            else
                params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));

            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.setfav, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getDeletFav(BaseFragmentActivity fragment, String dealerid, String rating_id) {
        if (UtilsClass.isConnectingToInternet(fragment)) {
            String urlsArr[] = {URLsClass.service_type_deletefavorite};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("dealer_id", dealerid);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment, Constants.USER_ID, 0)));
            params.put("rating_id", rating_id);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.deletefav, true);
        } else {
            UtilsClass.plsStartInternet(fragment);
        }
    }

    public void getDetailNewmaches(BaseFragment fragment, String car_id) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_new_matches_list_detail};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("car_id", car_id);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.detailnewmatches, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getMessage(BaseFragmentActivity fragment, String dealerid, String type, String car_id,
                           String message) {
        if (UtilsClass.isConnectingToInternet(fragment)) {
            String urlsArr[] = {URLsClass.service_type_messages};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("dealer_id", dealerid);
            params.put("type", type);
            params.put("car_id", car_id);
            params.put("message", message);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment, Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.setMessage, true);
        } else {
            UtilsClass.plsStartInternet(fragment);
        }
    }

    public void getFindDelaer(BaseFragment fragment, String miles, String adress) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_find_my_matches};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("miles", miles);
            params.put("address", adress);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.findMatches, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getFindDelaerCount(BaseFragment fragment, String miles, String adress) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_count_my_matches};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("miles", miles);
            params.put("address", adress);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.findMatchesCount, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getBuyerList(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_buyer_list};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getBuyer, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getbuyerdeatil(BaseFragment fragment, String buyerid) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_buyer_detail};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("buyer_id", buyerid);
            params.put("carplan_id", MySharedPreferences.getInstance().getString(fragment.getActivity(), Constants.carplanId, ""));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.getBuyerdetail, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void DeclinedBuyer(BaseFragment fragment, String buyerid, String type, String carid) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_buyer_request};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("car_id", carid);
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            params.put("status", type);
            params.put("buyer_id", buyerid);
            params.put("carplan_id", MySharedPreferences.getInstance().getString(fragment.getActivity(), Constants.carplanId, ""));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.decline, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void acceptRequestcarplan(BaseFragment fragment, String buyerid) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_car_plan_detail};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            params.put("buyer_id", buyerid);
            params.put("carplan_id", MySharedPreferences.getInstance().getString(fragment.getActivity(), Constants.carplanId, ""));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.acceptcarplan, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getVichileList(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_car_list};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.carlist, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void createDeal(BaseFragment fragment, String vichkle, String terms, String disclimer, String price, String description,
                           String month, String interest_rate, String best_price, String salestaxrate, String downpayment
            , String tradeinvalue, String amountontrace, String nickname, String monthly_price) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_create_deal};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("vechicle", vichkle);
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            params.put("lease_deal_disclaimer", disclimer);
            params.put("price", price);
            params.put("lease_deal_trems", terms);
            params.put("description", description);
            params.put("months", month);
            params.put("interest_rate", interest_rate);
            params.put("best_price", best_price);
            params.put("down_payment", downpayment);
            params.put("sales_taxrate", salestaxrate);
            params.put("trade_in_value", tradeinvalue);
            params.put("amount_on_trace", amountontrace);
            params.put("nickname", nickname);
            params.put("monthly_payment", monthly_price);
            params.put("carplan_id", MySharedPreferences.getInstance().getString(fragment.getActivity(), Constants.carplanId, ""));

            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.createdeal, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getdealdetailt(BaseFragment fragment, String dealid) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_deal_detail};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("deal_id", dealid);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.dealdeatil, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void senddealdetailt(BaseFragment fragment, String dealid, String buyerid) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_deal_send};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("deal_id", dealid);
            params.put("buyer_id", buyerid);
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            params.put("carplan_id", MySharedPreferences.getInstance().getString(fragment.getActivity(), Constants.carplanId, ""));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.senddeatil, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getDeletCar(BaseFragmentActivity fragment, String carid) {
        if (UtilsClass.isConnectingToInternet(fragment)) {
            String urlsArr[] = {URLsClass.service_type_car_delete};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("car_id", carid);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.deletecar, true);
        } else {
            UtilsClass.plsStartInternet(fragment);
        }
    }

    public void getCarDetail(BaseFragment fragment, String carid) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_car_detail};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("car_id", carid);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.carDetail, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getNotifications(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_notifications};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("user_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.notifications, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getDealerSent(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            HashMap<String, String> params = new HashMap<String, String>();
            String urlsArr[] = {URLsClass.service_type_sent_deals};
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            params.put("carplan_id", MySharedPreferences.getInstance().getString(fragment.getActivity(), Constants.carplanId, ""));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.dealerSent, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void getDealerFavourite(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            HashMap<String, String> params = new HashMap<String, String>();
            String urlsArr[] = {URLsClass.service_type_dealer_favorites};

            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            params.put("buyer_id", MySharedPreferences.getInstance().getString(fragment.getActivity(), Constants.buyerid, ""));

            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.dealerFavourite, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }
    public void getDealerActivity(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            HashMap<String, String> params = new HashMap<String, String>();
            String urlsArr[] = {URLsClass.service_type_dealer_activity};

            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));

            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.dealerActivity, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }
    public void contactUs(BaseFragment fragment, String name, String email, String msg) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            HashMap<String, String> params = new HashMap<String, String>();
            String urlsArr[] = {URLsClass.service_type_contact_us};

            params.put("user_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            params.put("name", name);
            params.put("email", email);
            params.put("msg", msg);

            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.contactUs, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }
    public void testDrive(BaseFragmentActivity fragment, String dealerid,String deal_id,String date, String time, String carplan_id) {
        if (UtilsClass.isConnectingToInternet(fragment)) {
            HashMap<String, String> params = new HashMap<String, String>();
            String urlsArr[] = {URLsClass.service_type_test_drive};

            params.put("dealer_id", dealerid);
            params.put("buyer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment, Constants.USER_ID, 0)));
            params.put("deal_id", deal_id);
            params.put("date", date);
            params.put("time", time);
            params.put("carplan_id", carplan_id);


            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.testDrive, true);
        } else {
            UtilsClass.plsStartInternet(fragment );
        }
    }

    public void getAllScheduledTestDrive(BaseFragment fragment) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_all_test_drive};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("dealer_id", String.valueOf(MySharedPreferences.getInstance().getInteger(fragment.getActivity(), Constants.USER_ID, 0)));
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.allTestDrive, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }

    public void testDriveRequest(BaseFragment fragment,String testdrive_id,String status) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_test_drive_request};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("testdrive_id", testdrive_id);
            params.put("status", status);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.testDriveRequest, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }
    public void testDriveAppointmentStatus(BaseFragment fragment,String deal_id,String status) {
        if (UtilsClass.isConnectingToInternet(fragment.getActivity())) {
            String urlsArr[] = {URLsClass.service_type_test_drive_appointment_status};
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("deal_id", deal_id);
            params.put("appointment_status", status);
            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.testDriveAppontmentStatus, true);
        } else {
            UtilsClass.plsStartInternet(fragment.getActivity());
        }
    }
    public void DealExpire(BaseFragmentActivity fragment, String deal_id, String status) {
        if (UtilsClass.isConnectingToInternet(fragment)) {
            HashMap<String, String> params = new HashMap<String, String>();
            String urlsArr[] = {URLsClass.service_type_deal_expire};

            params.put("deal_id", deal_id);
            params.put("status", status);

            fragment.serviceCaller(fragment, urlsArr, params, true, false,
                    Constants.testDrive, true);
        } else {
            UtilsClass.plsStartInternet(fragment );
        }
    }
}
