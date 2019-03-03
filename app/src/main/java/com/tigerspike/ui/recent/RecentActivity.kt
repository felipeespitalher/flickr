package com.tigerspike.ui.recent

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.tigerspike.R
import com.tigerspike.databinding.ActivityRecentBinding
import com.tigerspike.di.AppContext
import com.tigerspike.ui.commons.EventObserver
import javax.inject.Inject

class RecentActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding: ActivityRecentBinding
    private lateinit var viewModel: RecentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AppContext.component.inject(this)
        super.onCreate(savedInstanceState)
        setupLayout()
        setupViewModel()
        setupRecyclerView()
        observeEvents()
    }

    private fun setupLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, factory)[RecentViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.startUp()
    }

    private fun setupRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, resources.getInteger(R.integer.galleryColumns))
        binding.recyclerView.apply {
            adapter = RecentAdapter(this@RecentActivity)
            layoutManager = gridLayoutManager
        }
    }

    private fun observeEvents() {

        viewModel.loadItems.observe(this, EventObserver {
            val adapter = binding.recyclerView.adapter as RecentAdapter
            adapter.addAll(it)
        })

        viewModel.refreshItems.observe(this, EventObserver {
            val adapter = binding.recyclerView.adapter as RecentAdapter
            adapter.refresh(it)
        })

        viewModel.errorEvent.observe(this, EventObserver {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

    }

}
