package com.tigerspike.ui.recent

import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignSelf
import com.google.android.flexbox.FlexboxLayoutManager
import com.tigerspike.databinding.ItemRecentBinding
import com.tigerspike.model.Photo


class RecentViewHolder(private val binding: ItemRecentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo, listener: RecentAdapter.Listener?) {
        binding.item = photo
        binding.executePendingBindings()
        binding.image.apply {
            setOnClickListener {
                listener?.onItemClick(photo, this)
            }
            if (layoutParams is FlexboxLayoutManager.LayoutParams) {
                val flexboxLp = layoutParams as FlexboxLayoutManager.LayoutParams
                flexboxLp.flexGrow = 1.0f
                flexboxLp.alignSelf = AlignSelf.STRETCH
            }
        }
    }
}