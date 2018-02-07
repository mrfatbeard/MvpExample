package com.github.mrfatbeard.mvpexample.mvp.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface ProfileView extends MvpView {
    void showEmail();
    void showPhoneNumber();
    void showBirthday();
    void hideEmail();
    void hidePhoneNumber();
    void hideBirthday();
    void showError();
    void setName(@NonNull String name);
    void setProfilePic(@Nullable String photoUrl);
    void setEmail(@Nullable String email);
    void setPhoneNumber(@Nullable String phoneNumber);
    void setBirthday(@Nullable String birthday);
    void openMailApp(@NonNull String email);
}
