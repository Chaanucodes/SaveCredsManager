package com.example.utilities.passwordmanager.feature.splash

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.service.autofill.Dataset
import android.view.autofill.AutofillManager.EXTRA_AUTHENTICATION_RESULT
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.utilities.passwordmanager.R
import com.example.utilities.passwordmanager.base.BaseActivity
import com.example.utilities.passwordmanager.databinding.ActivitySplashBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var factory: SplashViewModel.Factory

    companion object {
        const val EXTRA_DATASET = "dataset"
        private var requestCode = 0

        fun newIntentSender(context: Context, dataset: Dataset): IntentSender {
            val intent = Intent(context, SplashActivity::class.java)
            intent.putExtra(EXTRA_DATASET, dataset)

            return PendingIntent.getActivity(context, ++requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT)
                .intentSender
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun setupBindingAndViewModel(binding: ActivitySplashBinding) {
        val vm = ViewModelProviders.of(this, factory)[SplashViewModel::class.java]
        binding.viewModel = vm

        vm.needsClose.observe(this, Observer {
            finish(if (it) Activity.RESULT_OK else Activity.RESULT_CANCELED)
        })
    }

    override fun finish(result: Int, intent: Intent) {
        val dataset = this@SplashActivity.intent.getParcelableExtra<Dataset>(EXTRA_DATASET)
        if (result == Activity.RESULT_OK && dataset != null) {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_AUTHENTICATION_RESULT, dataset)
            super.finish(result, replyIntent)
            return
        }
        super.finish(result, intent)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    override val layoutId: Int = R.layout.activity_splash
}