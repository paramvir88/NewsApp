package com.paramvir.paramnews.headlines.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paramvir.paramnews.Resource
import com.paramvir.paramnews.headlines.DEFAULT_SOURCE
import com.paramvir.paramnews.headlines.News
import com.paramvir.paramnews.headlines.domain.NewsHeadlines
import com.paramvir.paramnews.headlines.network.Article
import com.paramvir.paramnews.headlines.repo.IHeadlinesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for the HeadlinesFragment.
 */
@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val headlineRepo: IHeadlinesRepo,
) : ViewModel() {

    private val _headlinesLiveData: MutableLiveData<Resource<List<NewsHeadlines>>> =
        MutableLiveData()

    val headlinesLiveData = _headlinesLiveData
    val progress = MutableLiveData<Boolean>()

    /**
     * For the fragment to get the headlines data. The data is updated via the Livedata
     */
    fun getHeadlines(sources: List<String>) {

        _headlinesLiveData.value = Resource.ResourceLoading()
        viewModelScope.launch {
            val response =
                headlineRepo.getHeadlines(getSelectedSources(sources).ifEmpty { DEFAULT_SOURCE })
            if (response.isSuccessful) {
                response.body()?.let {
                    _headlinesLiveData.value =
                        Resource.ResourceSuccess(getHeadlinesFromArticles(it.articles))
                    progress.value = false
                }
            } else {
                _headlinesLiveData.value =
                    Resource.ResourceError(Exception(response.message()))
            }
        }
    }

    private fun getSelectedSources(sources: List<String>): String {
        var sourceStr = ""
        sources.forEach {
            sourceStr = "$sourceStr,$it"
        }
        return sourceStr
    }

    private fun getHeadlinesFromArticles(articles: List<Article>): List<NewsHeadlines> {
        return articles.map {
            NewsHeadlines(
                title = it.title,
                description = it.description,
                author = it.author,
                pic = it.urlToImage,
                url = it.url
            )
        }.toMutableList()
    }

    /** The news source is to be be given to the news api to get the news only for the given source.
     *[]
     */
    fun getNewsSource(sources: String): String {

        return ""
    }

    fun saveForLater(news: News) {

    }
}