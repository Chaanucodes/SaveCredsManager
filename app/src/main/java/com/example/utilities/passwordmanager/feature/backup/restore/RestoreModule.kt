package com.example.utilities.passwordmanager.feature.backup.restore

import android.content.Context
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.interactor.BackupInteractor
import dagger.Module
import dagger.Provides

@Module
class RestoreModule {
    @Provides
    fun provideRestoreViewModelFactory(
        backupInteractor: BackupInteractor,
        context: Context,
        navigator: Navigator
    ): RestoreViewModel.Factory =
        RestoreViewModel.Factory(backupInteractor, context, navigator)
}