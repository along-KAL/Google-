package com.gezbox.googlemap.amap;

import android.support.annotation.Nullable;

import com.amap.api.maps.model.Circle;
import com.gezbox.googlemap.common.interfaces.ICircle;
import com.google.android.gms.maps.model.LatLng;

/**
 * Description
 *
 * @author hanjie
 * @date 2018/4/13
 */

public class ACircleWrapper implements ICircle {

    private Circle mCircle;

    public ACircleWrapper(Circle circle) {
        mCircle = circle;
    }

    @Override
    public void remove() {
        mCircle.remove();
    }

    @Override
    public void setCenter(LatLng latLng) {
        mCircle.setCenter(new com.amap.api.maps.model.LatLng(latLng.latitude, latLng.longitude));
    }

    @Override
    public LatLng getCenter() {
        return new LatLng(mCircle.getCenter().latitude, mCircle.getCenter().longitude);
    }

    @Override
    public void setRadius(double radius) {
        mCircle.setRadius(radius);
    }

    @Override
    public double getRadius() {
        return mCircle.getRadius();
    }

    @Override
    public void setStrokeWidth(float width) {
        mCircle.setStrokeWidth(width);
    }

    @Override
    public float getStrokeWidth() {
        return mCircle.getStrokeWidth();
    }

    @Override
    public void setStrokeColor(int var1) {
        mCircle.setStrokeColor(var1);
    }

    @Override
    public int getStrokeColor() {
        return mCircle.getStrokeColor();
    }

    @Override
    public void setFillColor(int var1) {
        mCircle.setFillColor(var1);
    }

    @Override
    public int getFillColor() {
        return mCircle.getFillColor();
    }

    @Override
    public void setVisible(boolean var1) {
        mCircle.setVisible(var1);
    }

    @Override
    public void setClickable(boolean var1) {

    }

    @Override
    public void setTag(@Nullable Object var1) {

    }

    @Override
    public Object getTag() {
        return null;
    }

}
