from flask import Blueprint, request, jsonify, make_response
from utils.db import db
from models.predio import Predio
from models.ubigeo import Ubigeo
from models.tipo_predio import TipoPredio

# Crea una nueva instancia de Blueprint
inform_predio = Blueprint("inform_predio", __name__)

@inform_predio.route("/inform_predio", methods=["GET"])
def obtener_informacion_predio():
    if request.method == "GET":
        try:
            response = []
                
                
                
            # Ejecutar la consulta utilizando SQLAlchemy
            data = (db.session.query(
                Predio.id_predio,
                Predio.descripcion,
                Predio.ruc,
                Predio.telefono,
                Predio.correo,
                Predio.direccion,
                TipoPredio.nomre_predio,
                Ubigeo.departamento,
                Ubigeo.provincia,
                Ubigeo.distrito
            ).join(
                Predio,
                TipoPredio.id_tipo_predio == Predio.id_tipo_predio
            ).join(
                Ubigeo,
                Predio.idubigeo == Ubigeo.idubigeo
            ).all()
            )
            # Procesar los resultados de la consulta
            for row in data:
                response.append({
                    "idPredio": row[0],
                    "nombrePredio": row[1],
                    "ruc": row[2],
                    "telefono": row[3],
                    "correo": row[4],
                    "direccion": row[5],
                    "tipoPredio": row[6],
                    "departamento": row[7],
                    "provincia": row[8],
                    "distrito": row[9]
                })
            
            # Devolver los resultados en formato JSON
            return jsonify(response)
        except Exception as e:
            # Manejo de errores: Registra el error o devuelve una respuesta de error adecuada
            return jsonify({"error": str(e)})
    else:
        # Manejo de métodos HTTP no admitidos
        return jsonify({"message": "Método HTTP no admitido"})