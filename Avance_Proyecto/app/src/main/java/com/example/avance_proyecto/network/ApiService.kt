package com.example.avance_proyecto.network

import com.example.avance_proyecto.data.model.EstadoSolicitud
import retrofit2.http.GET

interface ApiService {

    @GET("/estadosolicitud")
    suspend fun listProductoApiService( ): EstadoSolicitud

}