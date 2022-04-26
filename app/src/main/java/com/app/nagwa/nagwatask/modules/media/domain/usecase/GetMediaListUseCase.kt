package com.app.nagwa.nagwatask.modules.media.domain.usecase

import com.app.nagwa.nagwatask.modules.media.domain.repository.MediaRepository
import javax.inject.Inject

class GetMediaListUseCase @Inject constructor(private val repository: MediaRepository) {
    suspend fun execute() = repository.getMediaList()
}