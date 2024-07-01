package com.paramvir.paramnews.headlines.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paramvir.paramnews.databinding.ActivityHeadlinesDetailsBinding
import com.paramvir.paramnews.headlines.HEADLINE_URL


class HeadlinesDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeadlinesDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadlinesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.extras?.getString(HEADLINE_URL)
        url?.let {
            binding.showHeadlinesDetails.loadUrl(url)
        }
    }
}