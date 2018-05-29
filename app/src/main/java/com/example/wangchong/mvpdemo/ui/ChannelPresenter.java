package com.example.wangchong.mvpdemo.ui;

public class ChannelPresenter implements IChannel.Persenter{
    public IChannel.View view ;

    public ChannelPresenter(IChannel.View view) {
        this.view = view;
    }

    @Override
    public void doRefresh() {
        view.onShowLoading();

    }

    @Override
    public void doShowNetError() {

    }
}
