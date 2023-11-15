package com.example.avance_proyecto.data.model

data class InformacionSolicitanteItem(
    val Nombre: String="",
    val apellido_materno: String="",
    val apellido_paterno: String="",
    val correo: String="",
    val departamento: String="",
    val direccion: String="",
    val distrito: String="",
    val idsolicitante: String="",
    val numero_contacto: Int=0,
    val numero_documento: String="",
    val provincia: String="",
    val tipo_documento: String =""
)