package com.gezbox.googlemap.common;

/**
 * Created by along on 2017/8/16.
 */

public class LatLng {
    public double lat;
    public double lng;

    public LatLng(double v, double v1) {
        this.lat = v;
        this.lng = v1;
    }

    public boolean isValid() {
        return this.lat != 0f || this.lng != 0f;
    }

}
