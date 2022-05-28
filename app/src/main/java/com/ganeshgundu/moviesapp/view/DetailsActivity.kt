package com.ganeshgundu.moviesapp.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.ganeshgundu.moviesapp.BuildConfig
import com.ganeshgundu.moviesapp.R
import com.ganeshgundu.moviesapp.api.MovieResult
import com.ganeshgundu.moviesapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        var data = intent.getSerializableExtra("data") as MovieResult
        getSupportActionBar()?.setTitle(data.title);
        binding.releaseDate.text = data.releaseDate
        binding.rating.text = data.voteAverage
        binding.popularity.text = data.popularity
        binding.overview.text = data.overView
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(binding.root.context)
            .load(BuildConfig.BASE_URL_IMAGE+data.posterPath)
            .dontAnimate()
            .error(R.drawable.ic_broken_image)
            .placeholder(R.drawable.loading_animation)
            .apply(requestOptions)
            .into(binding.movieImageView)
        binding.movieImageView.adjustViewBounds = true

    }
}