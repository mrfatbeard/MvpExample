package com.github.mrfatbeard.mvpexample.mvp.model

import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DefaultUserRepository : UserRepository {
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    private val harold = UserModel(
            name = "Harold",
            userId = 0,
            email = "harold@hidethepain.com",
            photoUrl = "https://goo.gl/APMF6o"
    )

    private val bigShaq = UserModel(
            name = "Big Shaq",
            userId = 1,
            birthday = dateFormat.parse("10-10-1980"),
            photoUrl = "https://goo.gl/z65EpU"
    )

    private val zoidberg = UserModel(
            name = "John Zoidberg",
            userId = 2,
            photoUrl = "https://goo.gl/cewV7o"
    )

    private val allUsers = mutableListOf(harold, bigShaq, zoidberg)

    override fun listUsers(): Observable<MutableList<UserModel>> {
        return Observable.just(allUsers)
                .delay(2, TimeUnit.SECONDS) // simulate network delay
    }

    override fun getUser(userId: Long): Observable<UserModel> {
        val friend = allUsers.firstOrNull { it.userId == userId }

        return if (friend == null) {
            Observable.error<UserModel>(RuntimeException("No such user"))
        } else {
            Observable.just(friend)
        }
    }
}