package com.paramvir.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.paramvir.news.common.network.Resource
import com.paramvir.news.headlines.data.Article
import com.paramvir.news.headlines.data.HeadlinesResponse
import com.paramvir.news.headlines.data.IHeadlinesRepo
import com.paramvir.news.headlines.data.Source
import com.paramvir.news.headlines.views.HeadlinesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class HeadlinesViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var headlinesViewModel: HeadlinesViewModel

    @MockK
    private lateinit var networkRepo: IHeadlinesRepo


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        headlinesViewModel = HeadlinesViewModel(networkRepo)
    }

    private fun getHeadlinesResponse(): HeadlinesResponse {
        val list = mutableListOf<Article>()
        list.add(
            Article(
                "bbc", "vvsah", "fghj", "gjhg", Source("67", "fg"), "fghj", "fgh", "fgvh"
            )
        )
        return HeadlinesResponse(list, "ok", 10)
    }

    @Test
    fun `test successful received  headlines `() {
        coEvery { networkRepo.getHeadlines("bbc-news,") } returns Response.success(
            getHeadlinesResponse()
        )
        headlinesViewModel.headlinesLiveData.observeForever {}
        headlinesViewModel.getHeadlines(listOf("bbc-news,"))
        assert(headlinesViewModel.headlinesLiveData.value is Resource.ResourceSuccess)
    }

    @Test
    fun `test failed received  headlines `() {
        coEvery { networkRepo.getHeadlines("bbc-news,") } returns Response.error(
            404, ResponseBody.create(null, "Not found response body")
        )
        headlinesViewModel.getHeadlines(listOf("bbc-news,"))
        headlinesViewModel.headlinesLiveData.observeForever {}
        assert(headlinesViewModel.headlinesLiveData.value is Resource.ResourceError)
    }


}