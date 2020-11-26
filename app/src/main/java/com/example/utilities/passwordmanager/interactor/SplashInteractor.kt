package com.example.utilities.passwordmanager.interactor

import com.example.utilities.passwordmanager.base.BaseInteractor
import com.example.utilities.passwordmanager.core.autofill.AutofillManager
import com.example.utilities.passwordmanager.core.biometric.BiometricManager
import io.reactivex.Single


class SplashInteractor(private val biometricManager: BiometricManager,
                       private val autofillManager: AutofillManager) : BaseInteractor {

    fun isFingerprintSupported() = biometricManager.isSupported()

    fun hasFingerprintRegistered() = biometricManager.hasRegisteredFingerprints()

    fun authenticate() = biometricManager.authenticate()

    fun isAutofillEnabled(): Single<Boolean> = autofillManager.isEnabled()
}