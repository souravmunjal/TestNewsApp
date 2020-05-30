package apps.startup.testapp.ui

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import apps.startup.testapp.models.Article
import android.widget.Toast
import apps.startup.testapp.R
import apps.startup.testapp.utils.AppUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class SplashActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var database:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //network connectivity
        if(AppUtils.isNetworkAvailable){
            //proceeds to next activity with any problems
            var intent=Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            var articleList=getTheListOfNews()
            if(articleList.size==0){
                Toast.makeText(this@SplashActivity,"IT'S YOUR FIRST TIME! Please check your connection",Toast.LENGTH_LONG).show()
                finish()
            }else{
                //Proceeds to next activity
                var intent=Intent(this@SplashActivity,MainActivity::class.java)
                intent.putExtra("list",articleList)
                startActivity(intent)
                finish()
            }
        }
    }

    fun getTheListOfNews():ArrayList<Article>{
        var s: Array<String>? = null
        var articleList=ArrayList<Article>()
        var cursor = database.rawQuery("SELECT * FROM NEWS_ITEMS", s)
        if(cursor!=null)
            cursor.moveToFirst();

        if(cursor.count==0)
            return articleList

        do {
            var article= Article()
            article.author=cursor?.getString(2);
            article.title=cursor?.getString(3);
            article.description=cursor?.getString(4);
            article.url=cursor?.getString(5);
            article.urlToImage=cursor?.getString(6);
            article.publishedAt=cursor?.getString(7);
            article.content=cursor?.getString(8);
            articleList.add(article)
        }while (cursor.moveToNext())
        return articleList
    }

}
