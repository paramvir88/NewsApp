package com.paramvir.paramnews.headlines.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.paramvir.paramnews.databinding.HeadlinesListItemBinding
import com.paramvir.paramnews.R
import com.paramvir.paramnews.headlines.domain.NewsHeadlines

class HeadlinesAdapter(
    private val context: Context,
    private var headlines: List<NewsHeadlines>

) : Adapter<HeadlinesAdapter.ViewHolder>() {

    private var newsClickListener: NewsClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = HeadlinesListItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.headlines_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        headlines[position].run {
            with(holder.binding) {
                headlineLayout.setOnClickListener {
                    newsClickListener?.onClick(url ?: "")
                }
                articleTitle.text = title
                articleDescription.text = description
                articleAuthor.text = "Author: $author"
                readLater.setOnClickListener {
                    newsClickListener?.saveLater(this@run)
                }
                //Glide
            }
        }

    }

    override fun getItemCount(): Int {
        return headlines.size
    }


    interface NewsClickListener {
        fun onClick(url: String)
        fun saveLater(news: NewsHeadlines)
    }


    fun updateHeadlines(newHeadlines: List<NewsHeadlines>) {
        headlines = emptyList()
        headlines = newHeadlines
        notifyDataSetChanged()

    }

}