package com.example.wangchong.mvpdemo.api;

import com.example.wangchong.mvpdemo.bean.CityBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ICityApi {
    @POST("/urcarAdmin/city/queryAllCitys")
    @FormUrlEncoded
    Observable<CityBean> getCitys(@FieldMap HashMap<String, String> params);

}
