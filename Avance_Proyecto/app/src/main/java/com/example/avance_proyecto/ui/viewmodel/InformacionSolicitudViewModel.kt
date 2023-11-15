package com.example.avance_proyecto.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avance_proyecto.data.model.InformacionSolicitud
import com.example.avance_proyecto.data.model.InformacionSolicitudItem
import com.example.avance_proyecto.data.model.SolicitudesEstado
import com.example.avance_proyecto.data.model.SolicitudesEstadoItem
import com.example.avance_proyecto.domain.InformacionSolicitudUseCase
import com.example.avance_proyecto.domain.SolicitudesEstadoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InformacionSolicitudViewModel: ViewModel() {

    val obtenerDatosUseCase = InformacionSolicitudUseCase()

    private val _informacionSolicitudResult = MutableStateFlow(InformacionSolicitud())
    val informacionSolicitudResult = _informacionSolicitudResult.asStateFlow()

    private val _idSolcitudResult = MutableStateFlow(listOf<InformacionSolicitudItem>())
    val idSolcitudResult = _idSolcitudResult.asStateFlow()


    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    /*init{
        getInformacionSolicitud()
    }*/

    fun getInformacionSolicitud(nuevoId: String){
        viewModelScope.launch(Dispatchers.IO){
            obtenerDatosUseCase.getInformacionSolicitudUC().onSuccess {
                _informacionSolicitudResult.value = it
                FilterIdSolicitud(nuevoId)
                //println("FUNCION DENTRO DE VIEW MODEL:$it")
            }.onFailure {
                _isError.value = true
            }
            _isLoading.value = false
        }
    }

    fun FilterIdSolicitud(nuevoId: String) {
            _idSolcitudResult.value = _informacionSolicitudResult.value.filter { userData ->
                userData.id_solicitud.lowercase().equals(nuevoId)
            }

    }

}