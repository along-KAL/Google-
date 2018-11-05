package com.gezbox.googlemap.googlemap.route;

import android.os.Handler;
import android.os.Message;

import com.gezbox.googlemap.common.LatLng;
import com.gezbox.googlemap.common.interfaces.Route;
import com.gezbox.googlemap.common.bean.RoutePathBean;
import com.gezbox.googlemap.common.utils.NetUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.gezbox.googlemap.common.interfaces.Map.KEY;

/**
 * Created by along on 2017/8/16.
 */

public class GDriverRoute implements Route {

    private OnDriverRouteDataCallback mOnDriverRouteDataCallback;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1 && mOnDriverRouteDataCallback != null) {
                mOnDriverRouteDataCallback.onResult((RoutePathBean) msg.obj);
            }
        }
    };

    @Override
    public void searchRoute(String startAddress, String endAddress, OnDriverRouteDataCallback onDriverRouteDataCallback) {
        this.mOnDriverRouteDataCallback = onDriverRouteDataCallback;
        String url = null;
        try {
            url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + URLEncoder.encode(startAddress, "UTF-8") + "&destination=" + URLEncoder.encode(endAddress, "UTF-8") + "&key=" + KEY;
            //Log.d("flagg", "searchRoute: "+url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String finalUrl = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                String json = NetUtil.get(finalUrl);
                //Log.d("flagg", "searchRoute: "+json);
                Gson gson = new Gson();
                RoutePathBean routePath = gson.fromJson(json, RoutePathBean.class);
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = routePath;
                mHandler.sendMessage(msg);
            }
        }.start();

    }

    @Override
    public void searchRoute(LatLng startLatlng, LatLng endLatlng, OnDriverRouteDataCallback onDriverRouteDataCallback) {
        String startAddress = startLatlng.lat + "," + startLatlng.lng;
        String endAddress = endLatlng.lat + "," + endLatlng.lng;
        searchRoute(startAddress, endAddress, onDriverRouteDataCallback);
    }


}
