package com.example.utilities.passwordmanager.feature.splash.activate

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.example.utilities.passwordmanager.R
import com.example.utilities.passwordmanager.base.BaseDialogFragment
import com.example.utilities.passwordmanager.feature.splash.SplashViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ActivateDialog : BaseDialogFragment() {

    @Inject
    lateinit var factory: SplashViewModel.Factory

    override fun setupDependencies() {
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val vm = ViewModelProviders.of(activity!!, factory)[SplashViewModel::class.java]

        return AlertDialog.Builder(activity!!)
            .setTitle(R.string.title_activate)
            .setMessage(R.string.message_activate)
            .setPositiveButton(R.string.control_ok) { _, _ ->
                vm.requestFinish(true)
            }
            .setCancelable(false)
            .create()
    }

    override fun onResume() {
        super.onResume()
        dialog?.setCancelable(false)
    }
}