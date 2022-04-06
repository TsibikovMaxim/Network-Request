package com.example.test22byte.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test22byte.api.Api
import com.example.test22byte.data.Object
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    api: Api
) : ViewModel() {
    private val restaurantsLiveData = MutableLiveData<Object>()
    val restaurants: LiveData<Object> = restaurantsLiveData

    init {
        viewModelScope.launch {
            val restaurants = api.getData()
            delay(2000)
            restaurantsLiveData.value = restaurants
        }
    }
}