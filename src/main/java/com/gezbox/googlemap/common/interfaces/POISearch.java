package com.gezbox.googlemap.common.interfaces;

import com.gezbox.googlemap.common.bean.POIBean;

/**
 * Created by along on 2017/8/17.
 */

public interface POISearch {

    interface OnPOISearchDataCallback {
        void onResult(POIBean pOIBean);
    }
   interface OnSearchPlaceByKeywordCallback {
        void onResult(POIBean pOIBean);
    }

    void searchPOI(double lat, double lng, OnPOISearchDataCallback onPOISearchDataCallback);
    void searchPlaceByKeyword(String query, OnSearchPlaceByKeywordCallback onSearchPlaceByKeywordCallback);
}
