package com.geraa1985.mykotlinmvpapp.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.geraa1985.mykotlinmvpapp.mvp.model.ILoadImage

class GlideImgLoader: ILoadImage<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide
            .with(container.context)
            .load(url)
            .circleCrop()
            .into(container)
    }

}