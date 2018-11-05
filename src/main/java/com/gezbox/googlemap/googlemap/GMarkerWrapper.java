package com.gezbox.googlemap.googlemap;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;

import com.gezbox.googlemap.common.LatLng;
import com.gezbox.googlemap.common.interfaces.IMarker;
import com.gezbox.googlemap.googlemap.utils.GMapUtils;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by along on 2017/9/1.
 */

public class GMarkerWrapper implements IMarker {

    private Marker mMarker;

    public GMarkerWrapper(Marker marker) {
        this.mMarker = marker;
    }

    public void with(Marker marker) {
        this.mMarker = marker;
    }

    @Override
    public void remove() {
        mMarker.remove();
    }

    @Override
    public void setPosition(LatLng latLng) {
        mMarker.setPosition(GMapUtils.transformToGLatLng(latLng));
    }

    @Override
    public LatLng getPosition() {
        return GMapUtils.transformToLatLng(mMarker.getPosition());
    }

    @Override
    public void setIcon(@Nullable Bitmap bitmap) {
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        mMarker.setIcon(bitmapDescriptor);
    }

    @Override
    public void setView(View view) {
        setIcon(GMapUtils.loadBitmapFromView(view));
    }

    @Override
    public void setAnchor(float var1, float var2) {
        mMarker.setAnchor(var1, var2);
    }

    @Override
    public void setInfoWindowAnchor(float var1, float var2) {
        mMarker.setInfoWindowAnchor(var1, var2);
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
        mMarker.setTag(var1);
    }

    @Override
    public Object getTag() {
        return mMarker.getTag();
    }
}
