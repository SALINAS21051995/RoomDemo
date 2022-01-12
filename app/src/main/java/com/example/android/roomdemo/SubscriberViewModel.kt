package com.example.android.roomdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.roomdemo.db.Subscriber
import com.example.android.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository): ViewModel() {

    val subscribers = repository.subscribers

    val inputName = MutableLiveData<String?>()
    val inputEmail = MutableLiveData<String?>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init{
        clearAllOrDeleteButtonText.value = "clear"
        saveOrUpdateButtonText.value = "save"
    }

    fun saveOrUpdate(){
        val name = inputName.value!!
        val email = inputEmail.value!!
        insert(Subscriber(0,name, email))
        inputName.value = null
        inputEmail.value = null
    }

    fun clearOrDelete(){
        clearAll()
    }

    fun insert(subscriber: Subscriber) = viewModelScope.launch {
            repository.insert(subscriber)
        }

    fun update(subscriber: Subscriber) = viewModelScope.launch {
            repository.update(subscriber)
        }

    fun delete(subscriber: Subscriber) = viewModelScope.launch {
            repository.delete(subscriber)
        }

    fun clearAll() = viewModelScope.launch {
            repository.deleteAll()
        }
}