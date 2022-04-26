package com.app.nagwa.nagwatask.modules.media.domain.repository

import com.app.nagwa.nagwatask.modules.media.domain.model.MediaDomainModel

interface MediaRepository {
    suspend fun getMediaList(): List<MediaDomainModel>
    suspend fun downloadMediaItem(s: String)
}