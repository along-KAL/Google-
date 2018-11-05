package com.gezbox.googlemap.common.utils;

import com.gezbox.googlemap.common.LatLng;

/**
 * Created by along on 2017/11/8.
 * <p>
 * GPS WGS84   中国坐标系是 GCJ02
 */

public class MapUtils {

    /**
     * 克拉索夫斯基椭球参数长半轴a
     */
    private static double a = 6378245.0;
    /**
     * 克拉索夫斯基椭球参数第一偏心率平方
     */
    private static double ee = 0.00669342162296594323;

    /**
     * 计算两点距离 单位（米）
     *
     * @param start
     * @param end
     */
    public static int getDistance(LatLng start, LatLng end) {
        LatLng latLng1 = new LatLng(start.lat, start.lng);
        LatLng latLng2 = new LatLng(end.lat, end.lng);
        return (int) Math.floor(SphericalUtil.computeDistanceBetween(latLng1, latLng2));
    }

    /**
     * GPS转火星
     */
    public static double[] WGS2GCJ(double lat, double lng) {
        if (!MapUtils.outOfChina(lat, lng)) {
            double[] location = new double[2];
            if (outOfChina(lat, lng)) {
                location[0] = lat;
                location[1] = lng;
                return location;
            }
            double dLat = transformLat(lng - 105.0, lat - 35.0);
            double dLon = transformLon(lng - 105.0, lat - 35.0);
            double radLat = lat / 180.0 * Math.PI;
            double magic = Math.sin(radLat);
            magic = 1 - ee * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
            dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * Math.PI);
            location[0] = lat + dLat;
            location[1] = lng + dLon;
            return location;
        } else {
            return new double[]{lat, lng};
        }
    }

    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y * Math.PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x / 30.0 * Math.PI)) * 2.0 / 3.0;
        return ret;
    }

    public static double calDistance(double ori_lat, double ori_lon, double des_lat, double des_lon) {
        int radius = 6371;// # km

        double dlat = Math.toRadians(des_lat - ori_lat);
        double dlon = Math.toRadians(des_lon - ori_lon);
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(Math.toRadians(ori_lat)) * Math.cos(Math.toRadians(ori_lat)) * Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = radius * c;
        return d;
    }

}
