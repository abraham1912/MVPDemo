package com.example.wangchong.mvpdemo.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wangchong.mvpdemo.R;
import com.example.wangchong.mvpdemo.api.ICityApi;
import com.example.wangchong.mvpdemo.base.BaseActivity;
import com.example.wangchong.mvpdemo.utils.DigestUtils;
import com.example.wangchong.mvpdemo.utils.HandlerHttpError;
import com.example.wangchong.mvpdemo.utils.RetrofitFactory;
import com.example.wangchong.mvpdemo.utils.RxBus;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        //toolbar.inflateMenu(R.menu.menu_activity_main);
        initToolBar(toolbar,true,TAG);
        //toolbar.setOnMenuItemClickListener(this);

        HashMap<String,String> map = new HashMap<>();
        map.put("type","1");
        map.put("source","1002");
        map.put("latitude","30");
        map.put("longitude","112");
        map.put("sign", DigestUtils.makeVeriSign(map));
        RetrofitFactory.getRetrofit().create(ICityApi.class).getUserInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(this.bindAutoDispose())
                .subscribe(userInfo -> {
                    showToast(userInfo.content.userName);
                }, e ->   HandlerHttpError.handlerNetError(e));
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

                    //使用Rxbus传递数据
                    RxBus.getInstance().post(SplashActivity.TAG,cityBean);
                },  e ->  HandlerHttpError.handlerNetError(e));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_search){
            showToast("123");
        }else if (id==R.id.action){
            showToast("456");
        }else if (id==R.id.search){
            showToast("789");
        }
        return super.onOptionsItemSelected(item);
    }
}
