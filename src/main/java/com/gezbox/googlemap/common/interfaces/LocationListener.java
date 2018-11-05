package com.gezbox.googlemap.common.interfaces;


import com.gezbox.googlemap.common.bean.Location;

/**
 * Created by along on 2017/8/22.
 */

public interface LocationListener {

    void onLocationChange(Location location);

    void onLocationFailed();

}