package com.example.wangchong.mvpdemo.ui;

import com.example.wangchong.mvpdemo.base.IBasePresenter;
import com.example.wangchong.mvpdemo.base.IBaseView;

public interface IChannel {

    interface View extends IBaseView<Persenter> {

    }

    interface Persenter extends IBasePresenter {

    }
}
