package com.example.avance_proyecto.data.repository

import com.example.avance_proyecto.data.model.SolicitudEstadoSolDTO
import com.example.avance_proyecto.data.model.SolicitudEstadoSolicitud
import com.example.avance_proyecto.network.ApiInstance

class SolEstadoSolicitudRepository {

    private val service = ApiInstance.apiInstance

    suspend fun insertSolEstadoSolicitudRepository(ses : SolicitudEstadoSolDTO): Result<SolicitudEstadoSolicitud> {
        return try {
            val response = service.insertInformacionSolicitanteApiService(ses)
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }

    }

}