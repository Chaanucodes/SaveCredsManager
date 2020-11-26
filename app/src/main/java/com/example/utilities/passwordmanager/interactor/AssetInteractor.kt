package com.example.utilities.passwordmanager.interactor

import android.text.Html
import android.text.Spanned
import com.example.utilities.passwordmanager.base.BaseInteractor
import com.example.utilities.passwordmanager.core.asset.AssetManager
import io.reactivex.Single

class AssetInteractor(private val assetManager: AssetManager) : BaseInteractor {

    fun getStringFromAsset(assetName: String): Single<Spanned> {
        return Single.fromCallable {
            Html.fromHtml(assetManager.getStringFromAsset(assetName), Html.FROM_HTML_MODE_LEGACY)
        }
    }
}