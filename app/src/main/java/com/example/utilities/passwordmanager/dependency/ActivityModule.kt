package com.example.utilities.passwordmanager.dependency

import com.example.utilities.passwordmanager.dependency.scope.*
import com.example.utilities.passwordmanager.feature.about.AboutActivity
import com.example.utilities.passwordmanager.feature.about.AboutModule
import com.example.utilities.passwordmanager.feature.auth.LoginActivity
import com.example.utilities.passwordmanager.feature.auth.LoginModule
import com.example.utilities.passwordmanager.feature.backup.BackupActivity
import com.example.utilities.passwordmanager.feature.backup.BackupModule
import com.example.utilities.passwordmanager.feature.license.LicenseActivity
import com.example.utilities.passwordmanager.feature.license.LicenseModule
import com.example.utilities.passwordmanager.feature.main.MainActivity
import com.example.utilities.passwordmanager.feature.main.MainModule
import com.example.utilities.passwordmanager.feature.packages.PackageActivity
import com.example.utilities.passwordmanager.feature.packages.PackageModule
import com.example.utilities.passwordmanager.feature.splash.SplashActivity
import com.example.utilities.passwordmanager.feature.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @SplashScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    internal abstract fun bindSplashActivity(): SplashActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @PackageScope
    @ContributesAndroidInjector(modules = [PackageModule::class])
    internal abstract fun bindPackageActivity(): PackageActivity

    @AboutScope
    @ContributesAndroidInjector(modules = [AboutModule::class])
    internal abstract fun bindAboutActivity(): AboutActivity

    @LicenseScope
    @ContributesAndroidInjector(modules = [LicenseModule::class])
    internal abstract fun bindLicenseActivity(): LicenseActivity

    @LoginScope
    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun bindLoginActivity(): LoginActivity

    @BackupScope
    @ContributesAndroidInjector(modules = [BackupModule::class])
    internal abstract fun bindBackupActivity(): BackupActivity
}
