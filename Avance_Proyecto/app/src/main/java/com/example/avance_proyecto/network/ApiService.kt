package com.example.apiapp.network

import com.example.avance_proyecto.data.DataEstadoSolicitud
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

//https://crudcrud.com/api/b599b124a5cb4952bad2820f33d7b419
//https://crudcrud.com/api/eccff00b25a34f8286f02eb171e5288d
interface ApiService {
    @GET("/estado_solicitud")
    suspend fun listEstadoSolicitud( ): DataEstadoSolicitud

}