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

    private val _dataEstadoForChart = MutableLiveData<ArrayList<Int>>()
    val dataEstadoForChart: LiveData<ArrayList<Int>> = _dataEstadoForChart

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
            _dataEstadoForChart.value = getCantidadEstado()
            _isLoading.value = false
        }
    }

    fun getCantidadEstado(): ArrayList<Int>{
        val datoPendiente = _conteoEstadosolicitudResult.value?.filter { userData->
            userData.descripcion.lowercase().equals("pendiente")
        }
        val cantidadPendiente = datoPendiente?.get(0)?.cantidad ?: 0

        val datoCotizado = _conteoEstadosolicitudResult.value?.filter { userData->
            userData.descripcion.lowercase().equals("cotizado")
        }
        val cantidadCotizado = datoCotizado?.get(0)?.cantidad ?: 0

        val datoObservado = _conteoEstadosolicitudResult.value?.filter { userData->
            userData.descripcion.lowercase().equals("observado")
        }
        val cantidadObservado = datoObservado?.get(0)?.cantidad ?: 0

        val datoAnulado = _conteoEstadosolicitudResult.value?.filter { userData->
            userData.descripcion.lowercase().equals("anulado")
        }
        val cantidadAnulado = datoAnulado?.get(0)?.cantidad ?: 0

        val listaDeEstadoCantidad: ArrayList<Int> = arrayListOf(
            cantidadPendiente,
            cantidadCotizado,
            cantidadObservado,
            cantidadAnulado
        )
        return listaDeEstadoCantidad
    }

    fun updateIsError(value: Boolean) {
        _isError.value = value
    }

}