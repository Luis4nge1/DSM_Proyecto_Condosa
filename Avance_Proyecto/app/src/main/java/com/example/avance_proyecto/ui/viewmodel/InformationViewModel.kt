package com.example.avance_proyecto.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.avance_proyecto.data.uistate.InformationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InformationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InformationUiState())
    val uiState: StateFlow<InformationUiState> = _uiState.asStateFlow()
}