package com.kindl.core.localstorage.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.kindl.core.localstorage.constant.DataStoreConstant.DATA_STORE_NAME
import com.kindl.core.localstorage.permissions.PermissionInfoManager
import com.kindl.core.localstorage.permissions.PermissionInfoManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideOnboardingManager(
        dataStore: DataStore<Preferences>,
    ): PermissionInfoManager {
        return PermissionInfoManagerImpl(dataStore)
    }
}