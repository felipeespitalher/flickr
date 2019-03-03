package com.tigerspike.ui.recent

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tigerspike.R
import com.tigerspike.databinding.ItemRecentBinding
import com.tigerspike.model.Photo

class RecentAdapter(
        private val context: Context
) : RecyclerView.Adapter<RecentViewHolder>() {

    private val items = ArrayList<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemRecentBinding.inflate(inflater, parent, false)
        return RecentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun addAll(items: List<Photo>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun refresh(items: List<Photo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}
