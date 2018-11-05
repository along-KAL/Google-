package com.gezbox.googlemap.googlemap.location;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.gezbox.googlemap.common.bean.Location;
import com.gezbox.googlemap.common.interfaces.LocationClient;
import com.gezbox.googlemap.common.interfaces.LocationListener;
import com.gezbox.googlemap.common.utils.LocationInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一使用高德地图的定位功能为谷歌地图/高德地图提供定位信息
 *
 * @author hanjie
 * @date 2018/4/8
 */

public class WindLocationClient implements LocationClient, AMapLocationListener {

    private static final int DEFAULT_LOCATE_INTERVAL = 5 * 1000;

    private Context mContext;
    public AMapLocationClient mLocationClient;
    public AMapLocationClientOption mLocationOption = null;

    private List<LocationListener> mListeners = new ArrayList<>();

    public WindLocationClient(Context context) {
        this.mContext = context;
        initClient();
    }

    private void initClient() {
        mLocationClient = new AMapLocationClient(mContext);
        mLocationOption = new AMapLocationClientOption();
        mLocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(DEFAULT_LOCATE_INTERVAL);
        // 设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    @Override
    public void startLocate() {
        mLocationClient.startLocation();
    }

    @Override
    public void stopLocate() {
        mLocationClient.stopLocation();
    }

    @Override
    public Location getLastLocation() {
        return LocationInfo.getLastLocation(mContext);
    }

    @Override
    public void addLocationListener(LocationListener listener) {
        mListeners.add(listener);
    }

    @Override
    public void setLocateInterval(int interval) {
        mLocationOption.setInterval(interval);
        mLocationClient.setLocationOption(mLocationOption);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 0) {
            //定位成功回调信息，设置相关消息
            Location location = new Location();
            location.setLatitude(aMapLocation.getLatitude());
            location.setLongitude(aMapLocation.getLongitude());
            location.setAccuracy(aMapLocation.getAccuracy());
//            location.setLatitude(30.1850000000);
//            location.setLongitude(120.1830000000);
//            location.setAccuracy(50);
            location.setAddress(aMapLocation.getAddress());
            location.setPoiName(aMapLocation.getPoiName());
            location.setTime(System.currentTimeMillis());
            // 保存定位信息
            LocationInfo.saveLastLocation(mContext, aMapLocation);
            
            for (LocationListener listener : mListeners) {
                listener.onLocationChange(location);
            }

        } else {
            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
            Log.e("AmapError", "location Error, ErrCode:"
                    + aMapLocation.getErrorCode() + ", errInfo:"
                    + aMapLocation.getErrorInfo());
            for (LocationListener listener : mListeners) {
                listener.onLocationFailed();
            }
        }
    }

}
