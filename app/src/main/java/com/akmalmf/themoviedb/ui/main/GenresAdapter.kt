package com.akmalmf.themoviedb.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.akmalmf.themoviedb.R
import com.akmalmf.themoviedb.abstraction.base.BaseRecyclerViewAdapter
import com.akmalmf.themoviedb.databinding.ItemGenresBinding
import com.akmalmf.themoviedb.model.GenreModel

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 23:23
 * akmalmf007@gmail.com
 */
class GenresAdapter : BaseRecyclerViewAdapter<GenresAdapter.VHolder, GenreModel>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    inner class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemGenresBinding.bind(itemView)

        fun onBind(model: GenreModel, position: Int) {
            val isSelected = position == selectedPosition

            if (isSelected) {
                val colorStateList = ContextCompat.getColorStateList(itemView.context, R.color.green_500)
                binding.root.backgroundTintList = colorStateList
            } else {
                val colorStateList = ContextCompat.getColorStateList(itemView.context, R.color.purple_300)
                binding.root.backgroundTintList = colorStateList
            }

            binding.textGenre.text = model.name
            binding.root.setOnClickListener {
                val previousPosition = selectedPosition

                // Check if the clicked item is already selected
                if (isSelected) {
                    selectedPosition = RecyclerView.NO_POSITION
                } else {
                    selectedPosition = adapterPosition
                }

                // Notify the adapter of item changes to update the UI
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                onItemClick?.invoke(model)
            }
        }
    }

    override fun onBindViewHolder(holder: VHolder, item: GenreModel, position: Int) {
        holder.onBind(item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        )
    }
}
