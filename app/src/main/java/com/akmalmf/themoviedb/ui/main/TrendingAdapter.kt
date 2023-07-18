package com.akmalmf.themoviedb.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.akmalmf.themoviedb.BuildConfig
import com.akmalmf.themoviedb.R
import com.akmalmf.themoviedb.abstraction.base.BaseRecyclerViewAdapter
import com.akmalmf.themoviedb.databinding.ItemTrendingBinding
import com.akmalmf.themoviedb.model.MovieModel

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 21:59
 * akmalmf007@gmail.com
 */
class TrendingAdapter: BaseRecyclerViewAdapter<TrendingAdapter.VHolder, MovieModel>(){

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTrendingBinding.bind(itemView)
        fun onBind(model: MovieModel) {
            binding.textTitle.text = model.title
            binding.imageBackdrop.load(BuildConfig.BASE_IMAGE+"w300"+model.backdropPath) {
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_image_error)
            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(model)
            }
        }
    }

    override fun onBindViewHolder(holder: VHolder, item: MovieModel, position: Int) {
        holder.onBind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false))
    }
}