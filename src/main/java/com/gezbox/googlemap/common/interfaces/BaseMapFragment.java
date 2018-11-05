package com.gezbox.googlemap.common.interfaces;

import android.support.v4.app.Fragment;

/**
 * Created by along on 2017/8/16.
 */

public abstract class BaseMapFragment extends Fragment {
    public interface OnMapReadyCallback {
        void onMapReady(Map map);
    }

    public OnMapReadyCallback mOnMapReadyCallback;

    public void setOnMapReadyCallback(OnMapReadyCallback onMapReadyCallback) {
        this.mOnMapReadyCallback = onMapReadyCallback;
    }
}
