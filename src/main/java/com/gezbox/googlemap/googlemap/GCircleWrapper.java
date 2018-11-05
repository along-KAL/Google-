package com.gezbox.googlemap.googlemap;

import android.support.annotation.Nullable;

import com.gezbox.googlemap.common.interfaces.ICircle;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by along on 2017/9/1.
 */

public class GCircleWrapper implements ICircle {

    private Circle mCircle;

    public GCircleWrapper(Circle circle) {
        this.mCircle = circle;
    }

    @Override
    public void remove() {
        mCircle.remove();
    }

    @Override
    public void setCenter(LatLng var1) {
        mCircle.setCenter(var1);
    }

    @Override
    public LatLng getCenter() {
        return mCircle.getCenter();
    }

    @Override
    public void setRadius(double var1) {
        mCircle.setRadius(var1);
    }

    @Override
    public double getRadius() {
        return mCircle.getRadius();
    }

    @Override
    public void setStrokeWidth(float var1) {
        mCircle.setStrokeWidth(var1);
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
        mCircle.setClickable(var1);
    }

    @Override
    public void setTag(@Nullable Object var1) {
        mCircle.setTag(var1);
    }

    @Override
    public Object getTag() {
        return mCircle.getTag();
    }

}
