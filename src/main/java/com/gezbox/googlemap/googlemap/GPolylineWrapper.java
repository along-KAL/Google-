package com.gezbox.googlemap.googlemap;

import android.support.annotation.Nullable;

import com.gezbox.googlemap.common.interfaces.IPolyline;
import com.google.android.gms.maps.model.Polyline;

/**
 * Created by along on 2017/9/1.
 */

public class GPolylineWrapper implements IPolyline {

    private Polyline mPolyline;

    public GPolylineWrapper(Polyline polyline) {
        this.mPolyline = polyline;
    }
    public void with(Polyline polyline){
        this.mPolyline = polyline;
    }
    @Override
    public void remove() {
        if(mPolyline==null){return;}
        mPolyline.remove();
    }

    @Override
    public void setWidth(float var1) {
        mPolyline.setWidth(var1);
    }

    @Override
    public float getWidth() {
        return mPolyline.getWidth();
    }

    @Override
    public void setColor(int var1) {
        mPolyline.setColor(var1);
    }

    @Override
    public int getColor() {
        return mPolyline.getColor();
    }

    @Override
    public void setVisible(boolean var1) {
        mPolyline.setVisible(var1);
    }

    @Override
    public void setClickable(boolean var1) {
        mPolyline.setClickable(var1);
    }

    @Override
    public void setTag(@Nullable Object var1) {
        mPolyline.setTag(var1);
    }

    @Override
    public Object getTag() {
        return mPolyline.getTag();
    }

}
