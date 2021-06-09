package com.amd.movieapptask.ui.popular

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amd.movieapptask.R
import com.amd.movieapptask.data.model.ResultsItem
import com.amd.movieapptask.databinding.ActivityPopularBinding
import com.amd.movieapptask.ui.detail.DetailActivity
import com.amd.movieapptask.ui.favorite.FavoriteActivity
import com.amd.movieapptask.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularActivity : AppCompatActivity() {

    private val popularViewModel: PopularViewModel by viewModels()

    private val binding by lazy {
        ActivityPopularBinding.inflate(layoutInflater)
    }

    private val popularAdapter by lazy {
        PopularAdapter {
            showDetails(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
        setupUI()
        getPopularMovie()
    }

    private fun setupUI() {
        with(binding) {
            rvPopularMovie.also {
                it.layoutManager = LinearLayoutManager(this@PopularActivity)
                it.adapter = popularAdapter
                it.setHasFixedSize(true)
            }
            incError.btnRetry.setOnClickListener {
                popularViewModel.getPopularMovie()
            }
        }
    }

    private fun setupToolbar() {
        with(binding.incToolbar) {
            txtTitle.text = getString(R.string.app_name)
            btnFavorite.setOnClickListener {
                startActivity(Intent(this@PopularActivity, FavoriteActivity::class.java))
            }
        }
    }

    private fun getPopularMovie() {
        popularViewModel.popularMovie.observe(this, { response ->
                when (response) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.incError.root.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.incError.root.visibility = View.GONE
                        popularAdapter.differ.submitList(response.value.results)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.incError.root.visibility = View.GONE
                    }
                }
        })
    }

    private fun showDetails(item: ResultsItem) {
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra("movieDetail", item)
        })
    }
}