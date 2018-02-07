package com.github.mrfatbeard.mvpexample.mvp.model;

import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<List<UserModel>> listUsers();

    Observable<UserModel> getUser(long userId);
}
