package ru.varasoft.sobes_lesson1

import android.app.Application
import ru.varasoft.sobes_lesson1.di.AppComponent
import ru.varasoft.sobes_lesson1.di.DaggerAppComponent
import ru.varasoft.sobes_lesson1.di.modules.AppModule
import javax.inject.Inject

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}