package com.gezbox.googlemap.googlemap.poisearch;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.gezbox.googlemap.common.bean.POIBean;
import com.gezbox.googlemap.common.interfaces.POISearch;
import com.gezbox.googlemap.common.utils.NetUtil;
import com.google.gson.Gson;

import static com.gezbox.googlemap.common.interfaces.Map.KEY;

/**
 * Created by along on 2017/8/17.
 */

public class GPOISearch implements POISearch {

    private OnPOISearchDataCallback mOnPOISearchDataCallback;
    private OnSearchPlaceByKeywordCallback mOnSearchPlaceByKeywordCallback;


    private volatile static GPOISearch mGPOISearch;

    public static GPOISearch getInstance() {
        if (mGPOISearch == null) {
            synchronized (GPOISearch.class) {
                if (mGPOISearch == null) {
                    mGPOISearch = new GPOISearch();
                }
            }
        }
        return mGPOISearch;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1 && mOnPOISearchDataCallback != null) {
                mOnPOISearchDataCallback.onResult((POIBean) msg.obj);
            } else if (msg.what == 2 && mOnSearchPlaceByKeywordCallback != null) {
                mOnSearchPlaceByKeywordCallback.onResult((POIBean) msg.obj);
            }
        }
    };

    @Override
    public void searchPOI(double lat, double lng, OnPOISearchDataCallback onPOISearchDataCallback) {
        this.mOnPOISearchDataCallback = onPOISearchDataCallback;
        String url = null;
        url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?language=zh-CN&location=" + lat + "," + lng + "&types=" + "point_of_interest" + "&rankby=" + "distance" + "&sensor=" + false + "&key=" + KEY;
        Log.e("bingo", "searchPOI url:" + url);
        final String finalUrl = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                String json = NetUtil.get(finalUrl);
                Gson gson = new Gson();
                POIBean pOIBean = gson.fromJson(json, POIBean.class);
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = pOIBean;
                mHandler.sendMessage(msg);
            }
        }.start();

    }


    @Override
    public void searchPlaceByKeyword(String query, OnSearchPlaceByKeywordCallback onSearchPlaceByKeywordCallback) {
        this.mOnSearchPlaceByKeywordCallback = onSearchPlaceByKeywordCallback;
        String url = null;
        url = "https://maps.googleapis.com//maps/api/place/textsearch/json?language=zh-CN&query=" + query + "&key=" + KEY;
        Log.e("bingo", "searchPlaceByKeyword url:" + url);
        final String finalUrl = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                String json = NetUtil.get(finalUrl);
                Gson gson = new Gson();
                POIBean pOIBean = gson.fromJson(json, POIBean.class);
                Message msg = Message.obtain();
                msg.what = 2;
                msg.obj = pOIBean;
                mHandler.sendMessage(msg);
            }
        }.start();
    }
}
