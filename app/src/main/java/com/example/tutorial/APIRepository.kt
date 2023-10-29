package com.example.tutorial

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class APIRepository @Inject constructor(val apiInterface: ApiInterface) {
    fun getAPI() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {APIPagingSource(apiInterface)}
    ).liveData
}