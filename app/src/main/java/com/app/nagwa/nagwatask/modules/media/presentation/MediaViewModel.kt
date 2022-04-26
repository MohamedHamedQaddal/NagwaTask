package com.app.nagwa.nagwatask.modules.media.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.nagwa.nagwatask.modules.media.domain.usecase.DownloadMediaItemUseCase
import com.app.nagwa.nagwatask.modules.media.domain.usecase.GetMediaListUseCase
import com.app.nagwa.nagwatask.modules.media.presentation.mapper.toUI
import com.app.nagwa.nagwatask.modules.media.presentation.model.BaseMediaPresentationModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MediaViewModel @Inject constructor(
    private val getMediaListUseCase: GetMediaListUseCase,
    private val downloadMediaItemUseCase: DownloadMediaItemUseCase
) : ViewModel() {

    private val _mediaList = MutableLiveData<List<BaseMediaPresentationModel>>()
    val mediaList: LiveData<List<BaseMediaPresentationModel>>
        get() = _mediaList


    init {
        viewModelScope.launch {
            _mediaList.postValue(getMediaListUseCase.execute().map { it.toUI() })
        }
    }

    fun downloadMediaItem(){
        viewModelScope.launch {
            downloadMediaItemUseCase.execute()
        }
    }
}