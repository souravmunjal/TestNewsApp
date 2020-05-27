package apps.startup.testapp.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import java.util.ArrayList

import apps.startup.testapp.models.Article

class MyHelper(context: Context) : SQLiteOpenHelper(context, dbname, null, version) {
    private val data: ArrayList<Article>? = null

    override fun onCreate(db: SQLiteDatabase) {
        val sql =
            "CREATE TABLE NEWS_ITEMS(_id INTEGER PRIMARY KEY AUTOINCREMENT,source TEXT,author TEXT,title TEXT,description TEXT,url TEXT,urlToImage TEXT,publishedAt TEXT,content TEXT)"
        db.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private val dbname = "MY_DB"
        private val version = 1
        fun insertData(
            author: String?,
            title: String?,
            description: String?,
            url: String?,
            urlToImage: String?,
            publishedAt: String?,
            content: String?,
            database: SQLiteDatabase
        ) {
            val values = ContentValues()
            values.put("author", author)
            values.put("title", title)
            values.put("description", description)
            values.put("url", url)
            values.put("urlToImage", urlToImage)
            values.put("publishedAt", publishedAt)
            values.put("content", content)
            database.insert("NEWS_ITEMS", null, values)
        }

        fun emptyTable(db: SQLiteDatabase) {
            db.execSQL("delete from " + Constant.TABLE_NAME)
        }
    }
}
