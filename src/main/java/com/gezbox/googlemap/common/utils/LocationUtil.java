package com.gezbox.googlemap.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.gezbox.googlemap.common.bean.Location;
import com.gezbox.googlemap.constants.PrefConstants;

/**
 * Description
 *
 * @author hanjie
 * @date 2018/4/8
 */

public class LocationUtil {

    public static Location getLastLocation(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PrefConstants.FILE_NAME, Context.MODE_PRIVATE);
        Location location = new Location();
        location.setLatitude(Double.parseDouble(sp.getString(PrefConstants.KEY_LAST_LOCATION_LAT, "30.184166")));
        location.setLongitude(Double.parseDouble(sp.getString(PrefConstants.KEY_LAST_LOCATION_LNG, "120.181465")));
        location.setAddress(sp.getString(PrefConstants.KEY_LAST_LOCATION_ADDRESS, ""));
        return location;
    }

    /**
     * 将 android.location.Location 转换为封装的 Location
     * <p>
     * 注：由于 Google 地图定位源转接原因，定位客户端使用网络定位到的 Location 需要先转换成 android.location.Location(不包含 address 字段存放位置详细地址信息)
     * 传递给 Google 地图，然后当 Google 地图回调时又需要将此 android.location.Location 转换为我们自己封装的 Location，所以为了转换中间详细位置不丢失，借用了 Bundle 临时携带
     */
    public static Location transformLocation(android.location.Location l) {
        Location location = new Location();
        location.setLatitude(l.getLatitude());
        location.setLongitude(l.getLongitude());
        location.setAccuracy(l.getAccuracy());
        Bundle bundle = l.getExtras();
        if (bundle != null) {
            location.setPoiName(bundle.getString("extra_address", ""));
            location.setAddress(bundle.getString("extra_address_details", ""));
            location.setTime(bundle.getLong("extra_address_time", 0L));
        }
        return location;
    }

    /**
     * 将 Location 转换为封装的 android.location.Location
     * <p>
     * 注：由于 Google 地图定位源转接原因，定位客户端使用网络定位到的 Location 需要先转换成 android.location.Location(不包含 address 字段存放位置详细地址信息)
     * 传递给 Google 地图，然后当 Google 地图回调时又需要将此 android.location.Location 转换为我们自己封装的 Location，所以为了转换中间详细位置不丢失，借用了 Bundle 临时携带
     */
    public static android.location.Location transformLocation(Location l) {
        android.location.Location location = new android.location.Location("");
        location.setLatitude(l.getLatitude());
        location.setLongitude(l.getLongitude());
        location.setAccuracy(l.getAccuracy());
        Bundle bundle = new Bundle();
        bundle.putString("extra_address", l.getPoiName());
        bundle.putString("extra_address_details", l.getAddress());
        bundle.putLong("extra_address_time", l.getTime());
        location.setExtras(bundle);
        return location;
    }

}
