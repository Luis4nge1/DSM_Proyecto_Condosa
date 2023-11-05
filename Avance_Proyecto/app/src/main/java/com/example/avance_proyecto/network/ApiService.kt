package com.example.avance_proyecto.network

import com.example.avance_proyecto.data.model.ConteoEstadoSolicitud
import com.example.avance_proyecto.data.model.EstadoSolicitud
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("/estado_solicitud")
    suspend fun listEstadoSolicitudApiService( ): EstadoSolicitud

    @GET("/conteo_estado_solicitud")
    suspend fun listConteoEstadoSolicitudApiService( ): ConteoEstadoSolicitud

}