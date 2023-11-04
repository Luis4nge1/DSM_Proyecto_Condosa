package com.example.avance_proyecto.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapp.domain.DataUseCase
import com.example.avance_proyecto.data.DataEstadoSolicitud
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel () : ViewModel() {

    val obtenerDatosUseCase = DataUseCase()

    private val _DataEstadoSolicitud = MutableLiveData<DataEstadoSolicitud>()
    val dataEstadoSolicitud: LiveData<DataEstadoSolicitud> = _DataEstadoSolicitud

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
            obtenerDatosUseCase.getEstadoSolicitud().onSuccess {
                _DataEstadoSolicitud.postValue(it)
                println(it)
            }.onFailure {
                println("ALGO PASÓ")
            }
        }
    }

}