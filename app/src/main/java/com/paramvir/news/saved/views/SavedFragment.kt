package com.paramvir.news.saved.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.news.R
import com.paramvir.news.common.ui.BaseFragment
import com.paramvir.news.common.utils.HEADLINE_EXTRA
import com.paramvir.news.common.utils.truncateWithDots
import com.paramvir.news.databinding.FragmentSavedBinding
import com.paramvir.news.headlines.domain.NewsHeadlines
import com.paramvir.news.headlines.views.HeadlinesDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for showing list of Saved [NewsHeadlines].
 */
@AndroidEntryPoint
class SavedFragment :
    BaseFragment<SavedViewModel, FragmentSavedBinding>(
        R.layout.fragment_saved,
        SavedViewModel::class.java
    ) {
    private lateinit var savedAdapter: SavedArticleAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.savedHeadlinesLiveData.observe(this) {

            savedAdapter = SavedArticleAdapter(requireContext(), it)
            binding.newsList.adapter = savedAdapter
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
        savedAdapter.setOnClickListener(object :
            SavedArticleAdapter.OnClickListener {
            override fun onClick(newsHeadlines: NewsHeadlines) {
                onHeadlineClick(newsHeadlines)
            }

            override fun onDeleteArticle(news: NewsHeadlines) {
                viewModel.deleteNews(requireContext(), news)
                Toast.makeText(
                    requireContext(),
                    "The article \"${news.title?.truncateWithDots(15)}\" is deleted.",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.getSavedArticles(requireContext())
            }


        })
    }

    private fun onHeadlineClick(headline: NewsHeadlines) {

        val intent = Intent(requireContext(), HeadlinesDetailsActivity::class.java).apply {
            putExtra(HEADLINE_EXTRA, headline)
        }
        requireActivity().startActivity(intent)
    }




}