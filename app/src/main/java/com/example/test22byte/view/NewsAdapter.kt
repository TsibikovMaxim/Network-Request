package com.example.test22byte.view

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test22byte.R
import com.example.test22byte.data.Article
import com.example.test22byte.databinding.NewsItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NewsAdapter : ListAdapter<Article, NewsAdapter.NewsViewHolder>(NewsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(article: Article) {
            binding.apply {
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .error(R.drawable.ic_close)
                    .into(newsImageView)

                writerTextView.text = article.source.name
                titleTextView.text = article.title
                descriptionTextView.text = article.description

                val string = article.publishedAt
                val formatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
                val date: LocalDate = LocalDate.parse(string, formatter)
                dateTextView.text = date.toString()
            }
        }
    }

    class NewsComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }
}