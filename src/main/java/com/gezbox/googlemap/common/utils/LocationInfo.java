package com.gezbox.googlemap.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.amap.api.location.AMapLocation;
import com.gezbox.googlemap.common.bean.Location;
import com.gezbox.googlemap.constants.PrefConstants;

/**
 * 保存 / 获取上次定位信息
 *
 * @author hanjie
 * @date 2018/4/12
 */

public class LocationInfo {

    /**
     * 获取上次定位的信息
     */
    public static Location getLastLocation(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PrefConstants.FILE_NAME, Context.MODE_PRIVATE);
        Location location = new Location();
        location.setLatitude(Double.parseDouble(preferences.getString(PrefConstants.KEY_LAST_LOCATION_LAT, "31")));
        location.setLongitude(Double.parseDouble(preferences.getString(PrefConstants.KEY_LAST_LOCATION_LNG, "120")));
        location.setAccuracy(preferences.getFloat(PrefConstants.KEY_LAST_LOCATION_ACCURACY, 50));
        location.setPoiName(preferences.getString(PrefConstants.KEY_LAST_LOCATION_ADDRESS, ""));
        location.setAddress(preferences.getString(PrefConstants.KEY_LAST_LOCATION_ADDRESS_DETAILS, ""));
        location.setTime(preferences.getLong(PrefConstants.KEY_LAST_LOCATION_TIME, 0L));
        return location;
    }

    /**
     * 保存定位信息
     */
    public static void saveLastLocation(Context context, AMapLocation aMapLocation) {
        SharedPreferences preferences = context.getSharedPreferences(PrefConstants.FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PrefConstants.KEY_LAST_LOCATION_LAT, String.valueOf(aMapLocation.getLatitude()));
        editor.putString(PrefConstants.KEY_LAST_LOCATION_LNG, String.valueOf(aMapLocation.getLongitude()));
        editor.putFloat(PrefConstants.KEY_LAST_LOCATION_ACCURACY, aMapLocation.getAccuracy());
        editor.putString(PrefConstants.KEY_LAST_LOCATION_ADDRESS, aMapLocation.getPoiName());
        editor.putString(PrefConstants.KEY_LAST_LOCATION_ADDRESS_DETAILS, aMapLocation.getAddress());
        editor.putLong(PrefConstants.KEY_LAST_LOCATION_TIME, aMapLocation.getTime());
        editor.apply();
    }

    /**
     * 判断是否在中国
     *
     * @param context
     * @return
     */
    public static boolean inChina(Context context) {
        Location lastLocation = getLastLocation(context);
        return !MapUtils.outOfChina(lastLocation.getLatitude(), lastLocation.getLongitude());
    }

}
