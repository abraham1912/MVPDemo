package com.example.wangchong.mvpdemo.utils;

import android.util.Log;

import com.example.wangchong.mvpdemo.BuildConfig;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class HandlerHttpError {

    public static void handlerNetError(Throwable e) {
        if(e instanceof HttpException){
            if (BuildConfig.DEBUG){
                ResponseBody body = ((HttpException) e).response().errorBody();
                try {
                    Log.e("Throwable",body.string());
                } catch (IOException IOe) {
                    IOe.printStackTrace();
                }
            }
            int responseCode = ((HttpException) e).code();
            switch (responseCode){
                case 400 : //Bad Request/错误请求
                    break;
                case 401 : //Unauthorized/未授权
                    break;
                case 404 : //Not Found/未找到
                    break;
                case 408 : //Request Timeout/请求超时
                    break;
                case 500 : //Internal Server Error/内部服务器错误
                    break;
                case 502 : //Bad Gateway/错误的网关
                    break;

                default:

            }
        }
    }
}
