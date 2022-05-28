package com.ganeshgundu.moviesapp.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
      //  var person = intent.extras.getParcelable("data")

    }
}