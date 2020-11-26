package com.example.utilities.passwordmanager.feature.about

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utilities.passwordmanager.BuildConfig
import com.example.utilities.passwordmanager.R
import com.example.utilities.passwordmanager.base.BaseViewModel
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.interactor.AssetInteractor

class AboutViewModel private constructor(assetInteractor: AssetInteractor, context: Context, navigator: Navigator) :
    BaseViewModel<AssetInteractor>(assetInteractor, context, navigator) {

    class Factory(private val assetInteractor: AssetInteractor,
                  private val context: Context,
                  private val navigator: Navigator) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return AboutViewModel(assetInteractor, context, navigator) as T
        }
    }

    val title = MutableLiveData<String>()
    val version = MutableLiveData<String>()

    init {
        title.postValue(context.getString(R.string.label_about))
        version.postValue(BuildConfig.VERSION_NAME)
    }
}