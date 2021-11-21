package com.randomdroids.nytimes.ui.detail

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.randomdroids.nytimes.R
import com.randomdroids.nytimes.databinding.TabletLayoutBinding
import com.randomdroids.nytimes.domain.Article
import com.randomdroids.nytimes.ui.inflate
import com.randomdroids.nytimes.ui.loadUrl
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class DetailAdapter(articlesList: List<Article> = emptyList(), private val listener: (Article) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private var getArticlesList = articlesList

    var articlesList by Delegates.observable(articlesList) { _, _, newArticles ->
        getArticlesList = newArticles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.tablet_layout, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = getArticlesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articlesList[position]
        holder.bind(article)
        holder.itemView.setOnClickListener { listener(article) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TabletLayoutBinding.bind(view)
        fun bind(article: Article) = with(binding) {
            if (!article.media.isNullOrEmpty()) {
                articleImage.loadUrl(article.media!![0].imageMetadata!![0].url.toString())
            }
            articleTitle.text = article.title
            articleAuthor.text = article.author
            articleSection.text = article.section
            articlePublishedDate.text = article.publishedDate
        }
    }
}