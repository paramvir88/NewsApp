package com.paramvir.news.headlines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paramvir.news.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for the [HeadlinesFragment].
 */
@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val headlinesRepo: IHeadlinesRepo,
) : ViewModel() {

    private val _headlinesLiveData: MutableLiveData<Resource<List<NewsHeadlines>>> =
        MutableLiveData()

    val headlinesLiveData = _headlinesLiveData
    private val progress = MutableLiveData<Boolean>()

    /**
     * Fetching [NewsHeadlines]. The data is updated via the Livedata.
     */
    fun getHeadlines(sources: List<String>) {

        _headlinesLiveData.value = Resource.ResourceLoading()
        viewModelScope.launch {
            val response =
                headlinesRepo.getHeadlines(getSelectedSources(sources).ifEmpty { DEFAULT_SOURCE })
            if (response.isSuccessful) {
                response.body()?.let {
                    _headlinesLiveData.value =
                        Resource.ResourceSuccess(mapArticlesToNewsHeadlines(it.articles))
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
            sourceStr = "$sourceStr$it"
        }
        return sourceStr
    }

    private fun mapArticlesToNewsHeadlines(articles: List<Article>): List<NewsHeadlines> {
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
}