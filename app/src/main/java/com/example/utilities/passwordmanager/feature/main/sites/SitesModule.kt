package com.example.utilities.passwordmanager.feature.main.sites

import dagger.Module
import dagger.Provides

@Module
class SitesModule {

    @Provides
    fun provideSiteAdapter(): SitesAdapter = SitesAdapter()
}