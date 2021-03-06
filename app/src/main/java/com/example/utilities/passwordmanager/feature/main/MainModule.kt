package com.example.utilities.passwordmanager.feature.main

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.utilities.passwordmanager.core.cipher.Cipher
import com.example.utilities.passwordmanager.core.clipboard.ClipboardManager
import com.example.utilities.passwordmanager.core.database.PackageDao
import com.example.utilities.passwordmanager.core.database.SiteDao
import com.example.utilities.passwordmanager.core.generator.PasswordGenerator
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.core.packages.PackageManager
import com.example.utilities.passwordmanager.dependency.scope.MainScope
import com.example.utilities.passwordmanager.feature.main.addsites.AddSitesDialog
import com.example.utilities.passwordmanager.feature.main.apps.AppFragment
import com.example.utilities.passwordmanager.feature.main.apps.AppViewModule
import com.example.utilities.passwordmanager.feature.main.backup.BackupDialog
import com.example.utilities.passwordmanager.feature.main.common.PackageViewModel
import com.example.utilities.passwordmanager.feature.main.common.SitesViewModel
import com.example.utilities.passwordmanager.feature.main.sites.SitesFragment
import com.example.utilities.passwordmanager.feature.main.sites.SitesModule
import com.example.utilities.passwordmanager.feature.main.view.ViewPackageDialog
import com.example.utilities.passwordmanager.feature.main.view.ViewSitesDialog
import com.example.utilities.passwordmanager.interactor.MainInteractor
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [MainModule.Bindings::class])
class MainModule {

    @Module
    interface Bindings {

        @ContributesAndroidInjector(modules = [AppViewModule::class])
        fun bindAppFragment(): AppFragment

        @ContributesAndroidInjector(modules = [SitesModule::class])
        fun bindSitesFragment(): SitesFragment

        @ContributesAndroidInjector
        fun bindAddSitesDialog(): AddSitesDialog

        @ContributesAndroidInjector
        fun bindViewPackageDialog(): ViewPackageDialog

        @ContributesAndroidInjector
        fun bindViewSitesDialog(): ViewSitesDialog

        @ContributesAndroidInjector
        fun bindBackupDialog(): BackupDialog
    }

    @Provides
    @MainScope
    fun provideMainViewModelFactory(
        mainInteractor: MainInteractor,
        context: Context,
        navigator: Navigator,
        fragmentManager: FragmentManager
    ): MainViewModel.Factory =
        MainViewModel.Factory(mainInteractor, context, navigator, fragmentManager)

    @Provides
    @MainScope
    fun provideNavigator(mainActivity: MainActivity): Navigator = Navigator(mainActivity)

    @Provides
    @MainScope
    fun provideMainInteractor(
        packageDao: PackageDao, siteDao: SiteDao, packageManager: PackageManager,
        cipher: Cipher, context: Context, clipboardManager: ClipboardManager,
        passwordGenerator: PasswordGenerator
    ): MainInteractor =
        MainInteractor(
            packageDao,
            siteDao,
            packageManager,
            cipher,
            context,
            clipboardManager,
            passwordGenerator
        )

    @Provides
    @MainScope
    fun provideFragmentManager(activity: MainActivity): FragmentManager = activity.supportFragmentManager

    @Provides
    @MainScope
    fun provideMainViewPagerAdapter(
        fragmentManager: FragmentManager,
        context: Context,
        appFragment: AppFragment,
        sitesFragment: SitesFragment
    ): MainViewPagerAdapter = MainViewPagerAdapter(fragmentManager, context, appFragment, sitesFragment)

    @Provides
    fun providePackageViewModelFactory(
        mainInteractor: MainInteractor,
        context: Context,
        navigator: Navigator
    ): PackageViewModel.Factory = PackageViewModel.Factory(mainInteractor, context, navigator)

    @Provides
    fun provideSitesViewModelFactory(
        mainInteractor: MainInteractor,
        context: Context,
        navigator: Navigator
    ): SitesViewModel.Factory =
        SitesViewModel.Factory(mainInteractor, context, navigator)

    @Provides
    @MainScope
    fun provideAppFragment(): AppFragment = AppFragment()

    @Provides
    @MainScope
    fun provideSitesFragment(): SitesFragment = SitesFragment()
}