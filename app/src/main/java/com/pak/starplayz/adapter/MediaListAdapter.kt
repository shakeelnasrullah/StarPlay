package com.pak.starplayz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newstore.favqs.base.BaseRecyclerViewAdapter
import com.pak.lib_module.models.Media
import com.pak.lib_module.models.MediaTypeList
import com.pak.starplayz.databinding.MediaRowBinding

class MediaListAdapter(private val itemSelected: (media: Media) -> Unit) :
    BaseRecyclerViewAdapter<MediaListAdapter.MediaViewHolder, MediaTypeList>() {

    class MediaViewHolder(
        private val binding: MediaRowBinding,
        private val itemSelected: (media: Media) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MediaTypeList) {
            binding.apply {
                val adapter = ImageListAdapter(itemSelected)
                mediaTitle.text = item.mediaType
                imageRecyclerView.adapter = adapter
                imageRecyclerView.set3DItem(true)
                imageRecyclerView.setInfinite(false)
                imageRecyclerView.setAlpha(false)
                imageRecyclerView.setFlat(true)
                imageRecyclerView.setIsScrollingEnabled(true)
                adapter.replaceList(item.mediaList)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = MediaRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding, itemSelected)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}