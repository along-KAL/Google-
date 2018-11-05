package com.gezbox.googlemap.common.interfaces;

import com.gezbox.googlemap.common.bean.Location;

/**
 * Created by along on 2017/8/16.
 */

public interface LocationClient {

    void startLocate();

    void stopLocate();

    Location getLastLocation();

    void addLocationListener(LocationListener locationListener);

    void setLocateInterval(int interval);

}
