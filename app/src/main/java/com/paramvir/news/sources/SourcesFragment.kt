package com.paramvir.news.sources

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.news.BaseFragment
import com.paramvir.news.NewsActivity
import com.paramvir.news.R
import com.paramvir.news.Resource
import com.paramvir.news.databinding.FragmentSourcesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourcesFragment : BaseFragment<SourcesViewModel, FragmentSourcesBinding>(
    R.layout.fragment_sources,
    SourcesViewModel::class.java
) {
    private lateinit var sourceAdapter: SourceAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourceAdapter = SourceAdapter(emptyList(), (activity as NewsActivity).sourceArray)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getSources()
        viewModel.sourceLiveData.observe(viewLifecycleOwner) {
            handleUI(it)
        }
        initializeList()
        handleSourceClick()
    }

    private fun handleUI(it: Resource<List<NewsSources>>?) {
        when (it) {
            is Resource.ResourceSuccess -> {
                binding.newsProgressBar.visibility = View.GONE
                sourceAdapter.upDateList(it.data)
            }

            is Resource.ResourceError -> {
                updateErrorUi()
            }

            is Resource.ResourceLoading -> {
                binding.newsProgressBar.visibility = View.VISIBLE
            }

            else -> {}
        }
    }

    private fun updateErrorUi() {
        binding.newsProgressBar.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
        binding.errorTextView.text = "Could not load the sources."
    }


    private fun handleSourceClick() {
        sourceAdapter.setOnClickListener(object :
            SourceAdapter.OnClickListener {
            override fun onClick(sourceId: String, isSelected: Boolean) {
                if (isSelected && !(activity as NewsActivity).sourceArray.contains(sourceId)) {
                    (activity as NewsActivity).sourceArray.add(sourceId)
                } else {
                    (activity as NewsActivity).sourceArray.remove(sourceId)
                }
            }
        })
    }

    private fun initializeList() {
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