package com.example.avance_proyecto.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avance_proyecto.model.SearchState
import com.example.avance_proyecto.model.TrackingCard

class SearchViewModel : ViewModel() {

    private val _searchWidgetState: MutableState<SearchState> =
        mutableStateOf(value = SearchState.CLOSED)
    val searchWidgetState: State<SearchState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}