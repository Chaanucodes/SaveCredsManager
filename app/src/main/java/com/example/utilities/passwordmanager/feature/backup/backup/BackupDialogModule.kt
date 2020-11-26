package com.example.utilities.passwordmanager.feature.backup.backup

import android.content.Context
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.interactor.BackupInteractor
import dagger.Module
import dagger.Provides

@Module
class BackupDialogModule {
    @Provides
    fun provideBackupDialogViewModelFactory(
        backupInteractor: BackupInteractor,
        context: Context,
        navigator: Navigator
    ): BackupDialogViewModel.Factory =
        BackupDialogViewModel.Factory(backupInteractor, context, navigator)
}