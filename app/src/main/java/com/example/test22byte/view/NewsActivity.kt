package com.example.test22byte.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test22byte.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val newsAdapter = NewsAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(this@NewsActivity)
            }

            viewModel.restaurants.observe(this@NewsActivity) { Object ->
                newsAdapter.submitList(Object.articles)
            }
        }

    }
}