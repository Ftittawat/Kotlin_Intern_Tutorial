package com.example.tutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import javax.inject.Inject

class APIViewModel @Inject constructor(val apiRepository: APIRepository) : ViewModel() {
    val list = apiRepository.getAPI().cachedIn(viewModelScope)
}