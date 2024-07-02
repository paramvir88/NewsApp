package com.paramvir.paramnews.headlines.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.paramnews.NewsActivity
import com.paramvir.paramnews.Resource
import com.paramvir.paramnews.databinding.FragmentHeadlinesBinding
import com.paramvir.paramnews.headlines.HEADLINE_URL
import com.paramvir.paramnews.headlines.domain.NewsHeadlines
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
    ): View? {
        binding = FragmentHeadlinesBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        prepareNewsList()
        handleHeadlineClick()
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
                "There are no news for the selected sources. Try changing the sources."
        } else {
            headlinesAdapter.updateHeadlines(newsHeadlines)
        }
    }

    private fun handleHeadlineClick() {
        headlinesAdapter.setOnClickListener(object :
            HeadlinesAdapter.NewsClickListener {
            override fun onClick(url: String) {
                val bundle = Bundle()
                bundle.putString(HEADLINE_URL, url)
                val intent = Intent(requireContext(), HeadlinesDetailsActivity::class.java)
                intent.putExtras(bundle)
                requireActivity().startActivity(intent)
            }

        })
    }
}