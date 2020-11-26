package com.example.utilities.passwordmanager.dependency

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.example.utilities.passwordmanager.Constants
import com.example.utilities.passwordmanager.core.asset.AssetManager
import com.example.utilities.passwordmanager.core.auth.Authenticator
import com.example.utilities.passwordmanager.core.auth.firebase.FirebaseAuthenticator
import com.example.utilities.passwordmanager.core.autofill.AutofillManager
import com.example.utilities.passwordmanager.core.autofill.impl.AutofillManagerImpl
import com.example.utilities.passwordmanager.core.biometric.BiometricManager
import com.example.utilities.passwordmanager.core.biometric.reprint.ReprintBiometricManager
import com.example.utilities.passwordmanager.core.cipher.Cipher
import com.example.utilities.passwordmanager.core.cipher.aes.AESCipher
import com.example.utilities.passwordmanager.core.clipboard.ClipboardManager
import com.example.utilities.passwordmanager.core.database.PackageDao
import com.example.utilities.passwordmanager.core.database.PasswordDatabase
import com.example.utilities.passwordmanager.core.database.SiteDao
import com.example.utilities.passwordmanager.core.datastore.DataStore
import com.example.utilities.passwordmanager.core.datastore.firebase.FirebaseDataStore
import com.example.utilities.passwordmanager.core.generator.PasswordGenerator
import com.example.utilities.passwordmanager.core.generator.impl.RandomPasswordGenerator
import com.example.utilities.passwordmanager.core.packages.PackageManager
import com.example.utilities.passwordmanager.core.packages.impl.PackageManagerImpl
import com.example.utilities.passwordmanager.model.UserContainer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideBiometricManager(reprintBiometricManager: ReprintBiometricManager): BiometricManager =
        reprintBiometricManager

    @Provides
    @Singleton
    fun provideReprintBiometricManager(): ReprintBiometricManager = ReprintBiometricManager()

    @Provides
    @Singleton
    fun providePackageManager(packageManagerImpl: PackageManagerImpl): PackageManager = packageManagerImpl

    @Provides
    @Singleton
    fun providePackageManagerImpl(context: Context): PackageManagerImpl = PackageManagerImpl(context)

    @Provides
    @Singleton
    fun provideCipher(): Cipher = AESCipher()

    @Provides
    @Singleton
    fun provideClipboardManagerImpl(context: Context): ClipboardManager = ClipboardManager(context)

    @Provides
    @Singleton
    fun provideAssetManager(context: Context): AssetManager = AssetManager(context)

    @Provides
    @Singleton
    fun provideAutofillManager(autofillManagerImpl: AutofillManagerImpl): AutofillManager = autofillManagerImpl

    @Provides
    @Singleton
    fun provideAutofillManagerImpl(context: Context): AutofillManagerImpl = AutofillManagerImpl(context)

    @Provides
    @Singleton
    fun provideDatabase(context: Context): PasswordDatabase {
        return Room.databaseBuilder(context, PasswordDatabase::class.java, Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePackageDao(database: PasswordDatabase): PackageDao = database.packageDao()

    @Provides
    @Singleton
    fun provideSiteDao(database: PasswordDatabase): SiteDao = database.siteDao()

    @Provides
    @Singleton
    fun providePasswordGenerator(randomPasswordGenerator: RandomPasswordGenerator): PasswordGenerator =
        randomPasswordGenerator

    @Provides
    @Singleton
    fun provideRandomPasswordGenerator(): RandomPasswordGenerator = RandomPasswordGenerator()

    @Provides
    @Singleton
    fun provideAuthenticator(firebaseAuthenticator: FirebaseAuthenticator): Authenticator = firebaseAuthenticator

    @Provides
    @Singleton
    fun provideFirebaseAuthenticator(): FirebaseAuthenticator = FirebaseAuthenticator(FirebaseAuth.getInstance())

    @Provides
    @Singleton
    fun provideUserContainer(): UserContainer = UserContainer("")

    @Provides
    @Singleton
    fun provideDataStore(firebaseDataStore: FirebaseDataStore): DataStore = firebaseDataStore

    @Provides
    @Singleton
    fun provideFirebaseDataStore(): FirebaseDataStore {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(false)
            .build()
        val db = FirebaseFirestore.getInstance()
        db.firestoreSettings = settings
        return FirebaseDataStore(db)
    }
}