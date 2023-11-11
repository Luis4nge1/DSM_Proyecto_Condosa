package com.example.avance_proyecto.network
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitud
import com.example.avance_proyecto.data.model.EstadoSolicitud
import com.example.avance_proyecto.data.model.SolicitudesEstado
import retrofit2.http.GET


interface ApiService {

    @GET("/estado_solicitud")
    suspend fun listEstadoSolicitudApiService( ): EstadoSolicitud

    @GET("/conteo_estado_solicitud")
    suspend fun listConteoEstadoSolicitudApiService( ): ConteoEstadoSolicitud

    @GET("/solicitudes_estado")
    suspend fun listSolcitudesEstadoApiService( ): SolicitudesEstado

}
