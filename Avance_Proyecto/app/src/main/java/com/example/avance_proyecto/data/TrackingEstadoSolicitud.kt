import com.example.avance_proyecto.data.APIService
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TrackingEstadoSolicitud {

    private const val BASE_URL = "http://localhost:5000"  // Reemplaza con tu URL base
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://localhost:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getData(query:String){
        CoroutineScope(Dispatchers.IO).launch{
            val call = getRetrofit().create(APIService::class.java).getEstadoSolicitudes("estado_solicitud")
            val puppies = call.body()
            if(call.isSuccessful){
                //Mostrar data

            }else{

            }
        }
    }

    private fun initRecyclerView(){

    }


    private val apiService: APIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    // Función para obtener datos de la API
    suspend fun getTrackingData(): List<EstadoSolicitud>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getEstadoSolicitudes("estado_solicitud")

                if (response.isSuccessful) {
                    return@withContext response.body()?.data
                } else {
                    // Manejar el error de la API según sea necesario
                    // ...
                }
            } catch (e: Exception) {
                // Manejar excepciones de red u otras excepciones
                // ...
            }

            return@withContext null
        }
    }
}

