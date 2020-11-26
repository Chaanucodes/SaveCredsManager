package com.example.utilities.passwordmanager.feature.about

import android.content.Context
import com.example.utilities.passwordmanager.core.asset.AssetManager
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.dependency.scope.AboutScope
import com.example.utilities.passwordmanager.interactor.AssetInteractor
import dagger.Module
import dagger.Provides

@Module
class AboutModule {

    @Provides
    @AboutScope
    fun provideAboutViewModelFactory(
        assetInteractor: AssetInteractor,
        context: Context,
        navigator: Navigator
    ): AboutViewModel.Factory =
        AboutViewModel.Factory(assetInteractor, context, navigator)

    @Provides
    @AboutScope
    fun provideAssetInteractor(assetManager: AssetManager): AssetInteractor = AssetInteractor(assetManager)

    @Provides
    @AboutScope
    fun provideNavigator(aboutActivity: AboutActivity) : Navigator = Navigator(aboutActivity)
}
