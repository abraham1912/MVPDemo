package com.example.wangchong.mvpdemo.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wangchong.mvpdemo.R;
import com.example.wangchong.mvpdemo.api.ICityApi;
import com.example.wangchong.mvpdemo.base.BaseActivity;
import com.example.wangchong.mvpdemo.utils.DigestUtils;
import com.example.wangchong.mvpdemo.utils.RetrofitFactory;
import com.example.wangchong.mvpdemo.utils.RxBus;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    public static final String TAG = "BaseActivity";
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
                });
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
