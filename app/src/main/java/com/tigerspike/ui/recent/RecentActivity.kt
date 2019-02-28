package com.tigerspike.ui.recent

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecentActivity)
//                .grid(resources.getInteger(R.integer.galleryColumns))

            addDividers(this)
        }
    }

    private fun addDividers(recyclerView: RecyclerView) {
        val verticalDividerItemDecoration = DividerItemDecoration(
            this, DividerItemDecoration.VERTICAL
        ).apply {
            setDrawable(resources.getDrawable(R.drawable.vertical_divider))
        }
        recyclerView.addItemDecoration(verticalDividerItemDecoration)

        val horizontalDividerItemDecoration = DividerItemDecoration(
            this, DividerItemDecoration.HORIZONTAL
        ).apply {
            setDrawable(resources.getDrawable(R.drawable.horizontal_divider))
        }
        recyclerView.addItemDecoration(horizontalDividerItemDecoration)
    }

    private fun observeEvents() {

        viewModel.errorEvent.observe(this, EventObserver {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.dataEvent.observe(this, EventObserver {
            val adapter = binding.recyclerView.adapter
        })

    }

}
