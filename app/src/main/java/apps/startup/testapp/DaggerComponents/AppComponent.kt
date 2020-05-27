package apps.startup.testapp.DaggerComponents

import android.app.Application

import javax.inject.Singleton

import apps.startup.testapp.api.ApiInterface
import apps.startup.testapp.application.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Singleton
@Component(modules = [AppModule::class, ActivityBuildersModule::class, AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<TestApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: TestApplication)
}

