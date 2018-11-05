package com.gezbox.googlemap.amap;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.gezbox.googlemap.R;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by along on 2017/8/16.
 * <p>
 * 部分功能未实现，可以边写边实现
 */

public class AMapWrapper implements Map, AMap.OnCameraChangeListener, AMap.OnMyLocationChangeListener {

    private AMap mMap;
    private Context mContext;
    private LocationClient mLocationClient;

    public AMapWrapper(AMap map, Context context) {
        this.mMap = map;
        this.mContext = context;
    }

    @Override
    public void onDestory() {
        if (mLocationClient != null) {
            mLocationClient.stopLocate();
        }
    }

    /**
     * ==================== 地图覆盖物 ======================
     */
    @Override
    public IMarker addMarker(LatLng latLng, Bitmap icon, String title) {
        return null;
    }

    @Override
    public IMarker addMarker(LatLng latLng, Bitmap icon, String title, float anchorX, float anchorY) {
        MarkerOptions options = new MarkerOptions();
        options.position(new com.amap.api.maps.model.LatLng(latLng.lat, latLng.lng));
        options.title(title);
        options.anchor(anchorX, anchorY);
        options.icon(BitmapDescriptorFactory.fromBitmap(icon));
        return new AMarkerWrapper(mMap.addMarker(options));
    }

    @Override
    public IMarker addMarker(LatLng latLng, int iconResId, String title) {
        MarkerOptions options = new MarkerOptions();
        options.position(new com.amap.api.maps.model.LatLng(latLng.lat, latLng.lng));
        options.title(title);
        options.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), iconResId)));
        return new AMarkerWrapper(mMap.addMarker(options));
    }

    @Override
    public IMarker addMarker(LatLng latLng, View view, String title) {
        MarkerOptions options = new MarkerOptions();
        options.position(new com.amap.api.maps.model.LatLng(latLng.lat, latLng.lng));
        options.title(title);
        options.icon(BitmapDescriptorFactory.fromView(view));
        return new AMarkerWrapper(mMap.addMarker(options));
    }

    @Override
    public IMarker addMarker(LatLng latLng, View view, String title, float anchorX, float anchorY) {
        MarkerOptions options = new MarkerOptions();
        options.position(new com.amap.api.maps.model.LatLng(latLng.lat, latLng.lng));
        options.title(title);
        options.anchor(anchorX, anchorY);
        options.icon(BitmapDescriptorFactory.fromView(view));
        return new AMarkerWrapper(mMap.addMarker(options));
    }

    @Override
    public ICircle addCircle(LatLng center, double radiusMeters, int strokeWidth, int strokeColorArgb, int fillColorArgb, boolean clickable) {
        CircleOptions options = new CircleOptions();
        options.center(new com.amap.api.maps.model.LatLng(center.lat, center.lng));
        options.radius(radiusMeters);
        options.strokeWidth(strokeWidth);
        options.strokeColor(strokeColorArgb);
        options.fillColor(fillColorArgb);
        return new ACircleWrapper(mMap.addCircle(options));
    }

    @Override
    public IPolygon addPolygon(List<LatLng> points, int strokeWidth, int strokeColorArgb, int fillColorArgb, boolean clickable) {
        return null;
    }

    @Override
    public IPolyline addLine(LatLng beginPoint, LatLng endPoint, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable) {
        List<LatLng> points = new ArrayList<>();
        points.add(beginPoint);
        points.add(endPoint);
        return addLine(points, strokeWidth, strokeColorArgb, geodesic, clickable);
    }

    @Override
    public IPolyline addLine(List<LatLng> points, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable) {
        PolylineOptions options = new PolylineOptions();
        List<com.amap.api.maps.model.LatLng> latLngs = new ArrayList<>();
        for (LatLng point : points) {
            latLngs.add(new com.amap.api.maps.model.LatLng(point.lat, point.lng));
        }
        options.addAll(latLngs);
        options.width(strokeWidth);
        options.color(strokeColorArgb);
        return new APolyLineWrapper(mMap.addPolyline(options));
    }

    @Override
    public IPolyline addPolyLine(List<LatLng> points, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable) {
        return null;
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
    }

    @Override
    public void setOnCameraMoveListener(OnCameraMoveListener onCameraMoveListener) {
        this.mOnCameraMoveListener = onCameraMoveListener;
        mMap.setOnCameraChangeListener(this);
    }

    @Override
    public void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        this.mOnMyLocationChangeListener = onMyLocationChangeListener;
    }

    @Override
    public void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {

    }

    @Override
    public void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {

    }

    @Override
    public void setOnMapClickListener(OnMapClickListener onMapClickListener) {

    }

    public void snapshot(final OnSnapshotListener onSnapshotListener) {
        mMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {

            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int i) {
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
    public void onCameraChange(CameraPosition cameraPosition) {
        mOnCameraMoveListener.onCameraMove();
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        mOnCameraIdleListener.onCameraIdle();
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
    public Location getMyLocation() {
        return mLocationClient.getLastLocation();
    }

    private LocationSource.OnLocationChangedListener mSourceListener;

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
        mMap.setOnMyLocationChangeListener(this);
        mMap.setMyLocationEnabled(true);
        // 设置定位蓝点样式
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location));
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
        myLocationStyle.anchor(0.5f, 0.5f);
        myLocationStyle.strokeColor(Color.parseColor("#2F89EB"));
        myLocationStyle.radiusFillColor(Color.parseColor("#362F89EB"));
        mMap.setMyLocationStyle(myLocationStyle);
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
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        for (LatLng point : points) {
            boundsBuilder.include(new com.amap.api.maps.model.LatLng(point.lat, point.lng));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 300));
    }


    /**
     * ====================相机======================
     */
    @Override
    public void animateCamera(double lat, double lng, int zoomLevel) {
        com.amap.api.maps.model.LatLng latLng = new com.amap.api.maps.model.LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel);
        mMap.animateCamera(cameraUpdate);
    }

    @Override
    public void moveCamera(double lat, double lng, int zoomLevel) {
        com.amap.api.maps.model.LatLng latLng = new com.amap.api.maps.model.LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel);
        mMap.moveCamera(cameraUpdate);
    }

    private void changeCamera(CameraUpdate update) {
        mMap.animateCamera(update);
    }

    @Override
    public LatLng getCameraPosition() {
        com.amap.api.maps.model.LatLng target = mMap.getCameraPosition().target;
        return new com.gezbox.googlemap.common.LatLng(target.latitude, target.longitude);
    }

    @Override
    public void onMyLocationChange(android.location.Location location) {
        if (mOnMyLocationChangeListener == null) {
            return;
        }
        mOnMyLocationChangeListener.onMyLocationChange(LocationUtil.transformLocation(location));
    }

}
