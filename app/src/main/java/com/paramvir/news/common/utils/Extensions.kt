package com.paramvir.news.common.utils

fun String.truncateWithDots(maxLength: Int = 8): String {
    return if (this.length > maxLength - 2) {
        this.substring(0, maxLength - 2) + ".."
    } else {
        this
    }
}