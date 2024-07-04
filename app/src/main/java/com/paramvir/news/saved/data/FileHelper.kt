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

/**
 * Helper for serializing and storing the [NewsHeadlines] in File storage.
 */
object FileHelper {

    private const val FILE_NAME = "headlines.dat"
    private const val TAG = "FileHelper"

    fun saveHeadlines(context: Context, headlines: List<NewsHeadlines>) {
        val existingHeadlines = readHeadlines(context).toMutableSet()
        existingHeadlines.addAll(headlines)
        writeHeadlinesToFile(context, existingHeadlines)
    }

    fun saveHeadline(context: Context, headline: NewsHeadlines) {
        val existingHeadlines = readHeadlines(context).toMutableSet()
        existingHeadlines.add(headline)
        writeHeadlinesToFile(context, existingHeadlines)
    }

    private fun writeHeadlinesToFile(context: Context, headlines: Set<NewsHeadlines>) {
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

    fun readHeadlines(context: Context): Set<NewsHeadlines> {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME)
        var fis: FileInputStream? = null
        var ois: ObjectInputStream? = null

            return try {
                if (!file.exists()) {
                    return emptySet()
                }

                fis = FileInputStream(file)
                ois = ObjectInputStream(fis)
                @Suppress("UNCHECKED_CAST")
                ois.readObject() as Set<NewsHeadlines>
            } catch (e: IOException) {
                Log.e(TAG, "Failed to read headlines", e)
                emptySet()
            } catch (e: ClassNotFoundException) {
                Log.e(TAG, "Failed to cast to List<Headline>", e)
                emptySet()
            } finally {
                ois?.close()
                fis?.close()

        }
    }

     fun deleteHeadline(context: Context, headline: NewsHeadlines) {

            val existingHeadlines = readHeadlines(context).toMutableSet()
            if (existingHeadlines.remove(headline)) {
                writeHeadlinesToFile(context, existingHeadlines)
                Log.i(TAG, "Headline deleted successfully")
            } else {
                Log.i(TAG, "Headline not found")
            }
    }
}
