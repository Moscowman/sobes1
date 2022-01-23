package ru.varasoft.sobes_lesson1.di

import dagger.Component
import ru.varasoft.sobes_lesson1.App
import ru.varasoft.sobes_lesson1.di.modules.AppModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)
}