package com.github.mrfatbeard.mvpexample.mvp.view;

import android.support.annotation.NonNull;

import com.github.mrfatbeard.mvpexample.mvp.model.UserModel;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

public interface MainView extends MvpView {
    void setData(@NonNull List<UserModel> data);
    void showData();
    void showError();
    void showLoading();
    void openUserScreen(long userId);
}
