package com.gezbox.googlemap.amap.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.gezbox.googlemap.R;
import com.gezbox.googlemap.amap.AMapWrapper;
import com.gezbox.googlemap.common.interfaces.BaseMapFragment;


public class AMapFragment extends BaseMapFragment {
    private final static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private MapView mMapView;
    private Context mContext;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;
    private AMapWrapper mAMapWrapper;

    public static BaseMapFragment newInstance() {
        return new AMapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_amap, container, false);
        mContext = getActivity();
        mMapView = view.findViewById(R.id.a_map);
        mMapView.onCreate(savedInstanceState);
        AMap map = mMapView.getMap();
        // 隐藏地图自带的 + - 缩放按钮
        map.getUiSettings().setZoomControlsEnabled(false);
        mAMapWrapper = new AMapWrapper(map, mContext.getApplicationContext());
        if (mOnMapReadyCallback != null) {
            mOnMapReadyCallback.onMapReady(mAMapWrapper);
        }
        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mAMapWrapper.onDestory();
    }

}
