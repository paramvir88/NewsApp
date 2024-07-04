package com.paramvir.news.saved.data

import android.content.Context
import android.os.Environment
import android.util.Log
import com.paramvir.news.headlines.domain.NewsHeadlines
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object FileHelper {

    private const val FILE_NAME = "headlines.dat"
    private const val TAG = "FileHelper"

    fun saveHeadlines(context: Context, headlines: List<NewsHeadlines>) {
        val existingHeadlines = readHeadlines(context).toMutableList()
        existingHeadlines.addAll(headlines)
        writeHeadlinesToFile(context, existingHeadlines)
    }

    fun saveHeadline(context: Context, headline: NewsHeadlines) {
        val existingHeadlines = readHeadlines(context).toMutableList()
        existingHeadlines.add(headline)
        writeHeadlinesToFile(context, existingHeadlines)
    }

    private fun writeHeadlinesToFile(context: Context, headlines: List<NewsHeadlines>) {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME)
        var fos: FileOutputStream? = null
        var oos: ObjectOutputStream? = null

        try {
            fos = FileOutputStream(file)
            oos = ObjectOutputStream(fos)
            oos.writeObject(headlines)
            Log.i(TAG, "Headlines saved successfully")
        } catch (e: IOException) {
            Log.e(TAG, "Failed to save headlines", e)
        } finally {
            oos?.close()
            fos?.close()
        }
    }

    fun readHeadlines(context: Context): List<NewsHeadlines> {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME)
        var fis: FileInputStream? = null
        var ois: ObjectInputStream? = null

        return try {
            if (!file.exists()) {
                return emptyList()
            }

            fis = FileInputStream(file)
            ois = ObjectInputStream(fis)
            @Suppress("UNCHECKED_CAST")
            ois.readObject() as List<NewsHeadlines>
        } catch (e: IOException) {
            Log.e(TAG, "Failed to read headlines", e)
            emptyList()
        } catch (e: ClassNotFoundException) {
            Log.e(TAG, "Failed to cast to List<Headline>", e)
            emptyList()
        } finally {
            ois?.close()
            fis?.close()
        }
    }
}
