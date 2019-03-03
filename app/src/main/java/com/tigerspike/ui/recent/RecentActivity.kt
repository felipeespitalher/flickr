package com.tigerspike.ui.recent

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.flexbox.*
import com.tigerspike.R
import com.tigerspike.databinding.ActivityMainBinding
import com.tigerspike.di.AppContext
import com.tigerspike.ui.commons.EventObserver
import javax.inject.Inject

class RecentActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, factory)[RecentViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.startUp()
    }

    private fun setupRecyclerView() {
        val flexboxLayoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.SPACE_AROUND
            alignItems = AlignItems.BASELINE
        }
        binding.recyclerView.apply {
            adapter = RecentAdapter(this@RecentActivity)
            layoutManager = flexboxLayoutManager
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
