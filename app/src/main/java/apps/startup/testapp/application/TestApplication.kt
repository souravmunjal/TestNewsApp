package apps.startup.testapp.application

import androidx.databinding.DataBindingUtil
import apps.startup.testapp.DaggerComponents.DaggerAppComponent
import apps.startup.testapp.base.MyDataBindingComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class TestApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        DataBindingUtil.setDefaultComponent(MyDataBindingComponent() as androidx.databinding.DataBindingComponent)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    companion object {
        @JvmStatic var instance: TestApplication? = null
            private set
    }
}
