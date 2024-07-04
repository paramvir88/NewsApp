package com.paramvir.news.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paramvir.news.Resource
import com.paramvir.news.api.Source
import com.paramvir.news.sources.data.ISourceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(private val repo: ISourceRepo) : ViewModel() {
    private val _sourceLiveData: MutableLiveData<Resource<List<NewsSources>>> = MutableLiveData()
    val sourceLiveData: LiveData<Resource<List<NewsSources>>> = _sourceLiveData
    fun getSources() {
        viewModelScope.launch {
            val res = repo.getAllSources()
            if (res.isSuccessful) {
                res.body()?.let {
                    _sourceLiveData.value =
                        Resource.ResourceSuccess(getNewsSources(res.body()?.sources))
                }
            } else {
                _sourceLiveData.value = Resource.ResourceError(Exception("Unable to get sources"))
            }
        }
    }

    private fun getNewsSources(list: List<Source>?): List<NewsSources> {
        val listOfNewSources = mutableListOf<NewsSources>()
        list?.forEach {
            listOfNewSources.add(NewsSources(it.id, it.name))
        }
        return listOfNewSources
    }
}