package com.example.wangchong.mvpdemo.base;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public abstract class BaseMvpFragment <T extends IBasePresenter>  extends BaseFragment  implements IBaseView<T>{
    /**
     * 使用MVP的Activity使用这个类
     */
    protected T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setPresenter(presenter);
        super.onCreate(savedInstanceState);

    }

    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }


}
