package com.dev.prepcarapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.adapter.NotificationAdapter;
import com.dev.prepcarapplication.baseClasses.BaseFragment;
import com.dev.prepcarapplication.baseClasses.Constants;
import com.dev.prepcarapplication.bean.NotificationModel;
import com.dev.prepcarapplication.networkTask.ApiManager;
import com.dev.prepcarapplication.preference.MySharedPreferences;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by this on 2/16/2017.
 */

public class FragmentNotificationBuyer extends BaseFragment {
    public static String TAG = "NOTIFICATION";
    ArrayList<NotificationModel> arrayListItems;
    SwipeMenuRecyclerView mListNotifications;
    TextView mTvNoNotifications;
    NotificationAdapter adapter;

    public static FragmentNotificationBuyer newInstanse(Bundle bundle) {
        FragmentNotificationBuyer fragment = new FragmentNotificationBuyer();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_notification_buyer, null);
        initUi(view);
        setListener();

        refresh();
        callServiceForNotifications();
        return view;
    }

    private void callServiceForNotifications() {
        ApiManager.getInstance().getNotifications(this);
    }

    @Override
    protected void initUi(View view) {
        arrayListItems = new ArrayList<>();
        mListNotifications = (SwipeMenuRecyclerView) view.findViewById(R.id.list_notifications);
        mTvNoNotifications = (TextView) view.findViewById(R.id.tv_no_notification);

        mListNotifications.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListNotifications.setSwipeMenuCreator(swipeMenuCreator);
        mListNotifications.setSwipeMenuItemClickListener(menuItemClickListener);

       refresh();

    }

    @Override
    protected void setValueOnUi() {

    }

    @Override
    protected void setListener() {


    }


    @Override
    public boolean onBackPressedListener() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    private void refresh() {
        mListNotifications.setAdapter(new NotificationAdapter(getActivity(), arrayListItems));
        MySharedPreferences.getInstance().putBooleanKeyValue(getActivity(), Constants.isUnreadNotification,false);

        if (arrayListItems.size()==0){
            mTvNoNotifications.setVisibility(View.VISIBLE);
        }else{
            mTvNoNotifications.setVisibility(View.GONE);
        }
    }


    public void setrespponce(JSONObject jObject) {
        arrayListItems.clear();
        if (jObject.optInt("status") == 1 && jObject.optInt("dataFlow") == 1) {
            JSONArray data = jObject.optJSONArray("data");
            for (int j = 0; j < data.length(); j++) {
                NotificationModel model = new NotificationModel();
                JSONObject object = data.optJSONObject(j);
                model.setCarplanId(object.optString("carplan_id"));
                model.setNotificationId(object.optInt("notificationId"));
                model.setNotificationType(object.optString("notification_type"));
                model.setUserId(object.optString("userId"));
                model.setMessage(object.optString("message"));
                model.setTime(object.optString("created"));
                arrayListItems.add(model);
            }
            refresh();
        }
    }
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            {
                SwipeMenuItem addItem = new SwipeMenuItem(getActivity())
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("Delete")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeLeftMenu.addMenuItem(addItem);

            }

            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("Delete")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);

            }
        }
    };

    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {

        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getActivity(), "Notification removed from list", Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getActivity(), "Notification removed from list", Toast.LENGTH_SHORT).show();
            }

            if (menuPosition == 0) {
                arrayListItems.remove(adapterPosition);
                refresh();
            }
        }
    };

}
