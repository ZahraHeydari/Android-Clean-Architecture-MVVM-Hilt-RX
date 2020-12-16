package com.android.artgallery

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.android.artgallery.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlin.properties.Delegates


@HiltAndroidApp
class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}