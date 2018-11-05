package com.gezbox.googlemap.googlemap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.gezbox.googlemap.common.LatLng;
import com.gezbox.googlemap.common.bean.Location;
import com.gezbox.googlemap.common.interfaces.ICircle;
import com.gezbox.googlemap.common.interfaces.IMarker;
import com.gezbox.googlemap.common.interfaces.IPolygon;
import com.gezbox.googlemap.common.interfaces.IPolyline;
import com.gezbox.googlemap.common.interfaces.LocationClient;
import com.gezbox.googlemap.common.interfaces.LocationListener;
import com.gezbox.googlemap.common.interfaces.Map;
import com.gezbox.googlemap.common.utils.LocationUtil;
import com.gezbox.googlemap.googlemap.overlay.GMapOverlay;
import com.gezbox.googlemap.googlemap.utils.GMapUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by along on 2017/8/16.
 */

public class GMapWrapper implements Map, GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnMyLocationChangeListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnPolylineClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private GMapOverlay mGMapOverlay;
    private Context mContext;
    private List<Marker> mMarkers = new ArrayList<>();
    private List<Polyline> mPolylines = new ArrayList<>();
    private GMarkerWrapper mGMarkerWraper;
    private GPolylineWrapper mGPolylineWraper;
    private LocationSource.OnLocationChangedListener mSourceListener;
    private LocationClient mLocationClient;

    public GMapWrapper(GoogleMap map, Context context) {
        this.mMap = map;
        this.mContext = context;
        mGMapOverlay = new GMapOverlay(mMap);
    }

    @Override
    public void onDestory() {

    }

    /**
     * ==================== 地图覆盖物 ======================
     */
    @Override
    public IMarker addMarker(LatLng latLng, Bitmap icon, String title) {
        return addMarker(latLng, icon, title, 0.5f, 0.5f);
    }

    @Override
    public IMarker addMarker(LatLng latLng, Bitmap icon, String title, float anchorX, float anchorY) {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(icon);
        Marker marker = mGMapOverlay.addMarker(new MarkerOptions().position(GMapUtils.transformToGLatLng(latLng)).title(title).icon(bitmapDescriptor).anchor(anchorX, anchorY));
        return new GMarkerWrapper(marker);
    }

    @Override
    public IMarker addMarker(LatLng latLng, int iconResId, String title) {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(mContext.getResources(), iconResId));
        Marker marker = mGMapOverlay.addMarker(new MarkerOptions().position(GMapUtils.transformToGLatLng(latLng)).title(title).icon(bitmapDescriptor).anchor(0.5f, 0.5f));
        return new GMarkerWrapper(marker);
    }

    @Override
    public IMarker addMarker(LatLng latLng, View view, String title) {
        Bitmap bitmap = GMapUtils.loadBitmapFromView(view);
        if (bitmap == null) {
            return null;
        }
        return addMarker(latLng, bitmap, title);
    }

    @Override
    public IMarker addMarker(LatLng latLng, View view, String title, float anchorX, float anchorY) {
        Bitmap bitmap = GMapUtils.loadBitmapFromView(view);
        if (bitmap == null) {
            return null;
        }
        return addMarker(latLng, bitmap, title, anchorX, anchorY);
    }

    @Override
    public ICircle addCircle(LatLng center, double radiusMeters, int strokeWidth, int strokeColorArgb, int fillColorArgb, boolean clickable) {
        Circle circle = mGMapOverlay.addCircle(GMapUtils.transformToGLatLng(center), radiusMeters, strokeWidth, strokeColorArgb, fillColorArgb, clickable);
        return new GCircleWrapper(circle);
    }

    @Override
    public IPolygon addPolygon(List<LatLng> points, int strokeWidth, int strokeColorArgb, int fillColorArgb, boolean clickable) {
        Polygon polygon = mGMapOverlay.addPolygon(GMapUtils.transformToGLatLng(points), strokeWidth, strokeColorArgb, fillColorArgb, clickable);
        return new GPolygonWrapper(polygon);
    }

    @Override
    public IPolyline addLine(LatLng beginPoint, LatLng endPoint, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable) {
        List<com.google.android.gms.maps.model.LatLng> list = new ArrayList<>();
        list.add(GMapUtils.transformToGLatLng(beginPoint));
        list.add(GMapUtils.transformToGLatLng(endPoint));
        Polyline polyline = mGMapOverlay.addPolyline(list, strokeWidth, strokeColorArgb, geodesic, clickable);
        return new GPolylineWrapper(polyline);
    }

    @Override
    public IPolyline addLine(List<LatLng> points, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable) {
        Polyline polyline = mGMapOverlay.addPolyline(GMapUtils.transformToGLatLng(points), strokeWidth, strokeColorArgb, geodesic, clickable);
        return new GPolylineWrapper(polyline);
    }

    @Override
    public IPolyline addPolyLine(List<LatLng> points, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable) {
        Polyline polyline = mGMapOverlay.addPolyline(GMapUtils.transformToGLatLng(points), strokeWidth, strokeColorArgb, geodesic, clickable);
        return new GPolylineWrapper(polyline);
    }

    /**
     * ====================地图事件======================
     */
    private OnCameraIdleListener mOnCameraIdleListener;
    private OnCameraMoveListener mOnCameraMoveListener;
    private OnMyLocationChangeListener mOnMyLocationChangeListener;
    private OnMarkerClickListener mOnMarkerClickListener;
    private OnPolylineClickListener mOnPolylineClickListener;
    private OnMapClickListener mOnMapClickListener;

    @Override
    public void setOnCameraIdleListener(OnCameraIdleListener onCameraIdleListener) {
        this.mOnCameraIdleListener = onCameraIdleListener;
        mMap.setOnCameraIdleListener(this);
    }

    @Override
    public void setOnCameraMoveListener(OnCameraMoveListener onCameraMoveListener) {
        this.mOnCameraMoveListener = onCameraMoveListener;
        mMap.setOnCameraMoveListener(this);
    }

    @Override
    public void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        this.mOnMyLocationChangeListener = onMyLocationChangeListener;
        mMap.setOnMyLocationChangeListener(this);
    }

    @Override
    public void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        this.mOnMarkerClickListener = onMarkerClickListener;
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        this.mOnPolylineClickListener = onPolylineClickListener;
        mMap.setOnPolylineClickListener(this);
    }

    @Override
    public void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        this.mOnMapClickListener = onMapClickListener;
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void snapshot(final OnSnapshotListener onSnapshotListener) {
        mMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
                onSnapshotListener.onSnapshot(bitmap);
            }
        });
    }

    @Override
    public void moveToLastLocation() {
        Location lastLocation = LocationUtil.getLastLocation(mContext);
        moveCamera(lastLocation.getLatitude(), lastLocation.getLongitude(), 17);
    }

    @Override
    public void onCameraIdle() {
        if (mOnCameraIdleListener == null) {
            return;
        }
        mOnCameraIdleListener.onCameraIdle();
    }

    @Override
    public void onCameraMove() {
        if (mOnCameraMoveListener == null) {
            return;
        }
        mOnCameraMoveListener.onCameraMove();
    }

    @Override
    public void onMyLocationChange(android.location.Location location) {
        if (mOnMyLocationChangeListener == null) {
            return;
        }
        mOnMyLocationChangeListener.onMyLocationChange(LocationUtil.transformLocation(location));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (mOnMarkerClickListener == null) {
            return false;
        }
        if (!mMarkers.contains(marker)) {
            mMarkers.add(marker);
            mGMarkerWraper = new GMarkerWrapper(marker);
        } else {
            mGMarkerWraper.with(marker);
        }
        mOnMarkerClickListener.onMarkerClick(mGMarkerWraper);
        return false;
    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        if (mOnPolylineClickListener == null) {
            return;
        }
        if (!mPolylines.contains(polyline)) {
            mPolylines.add(polyline);
            mGPolylineWraper = new GPolylineWrapper(polyline);
        } else {
            mGPolylineWraper.with(polyline);
        }
        mOnPolylineClickListener.onPolylineClick(mGPolylineWraper);
    }

    @Override
    public void onMapClick(com.google.android.gms.maps.model.LatLng latLng) {
        mOnMapClickListener.OnMapClick(GMapUtils.transformToLatLng(latLng));
    }

    /**
     * ====================地图UI操作======================
     */
    @Override
    public void setMyLocationButtonEnabled(boolean isEnable) {
        mMap.getUiSettings().setMyLocationButtonEnabled(isEnable);
    }

    @Override
    public void setCompassEnabled(boolean isEnable) {
        mMap.getUiSettings().setCompassEnabled(isEnable);
    }

    @Override
    public void clearMap() {
        mMap.clear();
    }


    /**
     * ====================地图定位======================
     */
    @Override
    public void enableMyLocation(boolean enable) {
        if (enable) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission to access the location is missing.
            } else if (mMap != null) {
                // Access to the location has been granted to the app.
                mMap.setMyLocationEnabled(true);
            }
        } else {
            mMap.setMyLocationEnabled(false);
        }
    }

    @Override
    @Deprecated
    public Location getMyLocation() {
        return LocationUtil.transformLocation(mMap.getMyLocation());
    }

    @Override
    public void addLocateClient(final LocationClient locationClient) {
        mLocationClient = locationClient;
        mMap.setLocationSource(new LocationSource() {
            @Override
            public void activate(OnLocationChangedListener onLocationChangedListener) {
                mSourceListener = onLocationChangedListener;
            }

            @Override
            public void deactivate() {
                mSourceListener = null;
            }
        });

        locationClient.addLocationListener(new LocationListener() {
            @Override
            public void onLocationChange(Location location) {
                if (mSourceListener != null) {
                    mSourceListener.onLocationChanged(LocationUtil.transformLocation(location));
                }
            }

            @Override
            public void onLocationFailed() {

            }
        });
    }

    @Override
    public void startLocate() {
        mLocationClient.startLocate();
    }

    @Override
    public void stopLocate() {
        mLocationClient.stopLocate();
    }

    /**
     * 显示所有点
     *
     * @param points
     */
    @Override
    public void showAllDot(List<LatLng> points) {
//        float zoomLevel = -0.5f;
//        List<com.google.android.gms.maps.model.LatLng> tempPoints = GMapUtils.transformToGLatLng(points);
//        if (points == null || points.isEmpty()) {
//            return;
//        }
//        if (points.size() == 1) {
//            zoomLevel = -10;
//        }
//        LatLngBounds AUSTRALIA = new LatLngBounds(
//                GMapUtils.getMinLatLng(tempPoints), GMapUtils.getMaxLatLng(tempPoints));
//
//        final float finalZoomLevel = zoomLevel;
//        changeCamera(CameraUpdateFactory.newLatLngBounds(AUSTRALIA, 300));
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        for (LatLng point : points) {
            boundsBuilder.include(new com.google.android.gms.maps.model.LatLng(point.lat, point.lng));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 300));
    }


    /**
     * ====================相机======================
     */
    @Override
    public void animateCamera(double lat, double lng, int zoomLevel) {
        com.google.android.gms.maps.model.LatLng latLng = new com.google.android.gms.maps.model.LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel);
        mMap.animateCamera(cameraUpdate);
    }

    @Override
    public void moveCamera(double lat, double lng, int zoomLevel) {
        com.google.android.gms.maps.model.LatLng latLng = new com.google.android.gms.maps.model.LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel);
        mMap.moveCamera(cameraUpdate);
    }

    private void changeCamera(CameraUpdate update) {
        mMap.animateCamera(update);
    }

    @Override
    public LatLng getCameraPosition() {
        com.google.android.gms.maps.model.LatLng target = mMap.getCameraPosition().target;
        return new LatLng(target.latitude, target.longitude);
    }


}
