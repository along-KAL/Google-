package com.gezbox.googlemap.common.interfaces;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;

import com.gezbox.googlemap.common.LatLng;

/**
 * Created by along on 2017/9/1.
 */

public interface IMarker {
    void remove();

    void setPosition(LatLng latLng);

    LatLng getPosition();

    void setIcon(@Nullable Bitmap bitmap);

    void setView(View view);

    void setAnchor(float var1, float var2);

    void setInfoWindowAnchor(float var1, float var2);

    void setTitle(@Nullable String var1);

    String getTitle();

    void setVisible(boolean var1);

    void setAlpha(float var1);

    float getAlpha();

    void setZIndex(int var1);

    float getZIndex();

    void setTag(@Nullable Object var1);

    Object getTag();
}
