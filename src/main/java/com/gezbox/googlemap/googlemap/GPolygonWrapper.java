package com.gezbox.googlemap.googlemap;

import android.support.annotation.Nullable;

import com.gezbox.googlemap.common.interfaces.IPolygon;
import com.google.android.gms.maps.model.Polygon;

/**
 * Created by along on 2017/9/1.
 */

public class GPolygonWrapper implements IPolygon {

    private Polygon mPolygon;

    public GPolygonWrapper(Polygon polygon) {
        this.mPolygon = polygon;
    }

    @Override
    public void remove() {
        mPolygon.remove();
    }

    @Override
    public void setStrokeWidth(float var1) {
        mPolygon.setStrokeWidth(var1);
    }

    @Override
    public float getStrokeWidth() {
        return mPolygon.getStrokeWidth();
    }

    @Override
    public void setStrokeColor(int var1) {
        mPolygon.setStrokeColor(var1);
    }

    @Override
    public int getStrokeColor() {
        return mPolygon.getStrokeColor();
    }

    @Override
    public void setFillColor(int var1) {
        mPolygon.setFillColor(var1);
    }

    @Override
    public int getFillColor() {
        return mPolygon.getFillColor();
    }

    @Override
    public void setVisible(boolean var1) {
        mPolygon.setVisible(var1);
    }

    @Override
    public void setClickable(boolean var1) {
        mPolygon.setClickable(var1);
    }

    @Override
    public void setTag(@Nullable Object var1) {
        mPolygon.setTag(var1);
    }

    @Override
    public Object getTag() {
        return mPolygon.getTag();
    }

}
