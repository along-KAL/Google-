package com.gezbox.googlemap.common.interfaces;

import android.support.annotation.Nullable;


/**
 * Created by along on 2017/9/1.
 */

public interface IPolyline {
    void remove();

    void setWidth(float var1);

    float getWidth();

    void setColor(int var1);

    int getColor();

    void setVisible(boolean var1);

    void setClickable(boolean var1);

    void setTag(@Nullable Object var1);

    Object getTag();

}
