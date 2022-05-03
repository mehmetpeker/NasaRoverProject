package com.mehmetpeker.nasaroverproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mehmetpeker.nasaroverproject.data.datasource.RoverPagingSource
import com.mehmetpeker.nasaroverproject.data.model.Photo
import com.mehmetpeker.nasaroverproject.data.repository.NasaRoverRepository
import com.mehmetpeker.nasaroverproject.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SpiritViewModel @Inject constructor(private val nasaRoverRepository: NasaRoverRepository) :
    ViewModel() {
    private val roverName = "spirit"
    var selectedCamera = Constants.CAMERA_DEFAULT
    fun getSpirithoto(): Flow<PagingData<Photo>> {
        return Pager(PagingConfig(RoverPagingSource.ITEMS_PER_PAGE)) {
            nasaRoverRepository.getRoverPagingSource(
                roverName,
                sol = 1000,
                selectedCamera,
                apiKey = Constants.API_KEY
            )
        }.flow.cachedIn(viewModelScope)
    }
}