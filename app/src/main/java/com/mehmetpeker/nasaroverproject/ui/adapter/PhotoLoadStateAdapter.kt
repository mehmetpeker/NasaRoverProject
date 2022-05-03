package com.mehmetpeker.nasaroverproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehmetpeker.nasaroverproject.databinding.ItemLoadingStateBinding

class PhotoLoadStateAdapter(private val retry : () -> Unit) :
    LoadStateAdapter<PhotoLoadStateAdapter.PhotoLoadStateViewHolder>() {


    inner class PhotoLoadStateViewHolder(
        private val binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener { retry() }
        }
        fun bind(loadState: LoadState) = with(binding) {
            if (loadState is LoadState.Error) {
                errorMsg.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }
    override fun onBindViewHolder(holder: PhotoLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PhotoLoadStateViewHolder(
        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}