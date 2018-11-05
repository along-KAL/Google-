package com.gezbox.googlemap.amap;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.gezbox.googlemap.common.LatLng;
import com.gezbox.googlemap.common.interfaces.IMarker;

/**
 * Description
 *
 * @author hanjie
 * @date 2018/4/13
 */

public class AMarkerWrapper implements IMarker {

    private Marker mMarker;

    public AMarkerWrapper(Marker marker) {
        mMarker = marker;
    }

    @Override
    public void remove() {
        mMarker.remove();
    }

    @Override
    public void setPosition(LatLng latLng) {
        mMarker.setPosition(new com.amap.api.maps.model.LatLng(latLng.lat, latLng.lng));
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(mMarker.getPosition().latitude, mMarker.getPosition().longitude);
    }

    @Override
    public void setIcon(@Nullable Bitmap bitmap) {
        mMarker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
    }

    @Override
    public void setView(View view) {
        mMarker.setIcon(BitmapDescriptorFactory.fromView(view));
    }

    @Override
    public void setAnchor(float var1, float var2) {
        mMarker.setAnchor(var1, var2);
    }

    @Override
    public void setInfoWindowAnchor(float var1, float var2) {

    }

    @Override
    public void setTitle(@Nullable String var1) {
        mMarker.setTitle(var1);
    }

    @Override
    public String getTitle() {
        return mMarker.getTitle();
    }

    @Override
    public void setVisible(boolean var1) {
        mMarker.setVisible(var1);
    }

    @Override
    public void setAlpha(float var1) {
        mMarker.setAlpha(var1);
    }

    @Override
    public float getAlpha() {
        return mMarker.getAlpha();
    }

    @Override
    public void setZIndex(int var1) {
        mMarker.setZIndex(var1);
    }

    @Override
    public float getZIndex() {
        return mMarker.getZIndex();
    }

    @Override
    public void setTag(@Nullable Object var1) {

    }

    @Override
    public Object getTag() {
        return null;
    }

}
