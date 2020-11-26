package com.example.utilities.passwordmanager.interactor

import com.example.utilities.passwordmanager.base.BaseInteractor
import com.example.utilities.passwordmanager.core.auth.Authenticator
import com.example.utilities.passwordmanager.model.UserContainer
import io.reactivex.Single

class LoginInteractor(
    private val authenticator: Authenticator,
    private val userContainer: UserContainer
) : BaseInteractor {

    fun signup(email: String, pass: String) = authenticator.signup(email, pass)
        .doOnSuccess { userContainer.userId = it }

    fun login(email: String, pass: String): Single<String> = authenticator.login(email, pass)
        .doOnSuccess { userContainer.userId = it }
}