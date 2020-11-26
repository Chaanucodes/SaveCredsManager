package com.example.utilities.passwordmanager.feature.packages

import android.content.Context
import com.example.utilities.passwordmanager.core.cipher.Cipher
import com.example.utilities.passwordmanager.core.database.PackageDao
import com.example.utilities.passwordmanager.core.generator.PasswordGenerator
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.core.packages.PackageManager
import com.example.utilities.passwordmanager.dependency.scope.PackageScope
import com.example.utilities.passwordmanager.feature.packages.add.AddPackageDialog
import com.example.utilities.passwordmanager.interactor.PackageInteractor
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [PackageModule.Bindings::class])
class PackageModule {

    @Module
    interface Bindings {
        @ContributesAndroidInjector
        fun bindAddPackageDialog(): AddPackageDialog
    }

    @Provides
    @PackageScope
    fun providePackageViewModelFactory(
        packageInteractor: PackageInteractor,
        context: Context,
        navigator: Navigator
    ): PackageViewModel.Factory =
        PackageViewModel.Factory(packageInteractor, context, navigator)

    @Provides
    @PackageScope
    fun provideNavigator(packageActivity: PackageActivity): Navigator = Navigator(packageActivity)

    @Provides
    @PackageScope
    fun providePackageInteractor(
        context: Context,
        packageManager: PackageManager,
        cipher: Cipher,
        packageDao: PackageDao,
        passwordGenerator: PasswordGenerator
    ):
            PackageInteractor =
        PackageInteractor(context, packageManager, cipher, packageDao, passwordGenerator)

    @Provides
    @PackageScope
    fun providePackageAdapter(): PackageAdapter = PackageAdapter()
}