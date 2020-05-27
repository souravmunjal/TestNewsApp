package apps.startup.testapp.api

import apps.startup.testapp.models.NewData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("v2/top-headlines")
    fun getNews(@Query("country") country: String, @Query("apiKey") key: String): Observable<NewData>
}