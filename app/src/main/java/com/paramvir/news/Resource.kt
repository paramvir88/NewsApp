package com.paramvir.news

/**
 * Represents the response from the network with possible states as [ResourceSuccess], [ResourceError] and [ResourceLoading]
 */
sealed class Resource<out T> {
    data class ResourceSuccess<out T>(val data: T) : Resource<T>()
    data class ResourceError<out T>(val error: Throwable) : Resource<T>()
    data class ResourceLoading<out T>(val data: T? = null) : Resource<T>()
}
