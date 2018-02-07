package com.github.mrfatbeard.mvpexample.mvp.presenter

import com.github.mrfatbeard.mvpexample.mvp.model.UserModel
import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

val email = UserModel(
        name = "Harold",
        userId = 0,
        email = "email@hidethepain.com",
        photoUrl = "https://goo.gl/APMF6o"
)

val birthday = UserModel(
        name = "Big Shaq",
        userId = 1,
        birthday = dateFormat.parse("10-10-1980"),
        photoUrl = "https://goo.gl/z65EpU"
)

val nothing = UserModel(
        name = "John Zoidberg",
        userId = 2,
        photoUrl = "https://goo.gl/cewV7o"
)

val phoneNumber = UserModel(
        name = "Han Solo",
        userId = 3,
        phoneNumber = "+0-123-4567-8900"
)

val everything = UserModel(
        name = "Noname",
        userId = 4,
        birthday = dateFormat.parse("1-1-1981"),
        phoneNumber = "123",
        email = "123@123.com"
)