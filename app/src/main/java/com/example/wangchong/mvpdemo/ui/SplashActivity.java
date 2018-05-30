package com.example.wangchong.mvpdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wangchong.mvpdemo.R;
import com.example.wangchong.mvpdemo.api.ICityApi;
import com.example.wangchong.mvpdemo.base.BaseActivity;
import com.example.wangchong.mvpdemo.bean.CityBean;
import com.example.wangchong.mvpdemo.utils.DigestUtils;
import com.example.wangchong.mvpdemo.utils.HandlerHttpError;
import com.example.wangchong.mvpdemo.utils.RetrofitFactory;
import com.example.wangchong.mvpdemo.utils.RxBus;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends BaseActivity {
    public static final String TAG = "SplashActivity";
    public  Observable<CityBean> observable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        TextView tv = findViewById(R.id.text) ;
        tv.setOnClickListener(v -> startActivity(new Intent(SplashActivity.this,MainActivity.class)));

        //使用Rxbus接收数据
        observable = RxBus.getInstance().register(SplashActivity.TAG);
        observable.subscribe(cityBean -> {
            tv.setText(cityBean.content.get(0).cityName);
        });


    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        HashMap<String,String> map = new HashMap<>();
        map.put("loginId","liaixin");
        map.put("password","123456");

        map.put("sign", DigestUtils.makeVeriSign(map));
        RetrofitFactory.getRetrofit().create(ICityApi.class).loginIn(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(this.bindAutoDispose())
                .subscribe(userInfo -> {
                    showToast(userInfo.content.userName);
                }, e ->  HandlerHttpError.handlerNetError(e));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
