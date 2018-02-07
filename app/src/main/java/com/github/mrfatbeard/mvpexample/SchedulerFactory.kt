package com.github.mrfatbeard.mvpexample

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface SchedulerFactory {
    fun <T> applySchedulers(): ObservableTransformer<T, T>
}

class DefaultSchedulerFactory: SchedulerFactory {
    override fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}
