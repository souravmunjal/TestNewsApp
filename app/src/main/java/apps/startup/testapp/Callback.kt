package apps.startup.testapp

import java.io.Serializable

import apps.startup.testapp.adapter.NewsItemAdapter


interface Callback : Serializable {
    fun onEventDone(`object`: Any)
}
