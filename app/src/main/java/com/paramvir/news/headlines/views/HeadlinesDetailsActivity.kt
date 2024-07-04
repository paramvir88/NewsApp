package com.paramvir.news.headlines.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.paramvir.news.databinding.ActivityHeadlinesDetailsBinding
import com.paramvir.news.common.utils.HEADLINE_EXTRA
import com.paramvir.news.headlines.domain.NewsHeadlines
import com.paramvir.news.saved.FileHelper

/**
 * To show the clicked news article in Embedded WebView.
 */
class HeadlinesDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeadlinesDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadlinesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val headlines = intent.getSerializableExtra(HEADLINE_EXTRA) as NewsHeadlines
        headlines.url?.let {
            binding.showHeadlinesDetails.loadUrl(headlines.url)
        }

        binding.saveButton.setOnClickListener { // Implement your save logic here
            headlines.let { saveArticle(it) }
        }
    }

    private fun saveArticle(headlines: NewsHeadlines) {

        //com.paramvir.news.headlines.data.FileStorageHelper.saveUrlToFile(this, url)
        FileHelper.saveHeadline(this, headline = headlines)
        Toast.makeText(this, "This article is saved.", Toast.LENGTH_SHORT).show()
    }


}