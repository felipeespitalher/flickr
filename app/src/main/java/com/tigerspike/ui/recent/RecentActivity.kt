package com.tigerspike.ui.recent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.tigerspike.R
import com.tigerspike.databinding.ActivityRecentBinding
import com.tigerspike.di.AppContext
import com.tigerspike.ui.commons.BaseActivity
import com.tigerspike.ui.commons.EventObserver
import com.tigerspike.ui.detail.DetailActivity
import javax.inject.Inject

class RecentActivity : BaseActivity<ActivityRecentBinding, RecentViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val layoutId = R.layout.activity_recent

    override fun onCreate(savedInstanceState: Bundle?) {
        AppContext.component.inject(this)
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        observeEvents()
        viewModel.startUp()
    }

    override fun providesViewModel(): RecentViewModel {
        return ViewModelProviders.of(this, factory)[RecentViewModel::class.java]
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = FlexboxLayoutManager(this)
        gridLayoutManager.flexDirection = FlexDirection.ROW
        gridLayoutManager.justifyContent = JustifyContent.FLEX_END

        binding.recyclerView.apply {
            adapter = viewModel.adapter
            layoutManager = gridLayoutManager
        }
    }

    private fun observeEvents() {
        viewModel.errorEvent.observe(this, EventObserver {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.itemClick.observe(this, EventObserver { pair ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.photoKey, pair.first)
            startActivity(intent)
        })
    }

}
