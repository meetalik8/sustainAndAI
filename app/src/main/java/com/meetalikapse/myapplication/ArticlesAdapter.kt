package com.meetalikapse.myapplication

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meetalikapse.myapplication.api.Article

class ArticlesAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.titleTextView.text = article.webTitle
        holder.urlTextView.text = article.webUrl
        article.fields?.thumbnail?.let {
            Glide.with(holder.itemView.context).load(it).into(holder.thumbnailImageView)
        } ?: run {
            // Handle the case where the thumbnail is null, maybe show a placeholder image
            Glide.with(holder.itemView.context).load(R.drawable.earth).into(holder.thumbnailImageView)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.webUrl))
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = articles.size

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tvArticleTitle)
        val urlTextView: TextView = itemView.findViewById(R.id.tvArticleUrl)
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.ivThumbnail)
    }
}
