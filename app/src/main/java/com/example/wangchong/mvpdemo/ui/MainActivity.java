package com.example.wangchong.mvpdemo.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wangchong.mvpdemo.R;
import com.example.wangchong.mvpdemo.api.ICityApi;
import com.example.wangchong.mvpdemo.base.BaseActivity;
import com.example.wangchong.mvpdemo.utils.DigestUtils;
import com.example.wangchong.mvpdemo.utils.RetrofitFactory;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity{
    private static final String TAG = "BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        initToolBar(toolbar,true,TAG);
    }

    @Override
    protected void initData() {
        HashMap<String,String> map = new HashMap<>();
        map.put("cityCode","110000");
        map.put("sign", DigestUtils.makeVeriSign(map));
        RetrofitFactory.getRetrofit().create(ICityApi.class).getCitys(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(this.bindAutoDispose())
                .subscribe(cityBean -> {
                    showToast(cityBean.content.get(0).cityName);
                });

    }

}
