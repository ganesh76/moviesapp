/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ganeshgundu.moviesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.ganeshgundu.moviesapp.BuildConfig
import com.ganeshgundu.moviesapp.R
import com.ganeshgundu.moviesapp.api.MovieResult
import com.ganeshgundu.moviesapp.databinding.MoviesListViewItemBinding

class MoviesAdapter :
    ListAdapter<MovieResult, MoviesAdapter.MoviesViewHolder>(DiffCallback) {

    class MoviesViewHolder(
        private var binding: MoviesListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieResult) {
            binding.movieTitleTextView.text = data.title
            binding.movieOverview.text = data.overView
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

    companion object DiffCallback : DiffUtil.ItemCallback<MovieResult>() {
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
        }
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        return MoviesViewHolder(
            MoviesListViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }



    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}
