package com.amd.movieapptask.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amd.movieapptask.BuildConfig
import com.amd.movieapptask.data.model.ResultsItem
import com.amd.movieapptask.databinding.ItemListPopularBinding
import com.amd.movieapptask.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PopularAdapter(private val onClickList: ((ResultsItem) -> Unit)
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: ItemListPopularBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ResultsItem>(){
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder = ViewHolder(
        ItemListPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PopularAdapter.ViewHolder, position: Int) {
        val item = differ.currentList[holder.adapterPosition]
        with(holder.binding){
            txtTitle.text = item.title
            txtDescription.text = item.overview
            txtReleaseDate.text = item.releaseDate?.let { Utils.dateFormat(it, "yyyy-mm-dd", "dd MMMM yyyy") }
            Glide.with(root.context)
                .load( BuildConfig.imageUrl + item?.posterPath)
                .into(imgPoster)
            root.setOnClickListener {
                onClickList.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}