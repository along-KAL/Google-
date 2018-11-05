package com.gezbox.googlemap.amap.utils;

import com.gezbox.googlemap.common.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author hanjie
 * @date 2018/4/13
 */

public class AMapUtils {

    public static com.amap.api.maps.model.LatLng transformToGLatLng(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new com.amap.api.maps.model.LatLng(latLng.lat, latLng.lng);
    }

    public static List<com.amap.api.maps.model.LatLng> transformToGLatLng(List<LatLng> latLng) {
        if (latLng == null || latLng.isEmpty()) {
            return null;
        }
        List<com.amap.api.maps.model.LatLng> list = new ArrayList<>();
        for (LatLng lng : latLng) {
            list.add(transformToGLatLng(lng));
        }
        return list;
    }

    public static com.amap.api.maps.model.LatLng getMinLatLng(List<com.amap.api.maps.model.LatLng> points) {
        if (points == null || points.isEmpty()) {
            return null;
        }
        double minLat = points.get(0).latitude;
        double minLng = points.get(0).longitude;

        for (int i = 0; i < points.size(); i++) {
            com.amap.api.maps.model.LatLng latLng = points.get(i);
            double tempLat = latLng.latitude;
            double tempLng = latLng.longitude;
            if (minLat > tempLat) {
                minLat = tempLat;
            }
            if (minLng > tempLng) {
                minLng = tempLng;
            }
        }
        return new com.amap.api.maps.model.LatLng(minLat, minLng);
    }

    public static com.amap.api.maps.model.LatLng getMaxLatLng(List<com.amap.api.maps.model.LatLng> points) {
        if (points == null || points.isEmpty()) {
            return null;
        }
        double maxLat = points.get(0).latitude;
        double maxLng = points.get(0).longitude;

        for (int i = 0; i < points.size(); i++) {
            com.amap.api.maps.model.LatLng latLng = points.get(i);
            double tempLat = latLng.latitude;
            double tempLng = latLng.longitude;
            if (maxLat < tempLat) {
                maxLat = tempLat;
            }
            if (maxLng < tempLng) {
                maxLng = tempLng;
            }
        }
        return new com.amap.api.maps.model.LatLng(maxLat, maxLng);
    }

}
