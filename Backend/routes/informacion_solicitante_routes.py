# informacion_solicitante_routes.py
from flask import Blueprint, request, jsonify, make_response
from utils.db import db
from models.persona import Persona
from models.ubigeo import Ubigeo
from models.solicitante import Solicitante
from models.tipo_documento import TipoDocumento
from schemas.informacion_solicitante_schema import InformacionSolicitanteSchema
import traceback

informacion_solicitante_routes = Blueprint("informacion_solicitante_routes", __name__)
informacion_solicitante_schema = InformacionSolicitanteSchema()
informacion_solicitante_schemas = InformacionSolicitanteSchema(many=True)

# informacion_solicitante_routes.py
from flask import Blueprint, request, jsonify, make_response
from utils.db import db
from sqlalchemy import text, func
import traceback
from schemas.informacion_solicitante_schema import InformacionSolicitanteSchema

informacion_solicitante_routes = Blueprint("informacion_solicitante", __name__)
informacion_solicitante_schema = InformacionSolicitanteSchema()


@informacion_solicitante_routes.route("/informacion_solicitante", methods=["POST"])
def create_InformacionSolicitantes():
    try:
        data = request.json
        # Lógica para obtener la información de las tablas y construir un objeto InformacionSolicitante

        # Supongamos que el objeto informacion_solicitante se ha construido correctamente
        new_informacion_solicitante = InformacionSolicitante(**data)

        db.session.add(new_informacion_solicitante)
        db.session.commit()

        result = informacion_solicitante_schema.dump(new_informacion_solicitante)

        data = {
            "message": "Nueva Informacion de Solicitante creada!",
            "status": 201,
            "data": result,
        }

        return make_response(jsonify(data), 201)

    except Exception as e:
        traceback.print_exc()
        data = {"message": "Error en la creación de la Informacion de Solicitante", "status": 500}
        return make_response(jsonify(data), 500)

@informacion_solicitante_routes.route("/informacion_solicitante", methods=["GET"])
def get_InformacionSolicitantes():
    response = []
    data = (
        db.session.query(
            Solicitante.id_solicitante.label("id_solicitante"),
            Persona.nombres.label("Nombre"),
            Persona.apellido_paterno.label("apellido_paterno"),
            Persona.apellido_materno.label("apellido_materno"),
            TipoDocumento.descripcion.label("tipo_documento"),
            Persona.ndocumento.label("numero_documento"),
            Ubigeo.distrito.label("distrito"),
            Ubigeo.provincia.label("provincia"), 
            Ubigeo.departamento.label("departamento"),
            Solicitante.telefono.label("numero_contacto"),
            Persona.direccion.label("direccion"),
            Solicitante.correo.label("correo"),
        )
        .join(Solicitante, Persona.id_persona == Solicitante.id_persona)
        .outerjoin(Ubigeo, Persona.idubigeo == Ubigeo.idubigeo)
        .join(TipoDocumento, TipoDocumento.id_tipo_documento == Persona.id_tipo_documento)
        .all()
    )

    for row in data:
        response.append({
            "idsolicitante": row[0],
            "Nombre": row[1],
            "apellido_paterno": row[2],
            "apellido_materno": row[3],
            "tipo_documento": row[4],
            "numero_documento": row[5],
            "provincia": row[6], 
            "departamento": row[7],
            "distrito": row[8],
            "numero_contacto": row[9],
            "direccion": row[10],
            "correo": row[11],
        })

    if response:
        return jsonify(response)
    else:
        return jsonify({"message": "No se encontraron datos"})

@informacion_solicitante_routes.route("/informacion_solicitante/<int:id>", methods=["PUT"])
def update_InformacionSolicitante(id):
    try:
        informacion_solicitante = InformacionSolicitante.query.get(id)

        if not informacion_solicitante:
            data = {"message": "Informacion de Solicitante no encontrada", "status": 404}
            return make_response(jsonify(data), 404)

        data = request.json
        for key, value in data.items():
            setattr(informacion_solicitante, key, value)

        db.session.commit()

        result = informacion_solicitante_schema.dump(informacion_solicitante)

        data = {"message": "Informacion de Solicitante actualizada", "status": 200, "data": result}

        return make_response(jsonify(data), 200)

    except Exception as e:
        traceback.print_exc()
        data = {"message": "Error al actualizar la Informacion de Solicitante", "status": 500}
        return make_response(jsonify(data), 500)

@informacion_solicitante_routes.route("/informacion_solicitante/<int:id>", methods=["DELETE"])
def delete_InformacionSolicitante(id):
    try:
        informacion_solicitante = InformacionSolicitante.query.get(id)

        if not informacion_solicitante:
            data = {"message": "Informacion de Solicitante no encontrada", "status": 404}
            return make_response(jsonify(data), 404)

        db.session.delete(informacion_solicitante)
        db.session.commit()

        data = {"message": "Informacion de Solicitante eliminada", "status": 200}

        return make_response(jsonify(data), 200)

    except Exception as e:
        traceback.print_exc()
        data = {"message": "Error al eliminar la Informacion de Solicitante", "status": 500}
        return make_response(jsonify(data), 500)