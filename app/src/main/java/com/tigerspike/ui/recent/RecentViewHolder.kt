package com.tigerspike.ui.recent

import androidx.recyclerview.widget.RecyclerView
import com.tigerspike.databinding.ItemRecentBinding
import com.tigerspike.model.Photo

class RecentViewHolder(private val binding: ItemRecentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Photo) {
        binding.item = item
        binding.executePendingBindings()
    }
}