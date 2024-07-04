package com.paramvir.news.saved.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.news.R
import com.paramvir.news.common.ui.BaseFragment
import com.paramvir.news.common.utils.HEADLINE_URL
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
            override fun onClick(url: String) {
                val bundle = Bundle()
                bundle.putString(HEADLINE_URL, url)
                val intent = Intent(requireContext(), HeadlinesDetailsActivity::class.java)
                intent.putExtras(bundle)
                requireActivity().startActivity(intent)
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

    fun String.truncateWithDots(maxLength: Int = 8): String {
        return if (this.length > maxLength - 2) {
            this.substring(0, maxLength - 2) + ".."
        } else {
            this
        }
    }


}