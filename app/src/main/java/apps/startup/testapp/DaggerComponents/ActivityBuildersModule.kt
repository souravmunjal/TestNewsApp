package apps.startup.testapp.DaggerComponents

import apps.startup.testapp.ui.MainActivity
import apps.startup.testapp.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

}
