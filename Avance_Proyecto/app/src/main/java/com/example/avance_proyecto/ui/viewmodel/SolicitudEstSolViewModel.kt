package com.example.avance_proyecto.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avance_proyecto.data.model.SolicitudEstadoSolDTO
import com.example.avance_proyecto.domain.SolEstadoSolicitudUseCase
import kotlinx.coroutines.launch

class SolicitudEstSolViewModel: ViewModel() {

    val datosUseCase = SolEstadoSolicitudUseCase()

    fun insertProduct(sesDTO : SolicitudEstadoSolDTO){
        viewModelScope.launch {
            datosUseCase.insertSolEstadoSolicitud(sesDTO).onSuccess {
                println("PRODUCTO AGREGADO CORRECTAMENTE")
            }.onFailure {
                println("HUBO UN ERROR AL AGREGAR EL PRODUCTO")
            }
        }

    }

}