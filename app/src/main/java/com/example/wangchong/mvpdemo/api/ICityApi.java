package com.example.wangchong.mvpdemo.api;

import com.example.wangchong.mvpdemo.bean.CityBean;
import com.example.wangchong.mvpdemo.bean.UserInfo;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ICityApi {
    @POST("/urcarAdmin/city/queryAllCitys")
    @FormUrlEncoded
    Observable<CityBean> getCitys(@FieldMap HashMap<String, String> params);

    @POST("/urcarAdmin/app/getUserInfo")
    @FormUrlEncoded
    Observable<UserInfo> getUserInfo(@FieldMap HashMap<String, String> params);

    @POST("/urcarAdmin/app/login")
    @FormUrlEncoded
    Observable<UserInfo> loginIn(@FieldMap HashMap<String, String> params);


}
