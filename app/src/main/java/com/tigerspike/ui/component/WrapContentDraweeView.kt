package com.tigerspike.ui.component


import android.content.Context
import android.graphics.drawable.Animatable
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo
import com.tigerspike.model.Photo


class WrapContentDraweeView : SimpleDraweeView {

    constructor(context: Context, hierarchy: GenericDraweeHierarchy) : super(context, hierarchy)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private val listener = object : BaseControllerListener<ImageInfo>() {

        override fun onIntermediateImageSet(id: String?, imageInfo: ImageInfo?) {
            updateViewSize(imageInfo)
        }

        override fun onFinalImageSet(id: String?, @Nullable imageInfo: ImageInfo?, @Nullable animatable: Animatable?) {
            updateViewSize(imageInfo)
        }
    }

    internal fun updateViewSize(@Nullable imageInfo: ImageInfo?) {
        if (imageInfo != null) {
            aspectRatio = imageInfo.width / imageInfo.height.toFloat()
            layoutParams.width = imageInfo.width
            layoutParams.height = imageInfo.height
            requestLayout()
        }
    }

    fun setup(uri: String) {
        val controller = (controllerBuilder as PipelineDraweeControllerBuilder)
                .setControllerListener(listener)
                .setCallerContext(context)
                .setUri(uri)
                .setOldController(controller)
                .build()
        setController(controller)
        hierarchy.setProgressBarImage(ProgressBarDrawable())
    }
}

@BindingAdapter("app:imageUrl", requireAll = false)
fun setPhotoFull(view: WrapContentDraweeView, photo: Photo?) {
    photo?.let {
        with(photo) {
            val url = "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"
            view.setup(url)

        }
    }
}

@BindingAdapter("app:thumbs", requireAll = false)
fun setPhotoThumbs(view: WrapContentDraweeView, photo: Photo?) {
    photo?.let {
        with(photo) {
            val url = "https://farm$farm.staticflickr.com/$server/${id}_${secret}_z.jpg"
            view.setup(url)
        }
    }
}