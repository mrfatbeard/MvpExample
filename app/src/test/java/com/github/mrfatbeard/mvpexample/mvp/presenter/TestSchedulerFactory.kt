package com.github.mrfatbeard.mvpexample.mvp.presenter

import com.github.mrfatbeard.mvpexample.SchedulerFactory
import io.reactivex.ObservableTransformer

class TestSchedulerFactory: SchedulerFactory {
    override fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { it }
    }
}