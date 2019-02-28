package com.tigerspike.ui.recent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tigerspike.R
import com.tigerspike.databinding.ActivityMainBinding
import com.tigerspike.di.AppContext
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
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeEvents() {
        //TODO: Add events
    }

}
