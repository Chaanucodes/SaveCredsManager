package com.example.utilities.passwordmanager.core.autofill

import io.reactivex.Single

interface AutofillManager {
    fun isEnabled() : Single<Boolean>
}