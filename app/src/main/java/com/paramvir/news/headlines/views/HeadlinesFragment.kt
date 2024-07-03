package com.paramvir.news.headlines.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.news.NewsActivity
import com.paramvir.news.R
import com.paramvir.news.Resource
import com.paramvir.news.databinding.FragmentHeadlinesBinding
import com.paramvir.news.headlines.HEADLINE_EXTRA
import com.paramvir.news.headlines.domain.NewsHeadlines
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main landing fragment where the headlines for the selected sources are shown.
 */

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {
    private val viewModel: HeadlinesViewModel by viewModels()
    private lateinit var binding: FragmentHeadlinesBinding
    private lateinit var headlinesAdapter: HeadlinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        prepareNewsList()
        headlinesAdapter.setOnClickListener(object :
            HeadlinesAdapter.NewsClickListener {
            override fun onClick(headlines: NewsHeadlines) {
                onHeadlineClick(headlines)
            }
        })
        return view
    }

    override fun onStart() {

        super.onStart()
        viewModel.getHeadlines((activity as NewsActivity).sourceArray)
        viewModel.headlinesLiveData.observe(this) {
            when (it) {
                is Resource.ResourceSuccess -> {
                    refreshNews(it.data)
                }

                is Resource.ResourceError -> {
                    //updateErrorUi()
                }

                is Resource.ResourceLoading -> {
                    binding.newsProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun prepareNewsList() {
        with(binding.newsList) {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
            headlinesAdapter = HeadlinesAdapter(requireContext(), emptyList())
            adapter = headlinesAdapter
        }
    }

    private fun refreshNews(newsHeadlines: List<NewsHeadlines>) {
        binding.newsProgressBar.visibility = View.GONE
        if (newsHeadlines.isEmpty()) {
            binding.errorTextView.visibility = View.VISIBLE
            binding.errorTextView.text =
                getString(R.string.there_are_no_news)
        } else {
            headlinesAdapter.updateHeadlines(newsHeadlines)
        }
    }


    private fun onHeadlineClick(headline: NewsHeadlines) {

        val intent = Intent(requireContext(), HeadlinesDetailsActivity::class.java).apply {
            putExtra(HEADLINE_EXTRA, headline)
        }
        requireActivity().startActivity(intent)
    }
}