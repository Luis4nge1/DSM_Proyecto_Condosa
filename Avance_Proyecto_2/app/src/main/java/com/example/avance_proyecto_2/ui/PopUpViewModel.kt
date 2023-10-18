package com.example.avance_proyecto_2.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PopUpViewModel : ViewModel() {


    var isDialogShown by mutableStateOf(false)
        private set

    fun onButtonClick(){
        isDialogShown = true
    }

    fun onDismissDialog(){
        isDialogShown = false
    }
}