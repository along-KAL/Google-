package com.gezbox.googlemap.common.interfaces;

import android.support.annotation.Nullable;


/**
 * Created by along on 2017/9/1.
 */

public interface IPolygon {
    void remove();

    void setStrokeWidth(float var1);

    float getStrokeWidth();


    void setStrokeColor(int var1);

    int getStrokeColor();

    void setFillColor(int var1);

    int getFillColor();

    void setVisible(boolean var1);

    void setClickable(boolean var1);

    void setTag(@Nullable Object var1);

    Object getTag();

}
