package apps.startup.testapp.adapter

import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

import apps.startup.testapp.models.Article
import apps.startup.testapp.Callback
import apps.startup.testapp.R
import apps.startup.testapp.base.BaseAdapter

class NewsItemAdapter(internal var articles: ArrayList<Article>, internal var callback: Callback) :
    BaseAdapter() {
    override fun getDataAtPosition(position: Int): Any {
        return articles[position]
    }

    override fun getLayoutIdForType(viewType: Int): Int {
        return R.layout.news_item_layout
    }

    override fun onItemClick(`object`: Any, position: Int) {
        callback.onEventDone(`object`)
    }

    override fun editHeightWidthItem(view: View, parent: ViewGroup) {

    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
