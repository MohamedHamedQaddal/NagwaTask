package com.app.nagwa.nagwatask.modules.media.presentation.mapper

import com.app.nagwa.nagwatask.modules.media.domain.model.MediaDomainModel
import com.app.nagwa.nagwatask.modules.media.presentation.model.MediaUIModel

fun MediaDomainModel.toUI(): MediaUIModel {
    return MediaUIModel(id = id, name = name, type = type, url = url)
}