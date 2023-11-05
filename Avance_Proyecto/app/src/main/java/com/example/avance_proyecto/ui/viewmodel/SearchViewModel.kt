package com.example.avance_proyecto.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.avance_proyecto.data.uistate.SearchUiState

class SearchViewModel : ViewModel() {

    private val _searchWidgetState: MutableState<SearchUiState> =
        mutableStateOf(value = SearchUiState.CLOSED)
    val searchWidgetState: State<SearchUiState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchUiState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}