package com.gezbox.googlemap.googlemap.utils;

import android.graphics.Bitmap;
import android.view.View;

import com.gezbox.googlemap.common.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by along on 2017/8/16.
 */

public class GMapUtils {

    public static com.google.android.gms.maps.model.LatLng transformToGLatLng(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new com.google.android.gms.maps.model.LatLng(latLng.lat, latLng.lng);
    }

    public static List<com.google.android.gms.maps.model.LatLng> transformToGLatLng(List<LatLng> latLng) {
        if (latLng == null || latLng.isEmpty()) {
            return null;
        }
        List<com.google.android.gms.maps.model.LatLng> list = new ArrayList<>();
        for (LatLng lng : latLng) {
            list.add(transformToGLatLng(lng));
        }
        return list;
    }

    public static LatLng transformToLatLng(com.google.android.gms.maps.model.LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new LatLng(latLng.latitude, latLng.longitude);
    }


    public static com.google.android.gms.maps.model.LatLng getMinLatLng(List<com.google.android.gms.maps.model.LatLng> points) {
        if (points == null || points.isEmpty()) {
            return null;
        }
        double minLat = points.get(0).latitude;
        double minLng = points.get(0).longitude;

        for (int i = 0; i < points.size(); i++) {
            com.google.android.gms.maps.model.LatLng latLng = points.get(i);
            double tempLat = latLng.latitude;
            double tempLng = latLng.longitude;
            if (minLat > tempLat) {
                minLat = tempLat;
            }
            if (minLng > tempLng) {
                minLng = tempLng;
            }
        }
        return new com.google.android.gms.maps.model.LatLng(minLat, minLng);
    }

    public static com.google.android.gms.maps.model.LatLng getMaxLatLng(List<com.google.android.gms.maps.model.LatLng> points) {
        if (points == null || points.isEmpty()) {
            return null;
        }
        double maxLat = points.get(0).latitude;
        double maxLng = points.get(0).longitude;

        for (int i = 0; i < points.size(); i++) {
            com.google.android.gms.maps.model.LatLng latLng = points.get(i);
            double tempLat = latLng.latitude;
            double tempLng = latLng.longitude;
            if (maxLat < tempLat) {
                maxLat = tempLat;
            }
            if (maxLng < tempLng) {
                maxLng = tempLng;
            }
        }
        return new com.google.android.gms.maps.model.LatLng(maxLat, maxLng);
    }

    public static Bitmap loadBitmapFromView(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

}
