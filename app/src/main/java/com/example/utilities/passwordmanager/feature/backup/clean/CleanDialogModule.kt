package com.example.utilities.passwordmanager.feature.backup.clean

import android.content.Context
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.interactor.BackupInteractor
import dagger.Module
import dagger.Provides

@Module
class CleanDialogModule {
    @Provides
    fun provideCleanDialogViewModelFactory(
        backupInteractor: BackupInteractor,
        context: Context,
        navigator: Navigator
    ): CleanDialogViewModel.Factory =
        CleanDialogViewModel.Factory(backupInteractor, context, navigator)
}