package com.example.utilities.passwordmanager.feature.backup

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utilities.passwordmanager.R
import com.example.utilities.passwordmanager.base.BaseViewModel
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.interactor.BackupInteractor
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class BackupViewModel private constructor(
    backupInteractor: BackupInteractor,
    context: Context,
    navigator: Navigator,
    private val dialogManager: BackupDialogManager
) :
    BaseViewModel<BackupInteractor>(backupInteractor, context, navigator) {

    class Factory(
        private val backupInteractor: BackupInteractor,
        private val context: Context,
        private val navigator: Navigator,
        private val dialogManager: BackupDialogManager
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return BackupViewModel(backupInteractor, context, navigator, dialogManager) as T
        }
    }

    val title = MutableLiveData<String>()
    val keyAvailable = MutableLiveData<Boolean>()

    init {
        title.postValue(context.getString(R.string.title_backup))
        subscriptions += interactor.getEncryptedIdentifier()
            .subscribeOn(Schedulers.io())
            .subscribe { keyAvailable.postValue(it.isNotEmpty()) }
    }

    fun showRegisterKeyDialog() {
        dialogManager.showDialog(BackupDialogManager.Dialogs.REGISTER_KEY)
    }

    fun showBackupDialog() {
        dialogManager.showDialog(BackupDialogManager.Dialogs.BACKUP)
    }

    fun showRestoreDialog() {
        dialogManager.showDialog(BackupDialogManager.Dialogs.RESTORE)
    }

    fun showCleanDialog() {
        dialogManager.showDialog(BackupDialogManager.Dialogs.CLEAN)
    }
}