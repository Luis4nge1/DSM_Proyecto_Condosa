package com.example.avance_proyecto.data.repository

import com.example.avance_proyecto.data.model.EstadoSolicitud
import com.example.avance_proyecto.network.ApiInstance

class EstadoSolicitudRepository {

    private val service = ApiInstance.apiInstance

    suspend fun getEstadoSolicitudRepository() : Result<EstadoSolicitud>{
        return try {
            val response = service.listProductoApiService()
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}