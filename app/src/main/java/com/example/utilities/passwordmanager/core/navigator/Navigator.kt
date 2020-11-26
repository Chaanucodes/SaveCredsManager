package com.example.utilities.passwordmanager.core.navigator

import android.app.Activity
import android.content.Intent
import com.example.utilities.passwordmanager.Constants
import com.example.utilities.passwordmanager.feature.about.AboutActivity
import com.example.utilities.passwordmanager.feature.auth.LoginActivity
import com.example.utilities.passwordmanager.feature.backup.BackupActivity
import com.example.utilities.passwordmanager.feature.license.LicenseActivity
import com.example.utilities.passwordmanager.feature.packages.PackageActivity
import com.example.utilities.passwordmanager.feature.splash.SplashActivity

class Navigator(private val activity: Activity) {

    fun setClear(screen: Screen) {
        setAddToBackStack(screen)
        activity.setResult(Activity.RESULT_OK)
        activity.finish()
    }

    fun setAddToBackStack(screen: Screen) {
        val intent = getIntentFromScreen(screen).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        activity.startActivityForResult(intent, Constants.REQUEST_CODE)
    }

    private fun getIntentFromScreen(screen: Screen): Intent {
        return when (screen) {
            Splash -> Intent(activity, SplashActivity::class.java)
            Package -> Intent(activity, PackageActivity::class.java)
            About -> Intent(activity, AboutActivity::class.java)
            Policy -> Intent(activity, LicenseActivity::class.java).apply {
                putExtra(
                    LicenseActivity.TAG_MODE,
                    LicenseActivity.PRIVACY
                )
            }
            License -> Intent(activity, LicenseActivity::class.java).apply {
                putExtra(
                    LicenseActivity.TAG_MODE,
                    LicenseActivity.LICENSE
                )
            }
            Login -> Intent(activity, LoginActivity::class.java)
            Backup -> Intent(activity, BackupActivity::class.java)
        }
    }
}