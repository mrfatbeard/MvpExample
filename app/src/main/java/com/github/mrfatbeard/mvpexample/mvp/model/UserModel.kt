package com.github.mrfatbeard.mvpexample.mvp.model

import java.util.*

data class UserModel(val name: String,
                     val userId: Long,
                     val birthday: Date? = null,
                     val phoneNumber: String? = null,
                     val email: String? = null,
                     val photoUrl: String? = null) {

    fun hasEmail(): Boolean = email?.isNotEmpty() == true

    fun hasBirthday(): Boolean = birthday != null

    fun hasPhoneNumber(): Boolean = phoneNumber?.isNotEmpty() == true
}