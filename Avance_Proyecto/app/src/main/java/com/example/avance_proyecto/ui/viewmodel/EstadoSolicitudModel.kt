package com.example.avance_proyecto.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapp.domain.DataUseCase
import com.example.avance_proyecto.data.DataEstadoSolicitud
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstadoSolicitudViewModel : ViewModel() {

    val obtenerDatosUseCase = DataUseCase()

    private val _estadosolicitudResult = MutableLiveData<DataEstadoSolicitud>()
    val estadosolicitudResult: LiveData<DataEstadoSolicitud> = _estadosolicitudResult

    init{
        getEstadoSolicitud()
    }

    private fun getEstadoSolicitud(){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getEstadoSolicitudUC().onSuccess {
                _estadosolicitudResult.postValue(it)
                println(it)
            }.onFailure {
                println("ALGO PASÓ")
            }
        }
    }

}