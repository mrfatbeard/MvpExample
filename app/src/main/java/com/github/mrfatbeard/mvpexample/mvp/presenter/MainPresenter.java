package com.github.mrfatbeard.mvpexample.mvp.presenter;

import com.github.mrfatbeard.mvpexample.SchedulerFactory;
import com.github.mrfatbeard.mvpexample.mvp.model.UserRepository;
import com.github.mrfatbeard.mvpexample.mvp.view.MainView;
import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;

public class MainPresenter extends MvpNullObjectBasePresenter<MainView> {
    private final UserRepository repository;
    private final SchedulerFactory schedulerFactory;

    public MainPresenter(UserRepository repository,
                         SchedulerFactory schedulerFactory) {
        this.repository = repository;
        this.schedulerFactory = schedulerFactory;
    }

    public void loadUsers() {
        repository.listUsers()
                .compose(schedulerFactory.applySchedulers())
                .retry(3)
                .doOnSubscribe(d -> getView().showLoading())
                .subscribe(
                        data -> {
                            getView().setData(data);
                            getView().showData();
                        },
                        error -> getView().showError()
                );
    }

    public void userClicked(long userId) {
        getView().openUserScreen(userId);
    }
}
