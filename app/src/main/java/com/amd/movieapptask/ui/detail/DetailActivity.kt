package com.amd.movieapptask.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.amd.movieapptask.BuildConfig
import com.amd.movieapptask.R
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.mapper.MovieMapper
import com.amd.movieapptask.data.model.ResultsItem
import com.amd.movieapptask.databinding.ActivityDetailBinding
import com.amd.movieapptask.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val detailViewModel: DetailViewModel by viewModels()

    private val movieItem: ResultsItem? by lazy {
        intent.getParcelableExtra("movieDetail")
    }

    private val movieEntity: MovieEntity by lazy {
        MovieMapper.mapResponseToEntity(movieItem!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupUI()
        checkFavorite()
        detailViewModel.getItemFavorite(movieEntity)
    }

    private fun setupToolbar() {
        with(binding) {
            imgBack.setOnClickListener {
                finish()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        movieItem?.let { movie ->
            with(binding) {
                getPhoto(imgBackground, movieItem?.backdropPath)
                getPhoto(imgPoster, movieItem?.posterPath)
                txtTitle.text = movie.title
                txtDescription.text = movie.overview
                txtRating.text = movie.voteAverage.toString()
                txtPopularity.text = movie.popularity.toString() + " viewers"
                txtRelease.text =
                    movie.releaseDate?.let { Utils.dateFormat(it, "yyyy-mm-dd", "dd MMMM yyyy") }
                btnFavorite.setOnClickListener {
                    detailViewModel.clickFavorite(movieEntity)
                }
            }
        }
    }

    private fun checkFavorite() {
        detailViewModel.stateFavorie.observe(this, {
            if (it) {
                setDrawableIsFavorite()
            } else {
                setDrawableNotFavorite()
            }
        })
    }

    private fun setDrawableIsFavorite() {
        binding.btnFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_favorite_24
            )
        )
    }

    private fun setDrawableNotFavorite() {
        binding.btnFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_favorite_border_24
            )
        )
    }

    private fun getPhoto(img: ImageView, url: String?) {
        Glide.with(binding.root.context)
            .load(BuildConfig.imageUrl + url)
            .into(img)
    }
}