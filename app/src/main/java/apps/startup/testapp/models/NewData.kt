package apps.startup.testapp.models

import java.io.Serializable
import java.util.ArrayList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import apps.startup.testapp.models.Article

class NewData : Serializable {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null
    @SerializedName("articles")
    @Expose
    var articles: ArrayList<Article>? = null

}
