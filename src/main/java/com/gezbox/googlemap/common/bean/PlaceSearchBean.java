package com.gezbox.googlemap.common.bean;

import java.util.List;

/**
 * Created by along on 2017/8/17.
 */

public class PlaceSearchBean {
    public String next_page_token;
    public String status;
    public List<Results> results;

    public static class Results {

        public String formatted_address;
        public Geometry geometry;
        public String icon;
        public String id;
        public String name;
        public String place_id;
        public String reference;
        public List<String> types;

        public static class Geometry {

            public Location location;
            public Viewport viewport;

            public static class Location {
                /**
                 * lat : 47.549135
                 * lng : 133.113724
                 */

                public double lat;
                public double lng;
            }

            public static class Viewport {
                /**
                 * northeast : {"lat":47.5504839802915,"lng":133.1150729802915}
                 * southwest : {"lat":47.5477860197085,"lng":133.1123750197085}
                 */

                public Northeast northeast;
                public Southwest southwest;

                public static class Northeast {
                    /**
                     * lat : 47.5504839802915
                     * lng : 133.1150729802915
                     */

                    public double lat;
                    public double lng;
                }

                public static class Southwest {
                    /**
                     * lat : 47.5477860197085
                     * lng : 133.1123750197085
                     */

                    public double lat;
                    public double lng;
                }
            }
        }
    }
}
