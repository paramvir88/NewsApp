package com.paramvir.paramnews.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paramvir.paramnews.headlines.News
import com.paramvir.paramnews.headlines.domain.NewsHeadlines
import com.paramvir.paramnews.utils.getNewsFromNewsHeadlines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedArticleViewModel @Inject constructor(

) : ViewModel() {
    val savedHeadlinesLiveData: LiveData<List<News>> = MutableLiveData<List<News>>()

    fun deleteNews(newsHeadlines: NewsHeadlines) {
        val news = getNewsFromNewsHeadlines(newsHeadlines)
        viewModelScope.launch {
            // newsRepository.delete(news)
        }

    }


}