package com.example.avance_proyecto.data.repository

<<<<<<< HEAD
import com.example.apiapp.network.ApiInstance
import com.example.avance_proyecto.data.DataEstadoSolicitud
=======
import com.example.avance_proyecto.data.model.EstadoSolicitud
import com.example.avance_proyecto.network.ApiInstance
>>>>>>> 52079d383da661d2629166dea83e9f3668f7ce62

class EstadoSolicitudRepository {

    private val service = ApiInstance.apiInstance

<<<<<<< HEAD
    suspend fun getEstadoSolicitudRepository() : Result<DataEstadoSolicitud>{
        return try {
            val response = service.listEstadoSolicitud()
            println("TODO BIEN")
            Result.success(response)
        }catch (e: Exception){
            println("TODO MAL")
=======
    suspend fun getEstadoSolicitudRepository() : Result<EstadoSolicitud>{
        return try {
            val response = service.listProductoApiService()
            Result.success(response)
        }catch (e: Exception){
>>>>>>> 52079d383da661d2629166dea83e9f3668f7ce62
            Result.failure(e)
        }
    }

}