package com.ganeshgundu.moviesapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ganeshgundu.moviesapp.R
import com.ganeshgundu.moviesapp.databinding.ActivityMainBinding
import com.ganeshgundu.nasaapod.viewmodel.MoviesViewModel
import androidx.lifecycle.ViewModelProvider
import com.ganeshgundu.moviesapp.app.MoviesApp
import com.ganeshgundu.nasaapod.repository.MoviesRepository


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MoviesViewModel
    private lateinit var context: Context
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,  MoviesViewModel.MoviesViewModelFactory((application as MoviesApp).repository)).get(MoviesViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        val adapter =  MoviesAdapter({
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        })
        binding.moviesRecyclerView.adapter = adapter
        viewModel.getMovies()
        viewModel.responseData.observe(this,{

            when (it.responseStatus) {
                MoviesRepository.ResponseStatus.SUCCESS -> {
                    val sz = it.response?.results?.size
                    if (sz != null) {
                        if (sz > 0){
                            adapter.submitList(it.response?.results)
                        } else {
                            binding.statusTextView.text = context.getString(R.string.api_error_msg)
                        }
                    }
                }
                MoviesRepository.ResponseStatus.API_ERROR -> {
                    binding.statusTextView.text = context.getString(R.string.api_error_msg)
                }
                MoviesRepository.ResponseStatus.API_ERROR -> {
                    binding.statusTextView.text = context.getString(R.string.api_error_msg)
                }
            }

        })
        viewModel.showLoading.observe(this,{
            if(it){
                binding.statusProgressBar.visibility = View.VISIBLE
            } else {
                binding.statusProgressBar.visibility = View.GONE
            }
        })
    }
}
