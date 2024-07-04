package com.paramvir.news.saved.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paramvir.news.R
import com.paramvir.news.databinding.SavedNewsListItemBinding
import com.paramvir.news.headlines.domain.NewsHeadlines

class SavedArticleAdapter(
    private val context: Context,
    private var listOfHeadlines: List<NewsHeadlines>,
) : RecyclerView.Adapter<SavedArticleAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = SavedNewsListItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_news_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfHeadlines.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            headlineLayout.setOnClickListener {
                onClickListener?.onClick(
                    listOfHeadlines[position]
                )
            }
            articleTitle.text = listOfHeadlines[position].title
            articleDescription.text = listOfHeadlines[position].description
            articleAuthor.text = listOfHeadlines[position].author
            deleteArticle.setOnClickListener { onClickListener?.onDeleteArticle(listOfHeadlines[position]) }
            Glide.with(context)
                .load(listOfHeadlines[position].pic)
                .placeholder(R.drawable.baseline_newspaper_24)
                .into(articleImage)

        }
    }

    interface OnClickListener {
        fun onClick(newsHeadlines: NewsHeadlines)
        fun onDeleteArticle(newsHeadlines: NewsHeadlines)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}
