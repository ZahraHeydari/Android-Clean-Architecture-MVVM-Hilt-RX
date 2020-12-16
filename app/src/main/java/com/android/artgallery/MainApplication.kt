package com.android.artgallery

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.android.artgallery.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import kotlin.properties.Delegates



class MainApplication : Application(),HasAndroidInjector{

    private val TAG = MainApplication::class.java.name
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        var instance: MainApplication by Delegates.notNull()
    }

    override fun androidInjector(): AndroidInjector<Any>  =
        activityInjector

}