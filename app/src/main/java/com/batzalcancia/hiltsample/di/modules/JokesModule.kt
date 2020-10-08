package com.batzalcancia.hiltsample.di.modules

import com.batzalcancia.hiltsample.data.JokesRepositoryImpl
import com.batzalcancia.hiltsample.data.remote.JokesApi
import com.batzalcancia.hiltsample.domain.repositories.JokesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
object JokesModule  {

    @Provides
    fun providesJokesRepository(
        jokesApi: JokesApi
    ): JokesRepository = JokesRepositoryImpl(jokesApi)

}