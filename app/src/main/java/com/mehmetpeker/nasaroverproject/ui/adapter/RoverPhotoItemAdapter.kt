package com.mehmetpeker.nasaroverproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mehmetpeker.nasaroverproject.data.model.Photo
import com.mehmetpeker.nasaroverproject.databinding.ItemRoverPhotoBinding


class RoverPhotoItemAdapter(
    private val onItemClick: (photo: Photo) -> Unit
) : PagingDataAdapter<Photo,RoverPhotoItemAdapter.RoverPhotoViewHolder>(PhotosComparator) {

    inner class RoverPhotoViewHolder(val binding: ItemRoverPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverPhotoViewHolder {
        val binding =
            ItemRoverPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoverPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoverPhotoViewHolder, position: Int) {
        val item = getItem(position)
        val imageLink = item!!.img_src.replace("http", "https")
        Glide.with(holder.binding.imageviewRover.context)
            .load(imageLink)
            .into(holder.binding.imageviewRover)
        holder.binding.tvIndex.text = position.toString()
        holder.binding.root.setOnClickListener { onItemClick(item) }
    }

    object PhotosComparator : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
}
