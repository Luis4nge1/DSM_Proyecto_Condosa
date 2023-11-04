package com.example.avance_proyecto.data

import DataEstadosSolicitud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET("/estado_solicitud")
    suspend fun getEstadoSolicitudes(@Url url: String): Response<DataEstadosSolicitud>
}


