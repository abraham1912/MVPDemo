package com.example.wangchong.mvpdemo.base;

import android.arch.lifecycle.Lifecycle;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public class BaseMvpActivity <T extends IBasePresenter> extends BaseActivity implements IBaseView<T>{
    protected T presenter ;

    @Override
    public void setContentView(int layoutResID) {
        setPresenter(presenter);
        super.setContentView(layoutResID);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(T presenter) {

    }

    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
