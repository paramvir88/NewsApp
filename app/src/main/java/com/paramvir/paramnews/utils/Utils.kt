package com.paramvir.paramnews.utils

import com.paramvir.paramnews.headlines.News
import com.paramvir.paramnews.headlines.domain.NewsHeadlines

fun getNewsFromNewsHeadlines(newsHeadlines: NewsHeadlines): News {
    return News(
        newsHeadlines.id,
        newsHeadlines.title ?: "",
        newsHeadlines.description ?: "",
        newsHeadlines.author ?: "",
        newsHeadlines.pic ?: "",
        newsHeadlines.url ?: ""
    )
}


fun getNewsHeadlinesFromNews(news: News): NewsHeadlines {
    return NewsHeadlines(
        title = news.title,
        description = news.description,
        author = news.author,
        pic = news.pic,
        url = news.url
    )
}