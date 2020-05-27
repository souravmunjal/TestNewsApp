package apps.startup.testapp.ui

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import apps.startup.testapp.models.Article
import apps.startup.testapp.Callback
import apps.startup.testapp.models.NewData
import apps.startup.testapp.adapter.NewsItemAdapter
import apps.startup.testapp.api.ApiInterface
import apps.startup.testapp.databinding.ActivityMainBinding
import apps.startup.testapp.utils.Constant.API_KEY
import apps.startup.testapp.utils.MyHelper.Companion.emptyTable
import apps.startup.testapp.utils.MyHelper.Companion.insertData

import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var apiService:ApiInterface

    @Inject
    lateinit var database:SQLiteDatabase

    lateinit var binding :ActivityMainBinding

    lateinit var articleList: ArrayList<Article>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, apps.startup.testapp.R.layout.activity_main)

        if(intent.extras!=null && intent.extras.size()!=0){
            articleList= intent.extras["list"] as (ArrayList<Article>)
        }else{
            articleList=ArrayList<Article>()
        }

        if(articleList.size==0){
            //fetch the data
            fetchTheData()
        }else{
            // put the data on adapter
            putTheData(articleList)
        }
    }
    fun fetchTheData(){
        val dataObservable=apiService.getNews("us",API_KEY);
        dataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<NewData> {
                override fun onSubscribe(@NonNull d: Disposable) {
                }
                override fun onNext(@NonNull newData: NewData) {
                    // put new data in table
                    putNewDataInTable(newData.articles!!)
                    putTheData(newData.articles!!)
                }
                override fun onError(@NonNull e: Throwable) {
                }
                override fun onComplete() {
                }
            })
    }

    private fun putNewDataInTable(data:ArrayList<Article> ) {
        emptyTable(database)
        for (d in data) {
            insertData(
                d.author,
                d.title,
                d.description,
                d.url,
                d.urlToImage,
                d.publishedAt,
                d.content,
                database
            )
        }
    }

    private fun putTheData(list:ArrayList<Article> ){
         var mCustomPagerAdapter = NewsItemAdapter(list,
             object : Callback {
                 override fun onEventDone(`object`: Any) {
                     val i = Intent(this@MainActivity, NewsDetailActivity::class.java)
                     i.putExtra("article", `object` as Article)
                     startActivity(i)
                 }
             })
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        rv_list.layoutManager=layoutManager
        rv_list.adapter=mCustomPagerAdapter
    }
}
