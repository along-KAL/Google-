package com.gezbox.googlemap.common.interfaces;


import android.graphics.Bitmap;
import android.view.View;

import com.gezbox.googlemap.common.LatLng;
import com.gezbox.googlemap.common.bean.Location;

import java.util.List;

/**
 * Created by along on 2017/8/16.
 */

public interface Map {


    String KEY = "AIzaSyAUiTpv-ferGfYSx04suUrAHqlW1HXI14U";//AIzaSyAuUcxdnXUnDReMjaUqeA99mYnta2IZH4g


    void onDestory();


    /**
     * ====================地图覆盖物======================
     */
    IMarker addMarker(LatLng latLng, Bitmap icon, String title);

    IMarker addMarker(LatLng latLng, Bitmap icon, String title, float anchorX, float anchorY);

    IMarker addMarker(LatLng latLng, int iconResId, String title);

    IMarker addMarker(LatLng latLng, View view, String title);

    IMarker addMarker(LatLng latLng, View view, String title, float anchorX, float anchorY);

    ICircle addCircle(LatLng center, double radiusMeters, int strokeWidth, int strokeColorArgb, int fillColorArgb, boolean clickable);

    IPolygon addPolygon(List<LatLng> points, int strokeWidth, int strokeColorArgb, int fillColorArgb, boolean clickable);

    IPolyline addLine(LatLng beginPoint, LatLng endPoint, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable);

    IPolyline addLine(List<LatLng> points, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable);

    IPolyline addPolyLine(List<LatLng> points, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable);


    /**
     * ====================地图事件======================
     */

    void setOnCameraIdleListener(OnCameraIdleListener onCameraIdleListener);

    void setOnCameraMoveListener(OnCameraMoveListener onCameraMoveListener);

    void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener);

    void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener);

    void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener);

    void setOnMapClickListener(OnMapClickListener onMapClickListener);

    void snapshot(OnSnapshotListener onSnapshotListener);

    void moveToLastLocation();

    interface OnSnapshotListener {
        void onSnapshot(Bitmap bitmap);
    }

    interface OnCameraIdleListener {
        void onCameraIdle();
    }

    interface OnCameraMoveListener {
        void onCameraMove();
    }

    interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }

    interface OnMarkerClickListener {
        void onMarkerClick(IMarker marker);
    }

    interface OnPolylineClickListener {
        void onPolylineClick(IPolyline polyline);
    }

    interface OnMapClickListener {
        void OnMapClick(LatLng latLng);
    }

    /**
     * ====================地图UI操作======================
     */

    void setMyLocationButtonEnabled(boolean isEnable);

    void setCompassEnabled(boolean isEnable);

    void clearMap();

    /**
     * ====================地图定位======================
     */
    void enableMyLocation(boolean enable);

    Location getMyLocation();

    void addLocateClient(LocationClient locationClient);

    void startLocate();

    void stopLocate();

    void showAllDot(List<LatLng> latLng);

    /**
     * ====================相机======================
     */

    void animateCamera(double lat, double lng, int zoomLevel);

    void moveCamera(double lat, double lng, int zoomLevel);

    LatLng getCameraPosition();
}
