package com.kindl.data.forbidden.di

import com.kindl.data.forbidden.repositoryimpl.ForbiddenAppRepositoryImpl
import com.kindl.domain.forbidden.repository.ForbiddenAppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ForbiddenRepositoryModule {

    @Binds
    @Singleton
    fun bindForbiddenRepository(
        impl: ForbiddenAppRepositoryImpl
    ) : ForbiddenAppRepository
}
