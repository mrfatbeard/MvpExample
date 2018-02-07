package com.github.mrfatbeard.mvpexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mrfatbeard.mvpexample.adapters.UserAdapter;
import com.github.mrfatbeard.mvpexample.mvp.model.DefaultUserRepository;
import com.github.mrfatbeard.mvpexample.mvp.model.UserModel;
import com.github.mrfatbeard.mvpexample.mvp.presenter.MainPresenter;
import com.github.mrfatbeard.mvpexample.mvp.view.MainView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.List;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    private RecyclerView contentView;
    private View loadingView;
    private View errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentView = findViewById(R.id.contentView);
        loadingView = findViewById(R.id.loadingView);
        errorView = findViewById(R.id.errorView);
        getPresenter().loadUsers();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(new DefaultUserRepository(), new DefaultSchedulerFactory());
    }

    @Override
    public void setData(@NonNull List<UserModel> data) {
        contentView.setAdapter(new UserAdapter(data, item -> presenter.userClicked(item.getUserId())));
    }

    @Override
    public void showData() {
        errorView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        errorView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        errorView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void openUserScreen(long userId) {
        final Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_USER_ID, userId);
        startActivity(intent);
    }
}
