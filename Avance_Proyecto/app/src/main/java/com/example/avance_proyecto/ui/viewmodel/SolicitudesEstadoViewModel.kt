package com.example.avance_proyecto.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avance_proyecto.data.model.SolicitudesEstado
import com.example.avance_proyecto.data.model.SolicitudesEstadoItem
import com.example.avance_proyecto.domain.SolicitudesEstadoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SolicitudesEstadoViewModel : ViewModel() {

    val obtenerDatosUseCase = SolicitudesEstadoUseCase()

    private val _solicitudesEstadoResult = MutableStateFlow(SolicitudesEstado())
    val solicitudesEstadoResult = _solicitudesEstadoResult.asStateFlow()

    private val _solicitudesEstadoPendienteResult = MutableStateFlow(listOf<SolicitudesEstadoItem>())
    val solicitudesEstadoPendienteResult = _solicitudesEstadoPendienteResult.asStateFlow()

    private val _filtradoPendiente = MutableStateFlow(listOf<SolicitudesEstadoItem>())
    val filtradoPendiente = _filtradoPendiente.asStateFlow()

    private val _solicitudesEstadoCotizadoResult = MutableStateFlow(listOf<SolicitudesEstadoItem>())
    val solicitudesEstadoCotizadoResult = _solicitudesEstadoCotizadoResult.asStateFlow()

    private val _filtradoCotizado = MutableStateFlow(listOf<SolicitudesEstadoItem>())
    val filtradoCotizado = _filtradoCotizado.asStateFlow()

    private val _solicitudesEstadoObservadoResult = MutableStateFlow(listOf<SolicitudesEstadoItem>())
    val solicitudesEstadoObservadoResult = _solicitudesEstadoObservadoResult.asStateFlow()

    private val _filtradoObservado = MutableStateFlow(listOf<SolicitudesEstadoItem>())
    val filtradoObservado = _filtradoObservado.asStateFlow()

    private val _solicitudesEstadoAnuladoResult = MutableStateFlow(listOf<SolicitudesEstadoItem>())
    val solicitudesEstadoAnuladoResult = _solicitudesEstadoAnuladoResult.asStateFlow()

    private val _filtradoAnulado = MutableStateFlow(listOf<SolicitudesEstadoItem>())
    val filtradoAnulado = _filtradoAnulado.asStateFlow()

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isMessageError = mutableStateOf("Error: No se pudo cargar los datos")
    val isMessageError: State<String> = _isMessageError

    private val _isLoadingState = MutableStateFlow(true)
    val isLoadingState = _isLoadingState.asStateFlow()

    init{
        getSolicitudEstado()
    }

    private fun getSolicitudEstado(){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getSolicitudEstadoPendienteUC().onSuccess {
                _solicitudesEstadoResult.value = it
                _isLoadingState.value = false
                println(it)
            }.onFailure {
                _isError.value = true
            }
            getSolicitudEstadoPendiente()
            getSolicitudEstadoCotizado()
            getSolicitudEstadoObservado()
            getSolicitudEstadoAnulado()
        }
    }

    fun getSolicitudEstadoPendiente(){
        _solicitudesEstadoPendienteResult.value = _solicitudesEstadoResult.value.filter { userData->
            userData.descripcion_estado.lowercase().equals("pendiente")
        }
    }

    fun getSolicitudEstadoCotizado(){
        _solicitudesEstadoCotizadoResult.value = _solicitudesEstadoResult.value.filter { userData->
            userData.descripcion_estado.lowercase().equals("cotizado")
        }
    }

    fun getSolicitudEstadoObservado(){
        _solicitudesEstadoObservadoResult.value = _solicitudesEstadoResult.value.filter { userData->
            userData.descripcion_estado.lowercase().equals("observado")
        }
    }

    fun getSolicitudEstadoAnulado(){
        _solicitudesEstadoAnuladoResult.value = _solicitudesEstadoResult.value.filter { userData->
            userData.descripcion_estado.lowercase().equals("anulado")
        }
    }

    fun getFiltradoPendiente(search: String){
        _filtradoPendiente.value = _solicitudesEstadoPendienteResult.value.filter { userData->
            userData.nombre_solicitante.lowercase().contains(search.toString().lowercase()) ||
                    userData.departamento.lowercase().contains(search.toString().lowercase()) ||
                    userData.provincia.lowercase().contains(search.toString().lowercase()) ||
                    userData.distrito.lowercase().contains(search.toString().lowercase())
        }
        println("data "+search+" "+_filtradoPendiente.value)
    }

    fun getFiltradoCotizado(search: String){
        _filtradoCotizado.value = _solicitudesEstadoCotizadoResult.value.filter { userData->
            userData.nombre_solicitante.lowercase().contains(search.toString().lowercase()) ||
                    userData.departamento.lowercase().contains(search.toString().lowercase()) ||
                    userData.provincia.lowercase().contains(search.toString().lowercase()) ||
                    userData.distrito.lowercase().contains(search.toString().lowercase())
        }
    }

    fun getFiltradoObservado(search: String){
        _filtradoObservado.value = _solicitudesEstadoObservadoResult.value.filter { userData->
            userData.nombre_solicitante.lowercase().contains(search.toString().lowercase()) ||
                    userData.departamento.lowercase().contains(search.toString().lowercase()) ||
                    userData.provincia.lowercase().contains(search.toString().lowercase()) ||
                    userData.distrito.lowercase().contains(search.toString().lowercase())
        }
    }

    fun getFiltradoAnulado(search: String){
        _filtradoAnulado.value = _solicitudesEstadoAnuladoResult.value.filter { userData->
            userData.nombre_solicitante.lowercase().contains(search.toString().lowercase()) ||
                    userData.departamento.lowercase().contains(search.toString().lowercase()) ||
                    userData.provincia.lowercase().contains(search.toString().lowercase()) ||
                    userData.distrito.lowercase().contains(search.toString().lowercase())
        }
    }

    fun updateIsError(value: Boolean) {
        _isError.value = value
    }

    fun updateIsLoadingState(value: Boolean) {
        _isLoadingState.value = value
    }

}