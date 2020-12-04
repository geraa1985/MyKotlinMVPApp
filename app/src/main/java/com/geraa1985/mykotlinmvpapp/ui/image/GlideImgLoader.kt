package com.geraa1985.mykotlinmvpapp.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.geraa1985.mykotlinmvpapp.mvp.model.ILoadImage

class GlideImgLoader: ILoadImage<ImageView, RequestOptions> {

    override fun loadInto(url: String, container: ImageView, options: RequestOptions?) {
        options?.let{
            Glide
                .with(container.context)
                .load(url)
                .apply(options)
                .into(container)
        } ?: run {
            Glide
                .with(container.context)
                .load(url)
                .into(container)
        }
    }

}