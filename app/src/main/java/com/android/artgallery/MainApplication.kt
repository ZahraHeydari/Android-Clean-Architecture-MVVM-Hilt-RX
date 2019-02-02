package com.android.artgallery

import android.app.Activity
import android.app.Application
import com.android.artgallery.di.component.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import kotlin.properties.Delegates

class MainApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector() = activityInjector

    companion object {

        private val TAG = MainApplication::class.java.simpleName
        var instance: MainApplication by Delegates.notNull()
    }

}