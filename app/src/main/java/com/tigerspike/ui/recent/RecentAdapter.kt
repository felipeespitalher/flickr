package com.tigerspike.ui.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.tigerspike.databinding.ItemRecentBinding
import com.tigerspike.model.Photo
import javax.inject.Inject

class RecentAdapter @Inject constructor() : RecyclerView.Adapter<RecentViewHolder>() {

    private val items = ArrayList<Photo>()
    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecentBinding.inflate(inflater, parent, false)
        return RecentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bind(items[position], listener)
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

    interface Listener {
        fun onItemClick(photo: Photo, imageView: ImageView)
    }

}
