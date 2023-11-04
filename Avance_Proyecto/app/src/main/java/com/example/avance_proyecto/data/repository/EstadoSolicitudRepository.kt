package com.example.avance_proyecto.data.repository

import com.example.apiapp.network.ApiInstance
import com.example.avance_proyecto.data.DataEstadoSolicitud

class EstadoSolicitudRepository {

    private val service = ApiInstance.apiInstance

    suspend fun getEstadoSolicitudRepository() : Result<DataEstadoSolicitud>{
        return try {
            val response = service.listEstadoSolicitud()
            println("TODO BIEN")
            Result.success(response)
        }catch (e: Exception){
            println("TODO MAL")
            Result.failure(e)
        }
    }

}