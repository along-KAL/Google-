package com.gezbox.googlemap.googlemap.overlay;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Created by along on 2017/8/16.
 */

public class GMapOverlay {

    private GoogleMap mMap;
    public GMapOverlay(GoogleMap map){
        this.mMap = map;
    }

    public Marker addMarker(MarkerOptions markerOptions) {
        return mMap.addMarker(markerOptions);
    }

    public Circle addCircle(LatLng center, double radiusMeters, int strokeWidth, int strokeColorArgb, int fillColorArgb, boolean clickable) {
        return mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(radiusMeters)
                .strokeWidth(strokeWidth)
                .strokeColor(strokeColorArgb)
                .fillColor(fillColorArgb)
                .clickable(clickable));
    }

    public Polygon addPolygon(List<LatLng> points, int strokeWidth, int strokeColorArgb, int fillColorArgb, boolean clickable) {
        if(points==null||points.isEmpty()){return null;}
        return mMap.addPolygon(new PolygonOptions()
                .addAll(points)
                //.addHole(createRectangle(new LatLng(-22, 128), 1, 1))
                //.addHole(createRectangle(new LatLng(-18, 133), 0.5, 1.5))
                .fillColor(fillColorArgb)
                .strokeColor(strokeColorArgb)
                .strokeWidth(strokeWidth)
                .clickable(clickable));
    }

    public Polyline addPolyline(List<LatLng> points, int strokeWidth, int strokeColorArgb, boolean geodesic, boolean clickable) {
        if(points==null||points.isEmpty()){return null;}
        return mMap.addPolyline(new PolylineOptions()
                .addAll(points)
                .width(strokeWidth)
                .color(strokeColorArgb)
                .geodesic(geodesic)
                .clickable(clickable));
    }
}
