package com.tigerspike.ui.detail

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Images
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tigerspike.R
import com.tigerspike.databinding.ActivityDetailBinding
import com.tigerspike.di.AppContext
import com.tigerspike.exception.MissingArgumentException
import com.tigerspike.extension.persistTempoary
import com.tigerspike.extension.toBitmap
import com.tigerspike.model.Photo
import com.tigerspike.ui.commons.BaseActivity
import com.tigerspike.ui.commons.EventObserver
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject

@RuntimePermissions
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val layoutId = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        AppContext.component.inject(this)
        super.onCreate(savedInstanceState)
        receiveParameter()
        observerEvents()
    }

    private fun receiveParameter() {
        if (intent.hasExtra(photoKey)) {
            val photo = intent.getParcelableExtra<Photo>(photoKey)
            viewModel.setup(photo)
        } else {
            throw MissingArgumentException(photoKey)
        }
    }

    override fun providesViewModel(): DetailViewModel {
        return ViewModelProviders.of(this, factory)[DetailViewModel::class.java]
    }

    private fun observerEvents() {
        viewModel.share.observe(this, EventObserver {
            shareImageWithPermissionCheck()
        })
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    internal fun shareImage() {
        val bitmap = binding.image.toBitmap()
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/jpeg"
        val values = ContentValues()
        values.put(Images.Media.MIME_TYPE, "image/jpeg")
        val uri = contentResolver.insert(Images.Media.EXTERNAL_CONTENT_URI, values)
        bitmap.persistTempoary(this, uri)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(intent, getString(R.string.activity_detail_share_title)))
    }

    companion object {
        const val photoKey = "DetailActivity.photoKey"
    }

}