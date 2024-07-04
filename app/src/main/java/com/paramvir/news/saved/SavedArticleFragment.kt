package com.paramvir.news.saved

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.news.common.ui.BaseFragment
import com.paramvir.news.R
import com.paramvir.news.databinding.FragmentSavedBinding
import com.paramvir.news.common.utils.HEADLINE_URL
import com.paramvir.news.headlines.views.HeadlinesDetailsActivity
import com.paramvir.news.headlines.domain.NewsHeadlines
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedArticleFragment :
    BaseFragment<SavedArticleViewModel, FragmentSavedBinding>(
        R.layout.fragment_saved,
        SavedArticleViewModel::class.java
    ) {
    private lateinit var savedadapter: SavedArticleAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.savedHeadlinesLiveData.observe(this) {

            savedadapter = SavedArticleAdapter(requireContext(), it)
            binding.newsList.adapter = savedadapter
            handleNewsClick()

        }
        viewModel.getSavedArticles(requireContext())
    }

    private fun initializeList() {
        with(binding.newsList) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context,
                LinearLayoutManager(context).orientation
            )
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(divider)
        }
    }

    private fun handleNewsClick() {
        savedadapter.setOnClickListener(object :
            SavedArticleAdapter.OnClickListener {
            override fun onClick(url: String) {
                val bundle = Bundle()
                bundle.putString(HEADLINE_URL, url)
                val intent = Intent(requireContext(), HeadlinesDetailsActivity::class.java)
                intent.putExtras(bundle)
                requireActivity().startActivity(intent)
            }

            override fun onDeleteArticle(news: NewsHeadlines) {
                viewModel.deleteNews(news)
            }


        })
    }

}