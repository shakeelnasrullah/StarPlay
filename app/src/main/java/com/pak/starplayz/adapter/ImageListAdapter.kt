package com.pak.starplayz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.newstore.favqs.base.BaseRecyclerViewAdapter
import com.pak.lib_module.models.Media
import com.pak.starplayz.base.loadImageFromUrl
import com.pak.starplayz.databinding.ImageRowBinding

class ImageListAdapter(private val itemSelected: (media: Media) -> Unit) :
    BaseRecyclerViewAdapter<ImageListAdapter.MediaViewHolder, Media>() {

    class MediaViewHolder(private val binding: ImageRowBinding, private val itemSelected: (media: Media) -> Unit) : ViewHolder(binding.root) {
        fun bind(item: Media) {
            binding.rowImageView.loadImageFromUrl(item)
            binding.rowImageView.setOnClickListener {
                itemSelected.invoke(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = ImageRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding, itemSelected)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}