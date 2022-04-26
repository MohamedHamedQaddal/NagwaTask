package com.app.nagwa.nagwatask.modules.media.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.children
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.nagwa.nagwatask.R
import com.app.nagwa.nagwatask.databinding.ItemMediaBinding
import com.app.nagwa.nagwatask.databinding.ItemMediaShimmerBinding
import com.app.nagwa.nagwatask.modules.media.presentation.model.BaseMediaPresentationModel
import com.app.nagwa.nagwatask.modules.media.presentation.model.MediaShimmerModel
import com.app.nagwa.nagwatask.modules.media.presentation.model.MediaUIModel
import com.facebook.shimmer.ShimmerFrameLayout
import javax.inject.Inject

class MediaAdapter @Inject constructor() :
    ListAdapter<BaseMediaPresentationModel, RecyclerView.ViewHolder>(MediaDiffUtil()) {

    var itemClickedCallBack: ((String) -> Unit)? = null


    inner class MediaViewHolder(private val binding: ItemMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindViews(item: MediaUIModel) {
            binding.apply {
                nameTv.text = item.name
                linkTv.text = item.url
                downloadIb.setOnClickListener {
                    itemClickedCallBack?.invoke(item.url ?: "")
                }
            }

        }

    }

    inner class ShimmerViewHolder(private val binding: ItemMediaShimmerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.children.filterIsInstance<ShimmerFrameLayout>().forEach {
                it.startShimmer()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {

        return if (getItem(position) is MediaShimmerModel)
            R.layout.item_media_shimmer
        else
            R.layout.item_media
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_media_shimmer -> {
                val binding = ItemMediaShimmerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ShimmerViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MediaViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MediaViewHolder)?.bindViews(getItem(position) as MediaUIModel)
    }

    private class MediaDiffUtil : DiffUtil.ItemCallback<BaseMediaPresentationModel>() {
        override fun areItemsTheSame(
            oldItem: BaseMediaPresentationModel,
            newItem: BaseMediaPresentationModel
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: BaseMediaPresentationModel,
            newItem: BaseMediaPresentationModel
        ): Boolean {
            return oldItem.equals(newItem)
        }

    }
}