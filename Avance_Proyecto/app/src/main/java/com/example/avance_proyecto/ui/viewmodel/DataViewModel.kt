package com.example.avance_proyecto.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avance_proyecto.data.model.EstadoSolicitud
import com.example.avance_proyecto.domain.EstadoSolicitudUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel () : ViewModel() {

    /*val obtenerDatosUseCase = EstadoSolicitudUseCase()

    private val _DataEstadoSolicitud = MutableLiveData<EstadoSolicitud>()
    val dataEstadoSolicitud: LiveData<EstadoSolicitud> = _DataEstadoSolicitud

    init{
        startData()
    }

    private fun startData(){//(Dispatchers.IO) para viewModelScope
        /*viewModelScope.launch(Dispatchers.IO) {
            obtenerDatosUseCase.getMovies().onSuccess {
                _remoteResult.postValue(it)
            }.onFailure {
                println("Hubo un error")//se puede agregar un popup de error y un boton de restart (lo que quieran)
            }
        }*/
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getEstadoSolicitudUC().onSuccess {
                _DataEstadoSolicitud.postValue(it)
                println(it)
            }.onFailure {
                println("ALGO PASÓ")
            }
        }
    }*/

}