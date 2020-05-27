package apps.startup.testapp.base


import android.app.Application
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView

import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

import apps.startup.testapp.R
import apps.startup.testapp.application.TestApplication
import jp.wasabeef.glide.transformations.BlurTransformation

class ImageBindingAdapters {

    @BindingAdapter("imagesetter:imageurl")
    fun setImageUrl(imageView: ImageView, imageurl: String) {
        Log.e("fsdfs", "hey f$imageurl")
        Picasso.get().load(imageurl).placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder).into(imageView)
    }

    @BindingAdapter("half_url")
    fun setImageHalfUrl(imageView: ImageView, url: String) {
        Picasso.get().load(url).placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
            .into(imageView)
    }

    @BindingAdapter("android:src")
    fun loadImage(view: ImageView, id: Int) {
        Picasso.get().load(id).placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
            .into(view)
    }

    @BindingAdapter("android:uri")
    fun loadImage(view: ImageView, id: Uri) {
        Picasso.get().load(id).into(view)
    }

    @BindingAdapter("android:drawable")
    fun loadImageNoPlacehoalder(view: ImageView, id: Int) {
        Picasso.get().load(id).into(view)
    }


    @BindingAdapter("imagesetter:load_thumbnail")
    fun loadThumbNail(view: ImageView, uri: String) {
        Glide.with(TestApplication.instance!!.baseContext).load(uri)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(20, 3)))
            .into(view)
    }

}