package com.gezbox.googlemap.common.bean;

import java.util.List;

/**
 * Created by along on 2017/8/15.
 */

public class POIBean {
    /**
     * html_attributions : []
     * results : [{"geometry":{"location":{"lat":30.1863431,"lng":120.1777499},"viewport":{"northeast":{"lat":30.1876920802915,"lng":120.1790988802915},"southwest":{"lat":30.1849941197085,"lng":120.1764009197085}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png","id":"256b5aaa6afdcaa0546312e4287019ac3dafad3a","name":"杭州瑞裕实业有限公司","place_id":"ChIJ31FIC4yCTDQRxyiK97vSVBQ","reference":"CmRRAAAAluMoPlk-5_rPW0BJG0U5ycAAyOIe6qrrAtnM2U9vBIcCpxeVmg8olDKgLqp9-xZ1cdZS58r_pt8To7LupAdmX8gwTB3phIunVCrb5uguEETwV59m_QBwp-buxohtwhMuEhDTE4f3t-fjmeRqaVPUbbmAGhRalmCSZzkeruPxik4VL0pHLvy9dg","scope":"GOOGLE","types":["point_of_interest","establishment"],"vicinity":"杭州市滨江区南北支路"},{"geometry":{"location":{"lat":30.187048,"lng":120.177985},"viewport":{"northeast":{"lat":30.1879169,"lng":120.1795347802915},"southwest":{"lat":30.1844413,"lng":120.1768368197085}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png","id":"f62d1e2bd6d40b10d5357b3949b8ba677d7cd19c","name":"杭州先锋电子技术股份有限公司","place_id":"ChIJHSHqa4yCTDQR9_9klHF4Xh0","reference":"CmRRAAAAVmI-WjeJ-CyN0Il7drkOWTZR1MZ9lEsePAA4DgHi_Kli0WI3I93ElYDlpbL4RcGvyLQr3v7fzWzXECVOnajJmyoE4XYwoV11_X2MOec3KPkezTmJqAN18qnxyh3KR8S9EhBBImw3SYQ_6X0otfMxW42zGhR-_KfxR9OU02JH7vdMDGkyTQxTMw","scope":"GOOGLE","types":["point_of_interest","establishment"],"vicinity":"杭州市滨江区滨安路1186号"},{"geometry":{"location":{"lat":30.185333,"lng":120.175722},"viewport":{"northeast":{"lat":30.1866819802915,"lng":120.1770709802915},"southwest":{"lat":30.1839840197085,"lng":120.1743730197085}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png","id":"f242cf5106614ca0f14258fc3fca8df2c5d7e6b0","name":"姚生记停车场","place_id":"ChIJX3OfXYmCTDQREZcyA7c9tkc","reference":"CmRRAAAA8kNTPtyhnM3EoVfPOarAmKJckwhF4KbAyluTKtgbGXkSJFOY9AsI56z_GKTfar0qhkSEkgy7RRD0zqwcau1et7pm5ZSaHM6jwWnbR_ABdOm1qGVvyUi3Rh4xdtwflezPEhCHTxKX7DPyafUNkNaJ8owPGhSLZyzEkEUVURR5YeWWxxLJ33CrcQ","scope":"GOOGLE","types":["parking","point_of_interest","establishment"],"vicinity":"杭州市滨江区滨安路1190号"},{"geometry":{"location":{"lat":30.185005,"lng":120.176613},"viewport":{"northeast":{"lat":30.1863539802915,"lng":120.1779619802915},"southwest":{"lat":30.1836560197085,"lng":120.1752640197085}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/bus-71.png","id":"db34bcdee00f0062eda2e52b7e263aa9fc41c37f","name":"滨安路信诚路口","place_id":"ChIJ9zgbZImCTDQRipaoJdfLiM4","reference":"CmRSAAAAnKXgNYhWz0tHZRmxbFPkw2IEEVibENbAa57NaAz981QBNynxxP4X5mI1FJZ4oj4blrmnaHSuNRph44jxAXU8O3xKS6blGf8yeRZBD1FOdtxgYTRjqKFN5TinbzB8F1AqEhAMtdV4syrn_u1Y0v4wyQMtGhQyMzNL4KdTomE8lg7qR1tCbJQz6A","scope":"GOOGLE","types":["bus_station","transit_station","point_of_interest","establishment"],"vicinity":"China"}]
     * status : OK
     */

    public String status;
    public List<Results> results;

    public static class Results {
        /**
         * geometry : {"location":{"lat":30.1863431,"lng":120.1777499},"viewport":{"northeast":{"lat":30.1876920802915,"lng":120.1790988802915},"southwest":{"lat":30.1849941197085,"lng":120.1764009197085}}}
         * icon : https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png
         * id : 256b5aaa6afdcaa0546312e4287019ac3dafad3a
         * name : 杭州瑞裕实业有限公司
         * place_id : ChIJ31FIC4yCTDQRxyiK97vSVBQ
         * reference : CmRRAAAAluMoPlk-5_rPW0BJG0U5ycAAyOIe6qrrAtnM2U9vBIcCpxeVmg8olDKgLqp9-xZ1cdZS58r_pt8To7LupAdmX8gwTB3phIunVCrb5uguEETwV59m_QBwp-buxohtwhMuEhDTE4f3t-fjmeRqaVPUbbmAGhRalmCSZzkeruPxik4VL0pHLvy9dg
         * scope : GOOGLE
         * types : ["point_of_interest","establishment"]
         * vicinity : 杭州市滨江区南北支路
         */
        public String formatted_address;
        public Geometry geometry;
        public String icon;
        public String id;
        public String name;
        public String place_id;
        public String reference;
        public String scope;
        public String vicinity;
        public List<String> types;


    }

    public static class Geometry {
        /**
         * location : {"lat":30.1863431,"lng":120.1777499}
         * viewport : {"northeast":{"lat":30.1876920802915,"lng":120.1790988802915},"southwest":{"lat":30.1849941197085,"lng":120.1764009197085}}
         */

        public Location location;
        public Viewport viewport;


    }

    public static class Location {
        /**
         * lat : 30.1863431
         * lng : 120.1777499
         */

        public double lat;
        public double lng;
    }

    public static class Viewport {
        /**
         * northeast : {"lat":30.1876920802915,"lng":120.1790988802915}
         * southwest : {"lat":30.1849941197085,"lng":120.1764009197085}
         */

        public Northeast northeast;
        public Southwest southwest;

        public static class Northeast {
            /**
             * lat : 30.1876920802915
             * lng : 120.1790988802915
             */

            public double lat;
            public double lng;
        }

        public static class Southwest {
            /**
             * lat : 30.1849941197085
             * lng : 120.1764009197085
             */

            public double lat;
            public double lng;
        }
    }

}
