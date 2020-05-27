package apps.startup.testapp.base


class MyDataBindingComponent : androidx.databinding.DataBindingComponent {
    override fun getImageBindingAdapters(): ImageBindingAdapters {
        var get = ImageBindingAdapters()
        return get
    }
}
