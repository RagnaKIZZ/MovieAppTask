package com.amd.movieapptask.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amd.movieapptask.R
import com.amd.movieapptask.data.database.model.MovieEntity
import com.amd.movieapptask.data.mapper.MovieMapper
import com.amd.movieapptask.databinding.ActivityFavoriteBinding
import com.amd.movieapptask.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private val favoriteAdapter by lazy {
        FavoriteAdapter(
            {
                onClickDetail(it)
            },
            {
                onClickFavorite(it)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupUI()
        getFavorite()
    }

    private fun setupToolbar() {
        with(binding.incToolbar) {
            btnBack.setOnClickListener {
                finish()
            }
            txtTitle.text = getString(R.string.favorite)
        }
    }

    private fun setupUI() {
        with(binding) {
            rvFavorite.also {
                it.layoutManager = LinearLayoutManager(this@FavoriteActivity)
                it.adapter = favoriteAdapter
            }
        }
    }

    private fun getFavorite() {
        favoriteViewModel.getListFavorite().observe(this, {
            favoriteAdapter.differ.submitList(it)
        })
    }

    private fun onClickFavorite(movieEntity: MovieEntity) {
        favoriteViewModel.deleteFavorite(movieEntity)
    }

    private fun onClickDetail(movieEntity: MovieEntity) {
        val movieItem = MovieMapper.mapEntityToResponse(movieEntity)
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("movieDetail", movieItem)
        })
    }

}