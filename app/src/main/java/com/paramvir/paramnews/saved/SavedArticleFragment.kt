package com.paramvir.paramnews.saved

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.paramvir.paramnews.databinding.FragmentSavedBinding
import com.paramvir.paramnews.getNewsHeadlinesFromNews
import com.paramvir.paramnews.headlines.HEADLINE_URL
import com.paramvir.paramnews.headlines.domain.NewsHeadlines
import com.paramvir.paramnews.headlines.views.HeadlinesDetailsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SavedArticleFragment : Fragment() {
    private val viewModel: SavedArticleViewModel by viewModels()
    private lateinit var savedadapter: SavedArticleAdapter
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeList()
    }

    override fun onStart() {
        super.onStart()
        viewModel.savedHeadlinesLiveData.observe(this) {
            val list = mutableListOf<NewsHeadlines>()
            it.forEach {
                list.add(
                    getNewsHeadlinesFromNews(it)
                )
            }
            savedadapter = SavedArticleAdapter(requireContext(), list)
            binding.newsList.adapter = savedadapter
            handleNewsClick()

        }
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