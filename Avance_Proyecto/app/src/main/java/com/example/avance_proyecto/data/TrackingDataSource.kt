package com.example.avance_proyecto.data

import com.example.avance_proyecto.R
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitud
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitudItem
import com.example.avance_proyecto.data.model.EstadoSolicitudItems
import com.example.avance_proyecto.model.InformationCard
import com.example.avance_proyecto.model.TrackingCard
import com.example.avance_proyecto.ui.theme.*

object TrackingDataSource {

    val itemCardTracking = listOf(
        TrackingCard(R.drawable.pendiente_image,//Pendiente
            BlueViolet1,
            BlueViolet2,
            BlueViolet3,
            ConteoEstadoSolicitudItem(0,"")
        ),
        TrackingCard(
            R.drawable.cotizado_image,//Cotizado
            LightGreen1,
            LightGreen2,
            LightGreen3,
            ConteoEstadoSolicitudItem(0,"")
        ),
        TrackingCard(
            R.drawable.observado_image,//Observado
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3,
            ConteoEstadoSolicitudItem(0,"")
        ),
        TrackingCard(
            R.drawable.anulado_image,//Anulado
            Beige1,
            Beige2,
            Beige3,
            ConteoEstadoSolicitudItem(0,"")
        )
    )

    val itemCardInformation = listOf(
        InformationCard(
            R.string.num_solicitado,"76"
        ),
        InformationCard(
            R.string.nombre_solicitante,"Fabrizzio Quintana"
        ),
        InformationCard(
            R.string.fecha_solicitud,"14/03/2023"
        ),
        InformationCard(
            R.string.predio,"Valle las Esmeraldas"
        ),
        InformationCard(
            R.string.area_predio,"1000 m2"
        ),
        InformationCard(
            R.string.num_casa_habitacion,"4"
        ),
        InformationCard(
            R.string.servicio_solicitado,"Administación: 4, Seguridad: 3, Limpieza:2, Jardineria: 1"
        ),
        InformationCard(
            R.string.area_comun,"200 m2 total: 2"
        )
    )

}