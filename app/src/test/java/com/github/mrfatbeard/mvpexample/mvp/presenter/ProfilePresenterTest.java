package com.github.mrfatbeard.mvpexample.mvp.presenter;

import com.github.mrfatbeard.mvpexample.mvp.model.UserRepository;
import com.github.mrfatbeard.mvpexample.mvp.view.ProfileView;

import org.junit.Test;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProfilePresenterTest {
    @Test
    public void testUserHasNothing() throws Exception {
        ProfileView view = mock(ProfileView.class);
        UserRepository repository = mock(UserRepository.class);

        when(repository.getUser(0))
                .thenReturn(
                        Observable.just(TestUsersKt.getNothing())
                );

        ProfilePresenter presenter = new ProfilePresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.loadUser(0);

        verify(view, times(1)).setProfilePic(nullable(String.class));
        verify(view, times(1)).setName(anyString());
        verify(view, never()).setEmail(anyString());
        verify(view, never()).setBirthday(anyString());
        verify(view, never()).setPhoneNumber(anyString());
        verify(view, never()).showError();
        verify(view, never()).showEmail();
        verify(view, never()).showBirthday();
        verify(view, never()).showPhoneNumber();
        verify(view, times(1)).hideEmail();
        verify(view, times(1)).hideBirthday();
        verify(view, times(1)).hidePhoneNumber();
    }

    @Test
    public void testUserHasEmail() throws Exception {
        ProfileView view = mock(ProfileView.class);
        UserRepository repository = mock(UserRepository.class);

        when(repository.getUser(0))
                .thenReturn(
                        Observable.just(TestUsersKt.getEmail())
                );

        ProfilePresenter presenter = new ProfilePresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.loadUser(0);

        verify(view, times(1)).setProfilePic(nullable(String.class));
        verify(view, times(1)).setName(anyString());
        verify(view, times(1)).setEmail(anyString());
        verify(view, never()).setBirthday(anyString());
        verify(view, never()).setPhoneNumber(anyString());
        verify(view, never()).showError();
        verify(view, times(1)).showEmail();
        verify(view, never()).showBirthday();
        verify(view, never()).showPhoneNumber();
        verify(view, never()).hideEmail();
        verify(view, times(1)).hideBirthday();
        verify(view, times(1)).hidePhoneNumber();
    }

    @Test
    public void testUserHasPhone() throws Exception {
        ProfileView view = mock(ProfileView.class);
        UserRepository repository = mock(UserRepository.class);

        when(repository.getUser(0))
                .thenReturn(
                        Observable.just(TestUsersKt.getPhoneNumber())
                );

        ProfilePresenter presenter = new ProfilePresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.loadUser(0);

        verify(view, times(1)).setProfilePic(nullable(String.class));
        verify(view, times(1)).setName(anyString());
        verify(view, never()).setEmail(anyString());
        verify(view, never()).setBirthday(anyString());
        verify(view, times(1)).setPhoneNumber(anyString());
        verify(view, never()).showError();
        verify(view, never()).showEmail();
        verify(view, never()).showBirthday();
        verify(view, times(1)).showPhoneNumber();
        verify(view, times(1)).hideEmail();
        verify(view, times(1)).hideBirthday();
        verify(view, never()).hidePhoneNumber();
    }

    @Test
    public void testUserHasBirthday() throws Exception {
        ProfileView view = mock(ProfileView.class);
        UserRepository repository = mock(UserRepository.class);

        when(repository.getUser(0))
                .thenReturn(
                        Observable.just(TestUsersKt.getBirthday())
                );

        ProfilePresenter presenter = new ProfilePresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.loadUser(0);

        verify(view, times(1)).setProfilePic(nullable(String.class));
        verify(view, times(1)).setName(anyString());
        verify(view, never()).setEmail(anyString());
        verify(view, times(1)).setBirthday(anyString());
        verify(view, never()).setPhoneNumber(anyString());
        verify(view, never()).showError();
        verify(view, never()).showEmail();
        verify(view, times(1)).showBirthday();
        verify(view, never()).showPhoneNumber();
        verify(view, times(1)).hideEmail();
        verify(view, never()).hideBirthday();
        verify(view, times(1)).hidePhoneNumber();
    }

    @Test
    public void testUserHasEverything() throws Exception {
        ProfileView view = mock(ProfileView.class);
        UserRepository repository = mock(UserRepository.class);

        when(repository.getUser(0))
                .thenReturn(
                        Observable.just(TestUsersKt.getEverything())
                );

        ProfilePresenter presenter = new ProfilePresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.loadUser(0);

        verify(view, times(1)).setProfilePic(nullable(String.class));
        verify(view, times(1)).setName(anyString());
        verify(view, times(1)).setEmail(anyString());
        verify(view, times(1)).setBirthday(anyString());
        verify(view, times(1)).setPhoneNumber(anyString());
        verify(view, never()).showError();
        verify(view, times(1)).showEmail();
        verify(view, times(1)).showBirthday();
        verify(view, times(1)).showPhoneNumber();
        verify(view, never()).hideEmail();
        verify(view, never()).hideBirthday();
        verify(view, never()).hidePhoneNumber();
    }

    @Test
    public void testUserNotFound() throws Exception {
        ProfileView view = mock(ProfileView.class);
        UserRepository repository = mock(UserRepository.class);

        when(repository.getUser(anyLong()))
                .thenReturn(
                        Observable.error(new RuntimeException())
                );

        ProfilePresenter presenter = new ProfilePresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.loadUser(0);

        verify(view, never()).setProfilePic(nullable(String.class));
        verify(view, never()).setName(anyString());
        verify(view, never()).setEmail(anyString());
        verify(view, never()).setBirthday(anyString());
        verify(view, never()).setPhoneNumber(anyString());
        verify(view, times(1)).showError();
        verify(view, never()).showEmail();
        verify(view, never()).showBirthday();
        verify(view, never()).showPhoneNumber();
        verify(view, never()).hideEmail();
        verify(view, never()).hideBirthday();
        verify(view, never()).hidePhoneNumber();
    }

    @Test
    public void testMailTo() throws Exception {
        ProfileView view = mock(ProfileView.class);
        UserRepository repository = mock(UserRepository.class);

        ProfilePresenter presenter = new ProfilePresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.mailTo("ololo@ololo.com");

        verify(view, times(1)).openMailApp("ololo@ololo.com");
    }

}