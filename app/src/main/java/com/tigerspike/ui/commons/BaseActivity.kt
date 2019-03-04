package com.tigerspike.ui.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tigerspike.BR

abstract class BaseActivity<DT : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    abstract val layoutId: Int
    protected lateinit var binding: DT
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = providesBinding()
        viewModel = providesViewModel()
        binding.setVariable(BR.viewModel, viewModel)
    }

    private fun providesBinding(): DT {
        return DataBindingUtil.setContentView(this, layoutId)
    }

    abstract fun providesViewModel(): VM

}