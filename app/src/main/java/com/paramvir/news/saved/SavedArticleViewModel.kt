package com.paramvir.news.saved

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paramvir.news.headlines.domain.NewsHeadlines
import com.paramvir.news.utils.getNewsFromNewsHeadlines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedArticleViewModel @Inject constructor(

) : ViewModel() {
    val savedHeadlinesLiveData: MutableLiveData<List<NewsHeadlines>> = MutableLiveData()


    fun getSavedArticles(context: Context) {
        savedHeadlinesLiveData.value = FileHelper.readHeadlines(context = context)
    }

    fun deleteNews(newsHeadlines: NewsHeadlines) {
        val news = getNewsFromNewsHeadlines(newsHeadlines)
        viewModelScope.launch {
            // newsRepository.delete(news)
        }

    }


}