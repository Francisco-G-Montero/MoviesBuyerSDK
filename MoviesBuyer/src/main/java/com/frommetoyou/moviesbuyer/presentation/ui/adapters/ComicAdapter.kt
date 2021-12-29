package com.frommetoyou.moviesbuyer.presentation.ui.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.frommetoyou.moviesbuyer.data.model.comics.Comic
import com.frommetoyou.moviesbuyer.databinding.ItemMovieBinding
import com.frommetoyou.moviesbuyer.presentation.extensions.getImgPath

class ComicAdapter(private var listener: OnClickListener) :
    ListAdapter<Comic, ComicAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(currentList[holder.adapterPosition], listener)
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(comic: Comic, listener: OnClickListener) {
            with(binding) {
                Glide.with(root)
                    .load(comic.thumbnail.getImgPath())
                    .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(15)))
                    .into(imgPhoto)
                tvTitle.text = comic.title
                tvDescription.text = comic.description
                btnBuy.setOnClickListener { listener.onItemClick(comic) }
            }
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Comic>() {
        override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
            return oldItem == newItem
        }
    }
}