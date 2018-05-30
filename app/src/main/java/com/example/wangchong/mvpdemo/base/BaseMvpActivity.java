package com.example.wangchong.mvpdemo.base;

import android.arch.lifecycle.Lifecycle;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public abstract class BaseMvpActivity <T extends IBasePresenter> extends BaseActivity implements IBaseView<T>{
    /**
     * 使用MVP的Activity使用这个类
     */
    protected T presenter ;

    @Override
    public void setContentView(int layoutResID) {
        setPresenter(presenter);
        super.setContentView(layoutResID);
    }


    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }

}
