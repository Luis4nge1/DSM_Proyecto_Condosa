package com.example.avance_proyecto.data.repository

import com.example.avance_proyecto.data.model.SolicitudesEstado
import com.example.avance_proyecto.network.ApiInstance

class SolicitudesEstadoRepository {

    private val service = ApiInstance.apiInstance

    suspend fun getSolicitudesEstadoRepository() : Result<SolicitudesEstado>{
        return try {
            val response = service.listSolcitudesEstadoApiService()
            Result.success(response)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

}