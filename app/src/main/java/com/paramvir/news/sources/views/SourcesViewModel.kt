package com.paramvir.news.sources.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paramvir.news.common.network.Resource
import com.paramvir.news.sources.data.ISourceRepo
import com.paramvir.news.sources.data.Source
import com.paramvir.news.sources.domain.NewsSources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the [SourcesFragment].
 */
@HiltViewModel
class SourcesViewModel @Inject constructor(private val repo: ISourceRepo) : ViewModel() {

    private val _sourceLiveData: MutableLiveData<Resource<List<NewsSources>>> = MutableLiveData()
    val sourceLiveData: LiveData<Resource<List<NewsSources>>> = _sourceLiveData

    fun getSources() {
        _sourceLiveData.value = Resource.ResourceLoading()
        viewModelScope.launch {
            val res = repo.getAllSources()
            if (res.isSuccessful) {
                res.body()?.let {
                    _sourceLiveData.value =
                        Resource.ResourceSuccess(mapSourcesToNewsSources(res.body()?.sources))
                }
            } else {
                _sourceLiveData.value = Resource.ResourceError(Exception(res.message()))
            }
        }
    }

    private fun mapSourcesToNewsSources(list: List<Source>?): List<NewsSources> {
        val newSources = mutableListOf<NewsSources>()
        list?.forEach {
            newSources.add(NewsSources(it.id, it.name))
        }
        return newSources
    }
}