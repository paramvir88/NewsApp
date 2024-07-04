package com.paramvir.news.headlines.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.news.common.ui.BaseFragment
import com.paramvir.news.NewsActivity
import com.paramvir.news.R
import com.paramvir.news.common.network.Resource
import com.paramvir.news.databinding.FragmentHeadlinesBinding
import com.paramvir.news.common.utils.HEADLINE_EXTRA
import com.paramvir.news.headlines.domain.NewsHeadlines
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main landing fragment where the [NewsHeadlines] items for the selected sources are shown.
 */

@AndroidEntryPoint
class HeadlinesFragment : BaseFragment<HeadlinesViewModel, FragmentHeadlinesBinding>(
    R.layout.fragment_headlines,
    HeadlinesViewModel::class.java
) {

    private lateinit var headlinesAdapter: HeadlinesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        fetchHeadlines()
    }


    private fun setupViews() {
        with(binding.newsRecyclerView) {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
            headlinesAdapter = HeadlinesAdapter(requireContext(), emptyList())
            adapter = headlinesAdapter
        }

        headlinesAdapter.setOnClickListener(object :
            HeadlinesAdapter.NewsClickListener {
            override fun onClick(headlines: NewsHeadlines) {
                onHeadlineClick(headlines)
            }
        })
    }

    private fun fetchHeadlines() {
        viewModel.headlinesLiveData.observe(this) {
            when (it) {
                is Resource.ResourceSuccess -> {
                    refreshNews(it.data)
                }

                is Resource.ResourceError -> {
                    showError()
                }

                is Resource.ResourceLoading -> {
                    binding.newsProgressBar.visibility = View.VISIBLE
                }
            }
        }
        viewModel.getHeadlines((activity as NewsActivity).newsSources)
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

    private fun showError() {
        binding.newsProgressBar.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
    }
}