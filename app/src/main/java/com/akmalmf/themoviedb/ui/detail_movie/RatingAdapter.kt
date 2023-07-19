package com.akmalmf.themoviedb.ui.detail_movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.akmalmf.themoviedb.BuildConfig
import com.akmalmf.themoviedb.R
import com.akmalmf.themoviedb.abstraction.base.BaseRecyclerViewAdapter
import com.akmalmf.themoviedb.databinding.ItemMoviesBinding
import com.akmalmf.themoviedb.databinding.ItemRatingBinding
import com.akmalmf.themoviedb.model.MovieModel
import com.akmalmf.themoviedb.model.RatingModel

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/19 06:51
 * akmalmf007@gmail.com
 */
class RatingAdapter: BaseRecyclerViewAdapter<RatingAdapter.VHolder, RatingModel>(){

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRatingBinding.bind(itemView)
        fun onBind(model: RatingModel) {
            binding.textName. text = model.author
            binding.ratingBar.rating = (model.authorDetails.rating ?: 0).toFloat()
            binding.textRating.text = "${model.authorDetails.rating ?: 0}/10"
            binding.textContent.text = model.content
            binding.imageProfile.load(BuildConfig.BASE_IMAGE+"w300"+model.authorDetails.avatarPath) {
                placeholder(R.drawable.ic_image_placeholder)
                error(R.drawable.ic_image_error)
            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(model)
            }
        }
    }

    override fun onBindViewHolder(holder: VHolder, item: RatingModel, position: Int) {
        holder.onBind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rating, parent, false))
    }
}