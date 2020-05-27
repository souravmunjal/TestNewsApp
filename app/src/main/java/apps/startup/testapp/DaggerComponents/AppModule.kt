package apps.startup.testapp.DaggerComponents

import android.database.sqlite.SQLiteDatabase

import java.util.concurrent.TimeUnit

import apps.startup.testapp.api.ApiInterface

import apps.startup.testapp.application.TestApplication
import apps.startup.testapp.utils.MyHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    internal val baseURLService: String
        @Provides
        get() = "https://newsapi.org/"

    internal val okHttpBuilderService: OkHttpClient.Builder
        @Provides
        get() = OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS)

    internal val sqLiteDatabaseService: SQLiteDatabase
        @Provides
        get() {
            val helper = MyHelper(TestApplication.instance!!.baseContext)
            return helper.writableDatabase
        }

    @Provides
    internal fun getRetrofitService(BASE_URL: String, httpClient: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    internal fun getApiInterfaceService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

}
