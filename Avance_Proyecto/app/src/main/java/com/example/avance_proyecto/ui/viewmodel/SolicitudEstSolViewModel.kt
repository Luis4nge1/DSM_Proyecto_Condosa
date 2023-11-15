package com.example.avance_proyecto.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.avance_proyecto.data.model.SolicitudEstadoSolDTO
import com.example.avance_proyecto.domain.SolEstadoSolicitudUseCase

class SolicitudEstSolViewModel: ViewModel() {

    val datosUseCase = SolEstadoSolicitudUseCase()

    suspend fun insertProduct(sesDTO : SolicitudEstadoSolDTO){
        datosUseCase.insertSolEstadoSolicitud(sesDTO).onSuccess {
            println("PRODUCTO AGREGADO CORRECTAMENTE")
        }.onFailure {
            println("HUBO UN ERROR AL AGREGAR EL PRODUCTO")
        }
    }

}