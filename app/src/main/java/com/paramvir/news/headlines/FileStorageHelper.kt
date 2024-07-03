package com.paramvir.news.headlines

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

object FileStorageHelper {

    private const val TAG = "com.paramvir.news.headlines.FileStorageHelper"
    private const val FILE_NAME = "headlines.dat"

    fun saveUrlToFile(context: Context?, url: String?) {
        if (context == null || url.isNullOrEmpty()) {
            Log.e(TAG, "Context or URL is null/empty")
            return
        }

        var fos: FileOutputStream? = null
        try {
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME)
            fos = FileOutputStream(file, true) // true to append to the file
            fos.write((url + System.lineSeparator()).toByteArray())
            Log.i(TAG, "URL saved successfully: $url")
        } catch (e: IOException) {
            Log.e(TAG, "Failed to save URL to file", e)
        } finally {
            fos?.let {
                try {
                    it.close()
                } catch (e: IOException) {
                    Log.e(TAG, "Failed to close FileOutputStream", e)
                }
            }
        }
    }

    fun getAllSavedUrls(context: Context?): List<String> {

        val urls = mutableListOf<String>()
        var fis: FileInputStream? = null
        try {
            val file =
                File(context?.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME)
            if (!file.exists()) {
                Log.i(TAG, "File does not exist")
                return emptyList()
            }

            fis = FileInputStream(file)
            val content = fis.bufferedReader().use { it.readText() }
            urls.addAll(content.split(System.lineSeparator()).filter { it.isNotEmpty() })
        } catch (e: IOException) {
            Log.e(TAG, "Failed to read URLs from file", e)
        } finally {
            fis?.let {
                try {
                    it.close()
                } catch (e: IOException) {
                    Log.e(TAG, "Failed to close FileInputStream", e)
                }
            }
        }

        return urls
    }
}
