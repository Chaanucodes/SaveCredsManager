package com.example.utilities.passwordmanager.feature.auth

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.utilities.passwordmanager.core.auth.Authenticator
import com.example.utilities.passwordmanager.core.navigator.Navigator
import com.example.utilities.passwordmanager.dependency.scope.LoginScope
import com.example.utilities.passwordmanager.feature.auth.page.LoginPageFragment
import com.example.utilities.passwordmanager.interactor.LoginInteractor
import com.example.utilities.passwordmanager.model.UserContainer
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Named

@Module(includes = [LoginModule.Bindings::class])
class LoginModule {

    @Module
    interface Bindings {
        @ContributesAndroidInjector
        fun bindLoginPageFragment(): LoginPageFragment
    }

    @Provides
    @LoginScope
    fun provideFragmentManager(activity: LoginActivity): FragmentManager = activity.supportFragmentManager

    @Provides
    @LoginScope
    @Named("login")
    fun provideLoginFragment(): LoginPageFragment = LoginPageFragment.newInstance(LoginPageFragment.LOGIN)

    @Provides
    @LoginScope
    @Named("signup")
    fun provideSignUpFragment(): LoginPageFragment = LoginPageFragment.newInstance(LoginPageFragment.SIGN_UP)

    @Provides
    @LoginScope
    fun provideLoginPagerAdapter(
        fragmentManager: FragmentManager,
        @Named("login") loginFragment: LoginPageFragment,
        @Named("signup") signupFragment: LoginPageFragment
    ) = LoginPagerAdapter(fragmentManager, loginFragment, signupFragment)

    @Provides
    @LoginScope
    fun provideLoginViewModelFactory(
        backupInteractor: LoginInteractor,
        context: Context,
        navigator: Navigator
    ): LoginViewModel.Factory = LoginViewModel.Factory(backupInteractor, context, navigator)

    @Provides
    @LoginScope
    fun provideNavigator(loginActivity: LoginActivity): Navigator = Navigator(loginActivity)

    @Provides
    @LoginScope
    fun provideBackupInteractor(
        authenticator: Authenticator,
        userContainer: UserContainer
    ): LoginInteractor = LoginInteractor(authenticator, userContainer)
}