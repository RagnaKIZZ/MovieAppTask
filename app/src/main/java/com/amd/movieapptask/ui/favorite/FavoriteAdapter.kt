package com.amd.movieapptask.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amd.movieapptask.BuildConfig
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.databinding.ItemListFavoriteBinding
import com.bumptech.glide.Glide

class FavoriteAdapter(
    private val onClickList: ((MovieEntity) -> Unit),
    private val onClickFavorite: ((MovieEntity) -> Unit)
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemListFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<MovieEntity>(){
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder = ViewHolder(
        ItemListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[holder.adapterPosition]
        with(holder.binding){
            txtTitle.text = item.title
            txtDescription.text = item.overview
            txtReleaseDate.text = item.release_date
            Glide.with(root.context)
                .load( BuildConfig.imageUrl + item?.backdrop_path)
                .into(imgPoster)
            root.setOnClickListener {
                onClickList.invoke(item)
            }
            btnFavorite.setOnClickListener {
                onClickFavorite.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}