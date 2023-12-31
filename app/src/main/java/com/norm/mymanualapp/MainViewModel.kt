package com.norm.mymanualapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.norm.mymanualapp.db.MainDb
import com.norm.mymanualapp.utils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDb: MainDb
) : ViewModel() {
    val mainList = mutableStateOf(emptyList<ListItem>())
    var job: Job? = null

    fun getAllItemsByCategory(category: String) {
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getAllItemsByCategory(category).collect { list ->
                mainList.value = list
            }
        }
    }

    fun getFavorites() {
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getFavorites().collect { list ->
                mainList.value = list
            }
        }
    }

    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }

    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }
}