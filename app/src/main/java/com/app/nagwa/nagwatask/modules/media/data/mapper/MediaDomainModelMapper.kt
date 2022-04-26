package com.app.nagwa.nagwatask.modules.media.data.mapper

import com.app.nagwa.nagwatask.modules.media.data.model.MediaDataModel
import com.app.nagwa.nagwatask.modules.media.domain.model.MediaDomainModel

fun MediaDataModel.toDomainModel(): MediaDomainModel {
    return MediaDomainModel(id = id, name = name, type = type, url = url)
}