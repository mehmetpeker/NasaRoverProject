package com.mehmetpeker.nasaroverproject.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mehmetpeker.nasaroverproject.api.ApiService
import com.mehmetpeker.nasaroverproject.data.model.Photo
import com.mehmetpeker.nasaroverproject.util.Constants

class RoverPagingSource(
    private val apiService: ApiService,
    private val roverName: String,
    private val sol: Int,
    private val camera: String,
    private val apiKey: String
) : PagingSource<Int, Photo>() {

    companion object {
        const val ITEMS_PER_PAGE = 25
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val pageNumber = params.key ?: 1
        return try {

            val queryMap = mutableMapOf<String, Any>(
                "sol" to sol,
                "page" to pageNumber,
                "api_key" to apiKey
            )

            if(camera != Constants.CAMERA_DEFAULT)
                queryMap["camera"] = camera
            Log.d("CustomLog",pageNumber.toString())
            val response = apiService.getRoverPhotoByName(roverName,queryMap)
            val pagedResponse = response.body()
            val isLastPage = pagedResponse!!.photos.size < ITEMS_PER_PAGE
            val nextPageNumber: Int? = if (isLastPage) null else pageNumber + 1
            val prevPageNumber: Int? = if (pageNumber > 1) pageNumber - 1 else null

            LoadResult.Page(
                data = pagedResponse.photos,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}