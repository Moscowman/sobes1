package ru.varasoft.sobes_lesson1.di.modules

import dagger.Module
import dagger.Provides
import ru.varasoft.sobes_lesson1.App

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}