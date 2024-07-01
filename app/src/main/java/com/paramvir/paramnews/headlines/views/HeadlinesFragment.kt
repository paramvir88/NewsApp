package com.paramvir.paramnews.headlines.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paramvir.paramnews.R
import com.paramvir.paramnews.Resource
import com.paramvir.paramnews.headlines.domain.NewsHeadlines
import dagger.hilt.android.AndroidEntryPoint

/**
 * For showing the headlines.
 */

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {
    private val viewModel: HeadlinesViewModel by viewModels()
    private lateinit var headlinesAdapter: HeadlinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment_layout, container, false)
        val recyclerView = view.rootView.findViewById<RecyclerView>(R.id.news_list)

        initializeList(recyclerView)
        return view
    }

    private fun initializeList(recyclerView: RecyclerView) {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
            headlinesAdapter = HeadlinesAdapter(requireContext(), emptyList())
            adapter = headlinesAdapter
        }
    }


    override fun onStart() {

        super.onStart()
        viewModel.getHeadlines("")
        viewModel.headlinesLiveData.observe(this) {
            when (it) {
                is Resource.ResourceSuccess -> {
                    updateSuccessUi(it.data)
                }

                is Resource.ResourceError -> {
                    //updateErrorUi()
                }

                is Resource.ResourceLoading -> {
                    //news_progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun updateSuccessUi(newsHeadlines: List<NewsHeadlines>) {
        //news_progressBar.visibility = View.GONE
        if (newsHeadlines.isEmpty()) {
            //error_textView.visibility = View.VISIBLE
            //error_textView.text = "Oh oh! No news to read for now"
        } else {
            headlinesAdapter.updateHeadlines(newsHeadlines)
        }
    }
}