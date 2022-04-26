package com.app.nagwa.nagwatask.modules.media.data.repository

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat.getSystemService
import com.app.nagwa.nagwatask.modules.media.data.mapper.toDomainModel
import com.app.nagwa.nagwatask.modules.media.data.model.MediaDataModel
import com.app.nagwa.nagwatask.modules.media.domain.model.MediaDomainModel
import com.app.nagwa.nagwatask.modules.media.domain.repository.MediaRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.delay
import java.io.File
import java.util.*
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(private val application: Application, private val moshi: Moshi) :
    MediaRepository {

    override suspend fun getMediaList(): List<MediaDomainModel> {
        delay(1000)
        lateinit var jsonString: String
        try {
            jsonString = application.assets.open("getListOfFilesResponse.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (exception: Exception) {
            Log.d("MediaDebug", "getMediaList: ")
        }

        val type =
            Types.newParameterizedType(List::class.java, MediaDataModel::class.java)

        val adapter = moshi.adapter<List<MediaDataModel>>(type)
        val mediaList = adapter.fromJson(jsonString)?.map { it.toDomainModel() } ?: emptyList()

        mediaList.forEach { Log.d("MediaDebug", "getMediaList: ${it.name}") }
        Log.d("MediaDebug", "getMediaList: $mediaList")
        return mediaList
    }

    override suspend fun downloadMediaItem(url: String) {
        try {
            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
            val uri = Uri.parse(url)
            var fileName = Date().time
            var fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            val request = DownloadManager.Request(uri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setMimeType(MimeTypeMap.getFileExtensionFromUrl(url))
                .setAllowedOverRoaming(false)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, File.separator+fileName+".${fileExtension}")
            downloadManager!!.enqueue(request)
        } catch (e: Exception) {
            Log.i("TAG", e.message.toString())
        }
    }
}