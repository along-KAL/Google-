package com.gezbox.googlemap.amap;

import android.support.annotation.Nullable;

import com.amap.api.maps.model.Polyline;
import com.gezbox.googlemap.common.interfaces.IPolyline;

/**
 * Description
 *
 * @author hanjie
 * @date 2018/4/13
 */

public class APolyLineWrapper implements IPolyline {

    private Polyline mPolyline;

    public APolyLineWrapper(Polyline polyline) {
        mPolyline = polyline;
    }

    @Override
    public void remove() {
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
    }

    @Override
    public void setTag(@Nullable Object var1) {

    }

    @Override
    public Object getTag() {
        return null;
    }

}
