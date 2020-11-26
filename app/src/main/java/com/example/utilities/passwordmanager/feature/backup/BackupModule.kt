package com.example.utilities.passwordmanager.feature.backup

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.utilities.passwordmanager.core.cipher.Cipher
import com.example.utilities.passwordmanager.core.database.PackageDao
import com.example.utilities.passwordmanager.core.database.SiteDao
import com.example.utilities.passwordmanager.core.datastore.DataStore
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.dependency.scope.BackupScope
import com.example.utilities.passwordmanager.feature.backup.backup.BackupDialog
import com.example.utilities.passwordmanager.feature.backup.backup.BackupDialogModule
import com.example.utilities.passwordmanager.feature.backup.clean.CleanDialog
import com.example.utilities.passwordmanager.feature.backup.clean.CleanDialogModule
import com.example.utilities.passwordmanager.feature.backup.key.RegisterKeyDialog
import com.example.utilities.passwordmanager.feature.backup.key.RegisterKeyModule
import com.example.utilities.passwordmanager.feature.backup.restore.RestoreDialog
import com.example.utilities.passwordmanager.feature.backup.restore.RestoreModule
import com.example.utilities.passwordmanager.interactor.BackupInteractor
import com.example.utilities.passwordmanager.model.UserContainer
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [BackupModule.Bindings::class])
class BackupModule {

    @Module
    interface Bindings {
        @ContributesAndroidInjector(modules = [RegisterKeyModule::class])
        fun bindRegisterKeyDialog(): RegisterKeyDialog

        @ContributesAndroidInjector(modules = [BackupDialogModule::class])
        fun bindBackupDialog(): BackupDialog

        @ContributesAndroidInjector(modules = [RestoreModule::class])
        fun bindRestoreDialog(): RestoreDialog

        @ContributesAndroidInjector(modules = [CleanDialogModule::class])
        fun bindCleanDialog(): CleanDialog
    }

    @Provides
    @BackupScope
    fun provideBackupViewModelFactory(
        backupInteractor: BackupInteractor,
        context: Context,
        navigator: Navigator,
        backupDialogManager: BackupDialogManager
    ): BackupViewModel.Factory =
        BackupViewModel.Factory(backupInteractor, context, navigator, backupDialogManager)

    @Provides
    @BackupScope
    fun provideBackupInteractor(
        cipher: Cipher,
        dataStore: DataStore,
        packageDao: PackageDao,
        siteDao: SiteDao,
        userContainer: UserContainer
    ): BackupInteractor = BackupInteractor(cipher, dataStore, packageDao, siteDao, userContainer)

    @Provides
    @BackupScope
    fun provideNavigator(backupActivity: BackupActivity): Navigator = Navigator(backupActivity)

    @Provides
    @BackupScope
    fun provideFragmentManager(backupActivity: BackupActivity): FragmentManager = backupActivity.supportFragmentManager

    @Provides
    @BackupScope
    fun provideDialogManager(
        fragmentManager: FragmentManager
    ): BackupDialogManager =
        BackupDialogManager(fragmentManager)
}