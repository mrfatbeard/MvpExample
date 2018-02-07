package com.github.mrfatbeard.mvpexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mrfatbeard.mvpexample.mvp.model.DefaultUserRepository;
import com.github.mrfatbeard.mvpexample.mvp.presenter.ProfilePresenter;
import com.github.mrfatbeard.mvpexample.mvp.view.ProfileView;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

public class ProfileActivity extends MvpActivity<ProfileView, ProfilePresenter>
        implements ProfileView {

    public final static String EXTRA_USER_ID = "extra_user_id";
    private final static long WRONG_USER = -1;

    private ImageView profilePicView;
    private Toolbar toolbar;
    private TextView phoneNumberView;
    private TextView emailView;
    private TextView birthdayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        long userId = getIntent().getLongExtra(EXTRA_USER_ID, WRONG_USER);
        if (userId == WRONG_USER) {
            throw new RuntimeException("No user id supplied");
        }

        initViews();
        presenter.loadUser(userId);
    }

    private void initViews() {
        profilePicView = findViewById(R.id.profilePicView);
        toolbar = findViewById(R.id.toolbar);
        phoneNumberView = findViewById(R.id.phoneNumberView);
        emailView = findViewById(R.id.emailView);
        birthdayView = findViewById(R.id.birthdayView);

        emailView.setOnClickListener(v -> presenter.mailTo(emailView.getText().toString()));
    }

    @NonNull
    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenter(new DefaultUserRepository(), new DefaultSchedulerFactory());
    }

    @Override
    public void showEmail() {
        emailView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPhoneNumber() {
        phoneNumberView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBirthday() {
        birthdayView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmail() {
        emailView.setVisibility(View.GONE);
    }

    @Override
    public void hidePhoneNumber() {
        phoneNumberView.setVisibility(View.GONE);
    }

    @Override
    public void hideBirthday() {
        birthdayView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.oops_something_went_wrong)
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public void setName(@NonNull String name) {
        toolbar.setTitle(name);
    }

    @Override
    public void setProfilePic(@Nullable String photoUrl) {
        Glide.with(this)
                .load(photoUrl)
                .into(profilePicView);
    }

    @Override
    public void setEmail(@Nullable String email) {
        emailView.setText(email);
    }

    @Override
    public void setPhoneNumber(@Nullable String phoneNumber) {
        phoneNumberView.setText(phoneNumber);
    }

    @Override
    public void setBirthday(@Nullable String birthday) {
        birthdayView.setText(birthday);
    }

    @Override
    public void openMailApp(@NonNull String email) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, email);

        startActivity(Intent.createChooser(intent, "Send Email"));

    }
}
