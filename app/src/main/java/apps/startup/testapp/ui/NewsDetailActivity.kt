package apps.startup.testapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import apps.startup.testapp.models.Article
import apps.startup.testapp.databinding.ActivityNewsDetailBinding
import android.content.Intent
import android.net.Uri
import apps.startup.testapp.R
import android.view.MenuItem
import java.util.*


class NewsDetailActivity : AppCompatActivity() {
    lateinit var article: Article
    lateinit var binding : ActivityNewsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityNewsDetailBinding>(this, R.layout.activity_news_detail)
        Objects.requireNonNull(getSupportActionBar())?.setDisplayHomeAsUpEnabled(true);
        article= intent.extras["article"] as (Article)
        binding.obj=article
        binding.readMore.setOnClickListener {
            val uri = Uri.parse(article.url) // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.getItemId() === android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(menuItem)
    }

}
