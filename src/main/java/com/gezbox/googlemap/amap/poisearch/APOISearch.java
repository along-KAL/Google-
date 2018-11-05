package com.gezbox.googlemap.amap.poisearch;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.gezbox.googlemap.common.bean.POIBean;
import com.gezbox.googlemap.common.interfaces.POISearch;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by along on 2017/8/17.
 */

public class APOISearch implements POISearch {

    private static WeakReference<Context> mContextWeakReference;

    private volatile static APOISearch mApoiSearch;

    public static APOISearch getInstance(Context context) {
        mContextWeakReference = new WeakReference<Context>(context);
        if (mApoiSearch == null) {
            synchronized (APOISearch.class) {
                if (mApoiSearch == null) {
                    mApoiSearch = new APOISearch();
                }
            }
        }
        return mApoiSearch;
    }

    public APOISearch() {
        init();
    }

    private void init() {

    }


    @Override
    public void searchPOI(double lat, double lng, final OnPOISearchDataCallback onPOISearchDataCallback) {
        PoiSearch.Query query = new PoiSearch.Query("", "", "");
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        PoiSearch poiSearch = new PoiSearch(mContextWeakReference.get(), query);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(lat,
                lng), 1000));//设置周边搜索的中心点以及半径
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                if (i == 1000) {
                    ArrayList<PoiItem> poiItems = poiResult.getPois();
                    if (poiItems == null || poiItems.isEmpty()) {
                        return;
                    }
                    POIBean poiBean = new POIBean();
                    poiBean.status = "OK";
                    ArrayList<POIBean.Results> results = new ArrayList<>();
                    for (PoiItem poiItem : poiItems) {
                        POIBean.Results result = new POIBean.Results();
                        POIBean.Geometry geometry = new POIBean.Geometry();
                        POIBean.Location location = new POIBean.Location();
                        location.lat = poiItem.getLatLonPoint().getLatitude();
                        location.lng = poiItem.getLatLonPoint().getLongitude();
                        geometry.location = location;
                        result.geometry = geometry;
                        result.name = poiItem.getTitle();
                        result.vicinity = poiItem.getSnippet();
                        results.add(result);
                    }
                    poiBean.results = results;
                    if (onPOISearchDataCallback != null) {
                        onPOISearchDataCallback.onResult(poiBean);
                    }
                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void searchPlaceByKeyword(String key, final OnSearchPlaceByKeywordCallback onSearchPlaceByKeywordCallback) {
        PoiSearch.Query query = new PoiSearch.Query(key, "", "");
        query.setPageSize(20);
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        PoiSearch poiSearch = new PoiSearch(mContextWeakReference.get(), query);
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                if (i == 1000) {
                    ArrayList<PoiItem> poiItems = poiResult.getPois();
                    if (poiItems == null || poiItems.isEmpty()) {
                        return;
                    }
                    POIBean poiBean = new POIBean();
                    poiBean.status = "OK";
                    ArrayList<POIBean.Results> results = new ArrayList<>();
                    for (PoiItem poiItem : poiItems) {
                        POIBean.Results result = new POIBean.Results();
                        POIBean.Geometry geometry = new POIBean.Geometry();
                        POIBean.Location location = new POIBean.Location();
                        location.lat = poiItem.getLatLonPoint().getLatitude();
                        location.lng = poiItem.getLatLonPoint().getLongitude();
                        geometry.location = location;
                        result.geometry = geometry;
                        result.formatted_address = poiItem.getSnippet();
                        result.name = poiItem.getTitle();
                        results.add(result);
                    }
                    poiBean.results = results;
                    if (onSearchPlaceByKeywordCallback != null) {
                        onSearchPlaceByKeywordCallback.onResult(poiBean);
                    }
                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();
    }

}
