package com.shahbaz.learning.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahbaz.learning.data.model.Data
import com.shahbaz.learning.data.repository.DataRepository
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: DataRepository) : ViewModel() {

    companion object{
        const val TAG = "MainViewModel"
    }

    private val _data = mutableStateOf<Data?>(null)
    val data: State<Data?> = _data

    private val _isLoading = mutableStateOf<Boolean?>(null)
    val isLoading: State<Boolean?> = _isLoading

    fun getData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val fetchedData = userRepository.getData()
                _data.value = fetchedData
            } catch (e: Exception) {
                Log.d(TAG, "getData: "+ e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

}