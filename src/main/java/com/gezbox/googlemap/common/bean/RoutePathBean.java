package com.gezbox.googlemap.common.bean;

import java.util.List;

/**
 * Created by along on 2017/8/15.
 */

public class RoutePathBean {
    public String status;
    public List<GeocodedWaypointsBean> geocoded_waypoints;
    public List<RoutesBean> routes;

    public static class RoutesBean {
        public BoundsBean bounds;
        public String copyrights;
        public OverviewPolylineBean overview_polyline;
        public String summary;
        public List<LegsBean> legs;
        public List<?> warnings;
        public List<?> waypoint_order;


    }

    public static class GeocodedWaypointsBean {
        public String geocoder_status;
        public String place_id;
        public List<String> types;
    }

    public static class BoundsBean {
        public NortheastBean northeast;
        public SouthwestBean southwest;

    }

    public static class NortheastBean {
        public double lat;
        public double lng;
    }

    public static class SouthwestBean {
        public double lat;
        public double lng;
    }

    public static class OverviewPolylineBean {
        public String points;
    }

    public static class LegsBean {
        public DistanceBean distance;
        public DurationBean duration;
        public String end_address;
        public EndLocationBean end_location;
        public String start_address;
        public StartLocationBean start_location;
        public List<StepsBean> steps;
        public List<?> traffic_speed_entry;
        public List<?> via_waypoint;

    }

    public static class DistanceBean {
        public String text;
        public int value;
    }

    public static class DurationBean {
        public String text;
        public int value;
    }

    public static class EndLocationBean {
        public double lat;
        public double lng;
    }

    public static class StartLocationBean {
        public double lat;
        public double lng;
    }

    public static class StepsBean {
        public DistanceBeanX distance;
        public DurationBeanX duration;
        public EndLocationBeanX end_location;
        public String html_instructions;
        public PolylineBean polyline;
        public StartLocationBeanX start_location;
        public String travel_mode;
        public String maneuver;

    }

    public static class DistanceBeanX {
        public String text;
        public int value;
    }

    public static class DurationBeanX {
        public String text;
        public int value;
    }

    public static class EndLocationBeanX {
        public double lat;
        public double lng;
    }

    public static class PolylineBean {
        public String points;
    }

    public static class StartLocationBeanX {
        public double lat;
        public double lng;
    }
}
