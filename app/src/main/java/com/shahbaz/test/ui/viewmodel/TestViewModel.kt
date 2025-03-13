package com.shahbaz.test.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahbaz.test.data.repository.DataRepository
import kotlinx.coroutines.launch

class TestViewModel(private val userRepository: DataRepository) : ViewModel() {

    companion object{
        const val TAG = "TestViewModel"
    }

    private val _userNames = MutableLiveData<List<String>>()
    val userNames: LiveData<List<String>> get() = _userNames

    private val _currentTemperature = MutableLiveData<String>()
    val currentTemperature: LiveData<String> get() = _currentTemperature

    init {
        getCurrentTemperature()
        fetchUsers()
    }


    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val fetchUsers = userRepository.getUsers()
                _userNames.value = fetchUsers

            } catch (e: Exception) {
                Log.d(TAG, "fetchUsers: Error "+ e.message)
                val fetchUsers = userRepository.getUsers()
                _userNames.value = fetchUsers
            }
        }
    }

    fun getCurrentTemperature() {
        viewModelScope.launch {
            try {
                val fetchedData = userRepository.getCurrentTemperature()
                _currentTemperature.value = fetchedData
            } catch (e: Exception) {
                Log.d(TAG, "getData: Error: "+ e.message)
                val fetchedData = userRepository.getCurrentTemperature()
                _currentTemperature.value = fetchedData
            }
        }
    }

}