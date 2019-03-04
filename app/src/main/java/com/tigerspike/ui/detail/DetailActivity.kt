package com.tigerspike.ui.detail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tigerspike.R
import com.tigerspike.databinding.ActivityDetailBinding
import com.tigerspike.di.AppContext
import com.tigerspike.exception.MissingArgumentException
import com.tigerspike.model.Photo
import com.tigerspike.ui.commons.BaseActivity
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val layoutId = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        AppContext.component.inject(this)
        super.onCreate(savedInstanceState)
        receiveParameter()
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

    companion object {
        const val photoKey = "DetailActivity.photoKey"
    }

}