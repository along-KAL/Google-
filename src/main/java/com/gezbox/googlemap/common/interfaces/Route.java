package com.gezbox.googlemap.common.interfaces;

import com.gezbox.googlemap.common.LatLng;
import com.gezbox.googlemap.common.bean.RoutePathBean;

/**
 * Created by along on 2017/8/16.
 */

public interface Route {

    interface OnDriverRouteDataCallback {
        void onResult(RoutePathBean routePathBean);
    }

    void searchRoute(String startAddress, String endAddress, OnDriverRouteDataCallback onDriverRouteDataCallback);

    void searchRoute(LatLng startLatlng, LatLng endLatlng, OnDriverRouteDataCallback onDriverRouteDataCallback);

}
