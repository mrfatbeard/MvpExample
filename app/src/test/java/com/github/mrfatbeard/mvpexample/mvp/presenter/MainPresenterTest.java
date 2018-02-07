package com.github.mrfatbeard.mvpexample.mvp.presenter;

import com.github.mrfatbeard.mvpexample.mvp.model.UserRepository;
import com.github.mrfatbeard.mvpexample.mvp.view.MainView;

import org.junit.Test;

import java.util.Collections;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest {
    @Test
    public void testNormalLoading() throws Exception {
        MainView view = mock(MainView.class);
        UserRepository repository = mock(UserRepository.class);

        when(repository.listUsers())
                .thenReturn(
                        Observable.just(Collections.emptyList())
                );

        MainPresenter presenter = new MainPresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.loadUsers();

        verify(view, times(1)).showLoading();
        verify(view, times(1)).setData(Collections.emptyList());
        verify(view, times(1)).showData();
        verify(view, never()).showError();
        verify(view, never()).openUserScreen(anyLong());
    }

    @Test
    public void testNetworkError() throws Exception {
        MainView view = mock(MainView.class);
        UserRepository repository = mock(UserRepository.class);

        when(repository.listUsers())
                .thenReturn(
                        Observable.error(new RuntimeException())
                );

        MainPresenter presenter = new MainPresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.loadUsers();

        verify(view, times(4)).showLoading();
        verify(view, times(1)).showError();
        verify(view, never()).setData(Collections.emptyList());
        verify(view, never()).showData();
        verify(view, never()).openUserScreen(anyLong());
    }

    @Test
    public void testUserClicked() throws Exception {
        MainView view = mock(MainView.class);
        UserRepository repository = mock(UserRepository.class);

        MainPresenter presenter = new MainPresenter(repository, new TestSchedulerFactory());
        presenter.attachView(view);
        presenter.userClicked(1);

        verify(view, only()).openUserScreen(1);
    }

}