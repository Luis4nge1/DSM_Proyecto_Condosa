package com.example.avance_proyecto.ui.viewmodel

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

    init{
        getConteoEstadoSolicitud()
    }

    private fun getConteoEstadoSolicitud(){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getConteoEstadoSolicitudUC().onSuccess {
                _conteoEstadosolicitudResult.postValue(it)
                println(it)
            }.onFailure {
                println("ALGO PASÓ")
            }
        }
    }

}