package apps.startup.testapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NewData : Serializable {

    @SerializedName("status")
    @Expose
    var status: String = ""
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int = 0
    @SerializedName("articles")
    @Expose
     var articles: ArrayList<Article>? = null

}
