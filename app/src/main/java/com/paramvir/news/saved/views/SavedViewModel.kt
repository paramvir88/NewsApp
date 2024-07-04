package com.paramvir.news.saved.views

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paramvir.news.headlines.domain.NewsHeadlines
import com.paramvir.news.saved.data.FileHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the [SavedFragment]
 */
@HiltViewModel
class SavedViewModel @Inject constructor(

) : ViewModel() {
    val savedHeadlinesLiveData: MutableLiveData<List<NewsHeadlines>> = MutableLiveData()


    fun getSavedArticles(context: Context) {
        savedHeadlinesLiveData.value = FileHelper.readHeadlines(context = context)
    }

    fun deleteNews(context: Context, newsHeadlines: NewsHeadlines) {
        FileHelper.deleteHeadline(context, newsHeadlines)
    }


}