package com.example.avance_proyecto.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.avance_proyecto.data.uistate.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SolicitudViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _isLoadingSearch = MutableStateFlow(false)
    val isLoadingSearch = _isLoadingSearch.asStateFlow()

    private val _searchWidgetState = MutableStateFlow(value = SearchUiState.CLOSED)
    val searchWidgetState = _searchWidgetState.asStateFlow()

    fun updateSearchWidgetState(newValue: SearchUiState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchText.value = newValue
    }

    fun updateIsLoadingSearch(value: Boolean) {
        _isLoadingSearch.value = value
    }

}