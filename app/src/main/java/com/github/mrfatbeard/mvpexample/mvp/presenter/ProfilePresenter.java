package com.github.mrfatbeard.mvpexample.mvp.presenter;

import android.support.annotation.NonNull;

import com.github.mrfatbeard.mvpexample.SchedulerFactory;
import com.github.mrfatbeard.mvpexample.mvp.model.UserModel;
import com.github.mrfatbeard.mvpexample.mvp.model.UserRepository;
import com.github.mrfatbeard.mvpexample.mvp.view.ProfileView;
import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ProfilePresenter extends MvpNullObjectBasePresenter<ProfileView> {
    private final UserRepository repository;
    private final SchedulerFactory schedulerFactory;
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

    public ProfilePresenter(@NonNull UserRepository repository,
                            @NonNull SchedulerFactory schedulerFactory) {
        this.repository = repository;
        this.schedulerFactory = schedulerFactory;
    }

    public void loadUser(long userId) {
        repository.getUser(userId)
                .compose(schedulerFactory.applySchedulers())
                .retry(3)
                .subscribe(this::setUser, error -> getView().showError());
    }

    private void setUser(@NonNull UserModel user) {
        final ProfileView view = getView();
        view.setName(user.getName());
        view.setProfilePic(user.getPhotoUrl());

        if (user.hasEmail()) {
            view.setEmail(user.getEmail());
            view.showEmail();
        } else {
            view.hideEmail();
        }

        if (user.hasBirthday()) {
            view.setBirthday(dateFormat.format(user.getBirthday()));
            view.showBirthday();
        } else {
            view.hideBirthday();
        }

        if (user.hasPhoneNumber()) {
            view.setPhoneNumber(user.getPhoneNumber());
            view.showPhoneNumber();
        } else {
            view.hidePhoneNumber();
        }
    }

    public void mailTo(@NonNull String email) {
        getView().openMailApp(email);
    }
}
