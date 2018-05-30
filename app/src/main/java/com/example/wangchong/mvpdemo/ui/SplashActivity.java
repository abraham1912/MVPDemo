package com.example.wangchong.mvpdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wangchong.mvpdemo.R;
import com.example.wangchong.mvpdemo.bean.CityBean;
import com.example.wangchong.mvpdemo.utils.RxBus;

import io.reactivex.Observable;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
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
    protected void onDestroy() {
        RxBus.getInstance().unregister(TAG,observable);
        super.onDestroy();
    }
}
