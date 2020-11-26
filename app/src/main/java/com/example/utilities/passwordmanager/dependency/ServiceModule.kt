package com.example.utilities.passwordmanager.dependency

import com.example.utilities.passwordmanager.dependency.scope.*
import com.example.utilities.passwordmanager.service.FillService
import com.example.utilities.passwordmanager.service.FillServiceModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ServiceModule {

    @ServiceScope
    @ContributesAndroidInjector(modules = [FillServiceModule::class])
    internal abstract fun bindFillService(): FillService
}
