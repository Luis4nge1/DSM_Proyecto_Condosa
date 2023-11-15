from utils.ma import ma
from models.area_comun import AreaComun

class AreaComunSchema(ma.SQLAlchemySchema):
    class Meta:
        fields = ('id_predio', 'descripcion','area')

areaComun_schema = AreaComunSchema()
areasComunes_schema = AreaComunSchema(many=True)