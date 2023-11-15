package com.example.avance_proyecto.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitud
import com.example.avance_proyecto.data.model.EstadoSolicitud
import com.example.avance_proyecto.domain.EstadoSolicitudUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstadoSolicitudViewModel : ViewModel() {

    val obtenerDatosUseCase = EstadoSolicitudUseCase()

    private val _estadosolicitudResult = MutableLiveData<EstadoSolicitud>()
    val estadosolicitudResult: LiveData<EstadoSolicitud> = _estadosolicitudResult

    private val _conteoEstadosolicitudResult = MutableLiveData<ConteoEstadoSolicitud>()
    val conteoEstadosolicitudResult: LiveData<ConteoEstadoSolicitud> = _conteoEstadosolicitudResult

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isMessageError = mutableStateOf("Error: No se pudo cargar los datos")
    val isMessageError: State<String> = _isMessageError

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init{
        getConteoEstadoSolicitud()
    }

    private fun getConteoEstadoSolicitud(){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getConteoEstadoSolicitudUC().onSuccess {
                _conteoEstadosolicitudResult.postValue(it)
            }.onFailure {
                println("ALGO PASÓ")
                _isError.value = true
            }
            _isLoading.value = false
        }
    }


    fun updateIsError(value: Boolean) {
        _isError.value = value
    }

}