package com.android.artgallery.di.builder

import android.arch.lifecycle.ViewModelProvider
import com.android.artgallery.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [(ViewModelsBuilder::class)])
abstract class ViewModelFactoryBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}