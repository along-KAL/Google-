package com.gezbox.googlemap.common;

import android.content.Context;

import com.gezbox.googlemap.amap.poisearch.APOISearch;
import com.gezbox.googlemap.amap.view.AMapFragment;
import com.gezbox.googlemap.common.interfaces.BaseMapFragment;
import com.gezbox.googlemap.common.interfaces.LocationClient;
import com.gezbox.googlemap.common.interfaces.POISearch;
import com.gezbox.googlemap.common.utils.LocationInfo;
import com.gezbox.googlemap.googlemap.location.WindLocationClient;
import com.gezbox.googlemap.googlemap.poisearch.GPOISearch;
import com.gezbox.googlemap.googlemap.route.GDriverRoute;
import com.gezbox.googlemap.googlemap.view.GMapFragment;

/**
 * Created by along on 2017/8/16.
 */

public class MapManager {

    public static BaseMapFragment getMapFragment(Context context, int location) {
        boolean inChina = LocationInfo.inChina(context);
        //#0代表 GPS 1代表 香港  2代表利雅得
        if (location == 1) {
            return AMapFragment.newInstance();
        } else if (location == 2) {
            return GMapFragment.newInstance();
        } else {
            if (inChina) {
                return AMapFragment.newInstance();
            } else {
                return GMapFragment.newInstance();
            }
        }
    }


    public static POISearch getPOISearch(Context context, int location) {
        boolean inChina = LocationInfo.inChina(context);
        //#0代表 GPS 1代表 香港  2代表利雅得
        if (location == 1) {
            return APOISearch.getInstance(context);
        } else  if (location == 2) {
            return GPOISearch.getInstance();
        } else {
            if (inChina) {
                return APOISearch.getInstance(context);
            } else {
                return GPOISearch.getInstance();
            }
        }
    }

    /**
     * 定位客户端
     *
     * @return
     */
    public static LocationClient getLocationClient(Context context) {
        return new WindLocationClient(context);
    }

    public static GDriverRoute getDriverRoute() {
        return new GDriverRoute();
    }
}
