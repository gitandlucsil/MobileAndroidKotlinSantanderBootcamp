package br.com.andlucsil.injecaodependencia.di

import br.com.andlucsil.injecaodependencia.HelloModel
import br.com.andlucsil.injecaodependencia.HelloModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsHelloModel(model: HelloModelImpl): HelloModel
}