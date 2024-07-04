package com.paramvir.news.sources.views

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.news.common.ui.BaseFragment
import com.paramvir.news.NewsActivity
import com.paramvir.news.R
import com.paramvir.news.common.network.Resource
import com.paramvir.news.databinding.FragmentSourcesBinding
import com.paramvir.news.sources.domain.NewsSources
import dagger.hilt.android.AndroidEntryPoint

/**
 * For showing list of sources loaded from the NewsApi.
 * User can select multiple sources which are persistent across user sessions.
 */
@AndroidEntryPoint
class SourcesFragment : BaseFragment<SourcesViewModel, FragmentSourcesBinding>(
    R.layout.fragment_sources,
    SourcesViewModel::class.java
) {
    private lateinit var sourceAdapter: SourceAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourceAdapter = SourceAdapter(emptyList(), (activity as NewsActivity).newsSources)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getSources()
        viewModel.sourceLiveData.observe(viewLifecycleOwner) {
            refreshSources(it)
        }
        setupList()
        handleSourceClick()
    }

    private fun refreshSources(it: Resource<List<NewsSources>>?) {
        when (it) {
            is Resource.ResourceSuccess -> {
                binding.newsProgressBar.visibility = View.GONE
                sourceAdapter.upDateList(it.data)
            }

            is Resource.ResourceError -> {
                displayError()
            }

            is Resource.ResourceLoading -> {
                binding.newsProgressBar.visibility = View.VISIBLE
            }

            else -> {}
        }
    }

    private fun displayError() {
        binding.newsProgressBar.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
        binding.errorTextView.text = getString(R.string.could_not_load_the_sources)
    }


    private fun handleSourceClick() {
        sourceAdapter.setOnClickListener(object :
            SourceAdapter.OnClickListener {
            override fun onClick(sourceId: String, isSelected: Boolean) {
                if (isSelected && !(activity as NewsActivity).newsSources.contains(sourceId)) {
                    (activity as NewsActivity).newsSources.add(sourceId)
                } else {
                    (activity as NewsActivity).newsSources.remove(sourceId)
                }
            }
        })
    }

    private fun setupList() {
        with(binding.sourcesList) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(divider)
            adapter = sourceAdapter
        }
    }
}