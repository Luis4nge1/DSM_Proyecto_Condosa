package com.example.avance_proyecto.data

import com.example.avance_proyecto.R
import com.example.avance_proyecto.model.InformationCard
import com.example.avance_proyecto.model.TrackingCard
import com.example.avance_proyecto.ui.theme.*

object TrackingDataSource {
    val itemCardTracking = listOf(
        TrackingCard(
            R.string.pendiente, 58, R.drawable.pendiente_image,
            BlueViolet1,
            BlueViolet2,
            BlueViolet3
        ),
        TrackingCard
            (R.string.cotizado, 68, R.drawable.cotizado_image,
            LightGreen1,
            LightGreen2,
            LightGreen3
                    ),
        TrackingCard(
            R.string.observado, 15, R.drawable.observado_image,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        TrackingCard(
            R.string.anulado, 58, R.drawable.anulado_image,
            Beige1,
            Beige2,
            Beige3
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