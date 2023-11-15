from flask import Blueprint, request, jsonify, make_response
from utils.db import db
from models.area_comun import AreaComun
from models.predio_area_comun import PredioAreaComun
from models.area_comun import AreaComun
from schemas.area_comun_schema import areaComun_schema, areasComunes_schema

area_comun_routes = Blueprint("area_comun_routes", __name__)


@area_comun_routes.route("/areaComun", methods=["POST"])
def create_AreaComun():
    descripcion = request.json.get("descripcion")

    new_area_comun = AreaComun(descripcion)

    db.session.add(new_area_comun)
    db.session.commit()

    result = areaComun_schema.dump(new_area_comun)

    data = {"message": "Nueva Área Común creada!", "status": 201, "data": result}

    return make_response(jsonify(data), 201)


@area_comun_routes.route("/area_comun", methods=["GET"])
def get_AreaComun():
    response = []
    data = (
        db.session.query(
            PredioAreaComun.id_predio.label("id_predio"), 
            AreaComun.descripcion.label("descripcion"), 
            PredioAreaComun.area.label("area")
        )
        .join(AreaComun, PredioAreaComun.id_area_comun == AreaComun.id_area_comun)
        .order_by(PredioAreaComun.id_predio)
        .all()
    )
    for row in data:
        response.append({
            "id_predio": row[0],
            "descripcion": row[1],
            "area": row[2],
        })
        
    if response:
        return jsonify(response)
    else:
        return jsonify({"message": "No se encontraron datos"})

@area_comun_routes.route("/areaComun/<int:id_areaComun>", methods=["PATCH"])
def update_AreaComun(id_areaComun):
    areaComun = AreaComun.query.get(id_areaComun)

    if areaComun:
        descripcion = request.json.get("descripcion")

        if descripcion:
            areaComun.descripcion = descripcion

        db.session.commit()
        result = areaComun_schema.dump(areaComun)

        data = {
            "message": "Información de Área Común editada!",
            "status": 200,
            "data": result,
        }

        return make_response(jsonify(data), 200)

    data = {"message": "ID de Área Común inválido!", "status": 404}

    return make_response(jsonify(data), 404)


@area_comun_routes.route("/areaComun/<int:id_areaComun>", methods=["DELETE"])
def delete_AreaComun(id_areaComun):
    areaComun = AreaComun.query.get(id_areaComun)

    if areaComun:
        db.session.delete(areaComun)
        db.session.commit()

        data = {"message": "Área Común eliminada!", "status": 204}

        return make_response(jsonify(data), 204)

    data = {"message": "ID de Área Común inválido!", "status": 404}

    return make_response(jsonify(data), 404)
