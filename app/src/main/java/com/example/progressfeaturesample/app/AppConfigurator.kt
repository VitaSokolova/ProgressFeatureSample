package com.example.progressfeaturesample.app

import com.example.progressfeaturesample.app.dagger.AppComponent
import com.example.progressfeaturesample.app.dagger.AppModule
import com.example.progressfeaturesample.app.dagger.DaggerAppComponent

object AppConfigurator {

    var appComponent: AppComponent? = null

    fun initInjector(app: App) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
    }
}