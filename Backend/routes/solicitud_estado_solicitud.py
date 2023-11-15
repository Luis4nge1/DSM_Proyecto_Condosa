from flask import Blueprint, request, jsonify, make_response
from utils.db import db
from models.solicitud_estado_solicitud import SolicitudEstadoSolicitud
from schemas.solicicitud_estado_solicitud import solicitud_estado_solicitud_schema, solicitud_estado_solicitudes_schema

# Crea una nueva instancia de Blueprint
solicitud_es_soli = Blueprint("solicitud_es_soli", __name__)

@solicitud_es_soli.route('/solicitud_est_solicit', methods=['POST'])
def create_Solicitud_Estado_Solicitud():
    
    id_solicitud_estado_solicitud = request.json.get('id_solicitud_estado_solicitud')
    fecha = request.json.get('fecha')
    id_solicitud = request.json.get('id_solicitud')
    id_estado_solicitud = request.json.get('id_estado_solicitud')
    ind_ultimo = request.json.get('ind_ultimo')

    new_sol_es_sol = SolicitudEstadoSolicitud(id_solicitud_estado_solicitud, fecha, id_solicitud, id_estado_solicitud, ind_ultimo)

    db.session.add(new_sol_es_sol)
    db.session.commit()

    result = solicitud_estado_solicitud_schema.dump(new_sol_es_sol)

    data = {
        'message': 'Nuevo solicitud estado solicitud creado!',
        'status': 201,
        'data': result
    }

    return make_response(jsonify(data), 201)