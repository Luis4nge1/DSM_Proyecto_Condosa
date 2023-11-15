from utils.ma import ma
from models.solicitud_estado_solicitud import SolicitudEstadoSolicitud

class SolicitudEstSoliSchema(ma.SQLAlchemySchema):
    class Meta:
        model = SolicitudEstadoSolicitud
        fields = ('id_solicitud_estado_solicitud', 'fecha','id_solicitud','id_estado_solicitud',
                  'ind_ultimo')

solicitud_estado_solicitud_schema = SolicitudEstSoliSchema()
solicitud_estado_solicitudes_schema = SolicitudEstSoliSchema(many=True)