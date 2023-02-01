package com.example.setwallpaperapp.presentation.ui.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.setwallpaperapp.databinding.ItemImageBinding
import com.example.setwallpaperapp.presentation.model.HitUI
import com.example.setwallpaperapp.presentation.utils.loadImage

class MainAdapter(
private val onItemClick: (url: String) -> Unit
) : ListAdapter<HitUI, MainAdapter.MainViewHolder>(HitDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model)
        holder.itemView.setOnClickListener {
            onItemClick(model.largeImageURL)
        }
    }

    class MainViewHolder(private val binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(model: HitUI?) {
            model?.largeImageURL?.let { binding.itemImg.loadImage(it) }
        }
    }
}

class HitDiffCallBack : DiffUtil.ItemCallback<HitUI>() {
    override fun areItemsTheSame(oldItem: HitUI, newItem: HitUI): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: HitUI, newItem: HitUI): Boolean =
        oldItem == newItem
}
