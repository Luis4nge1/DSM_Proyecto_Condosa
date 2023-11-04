<<<<<<< HEAD
package com.example.apiapp.network
=======
package com.example.avance_proyecto.network
>>>>>>> 52079d383da661d2629166dea83e9f3668f7ce62

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

<<<<<<< HEAD
//https://api.themoviedb.org/3/
=======
>>>>>>> 52079d383da661d2629166dea83e9f3668f7ce62
object ApiInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
<<<<<<< HEAD
            .baseUrl("http://localhost:5000/")
=======
            .baseUrl("tu_direccion")
>>>>>>> 52079d383da661d2629166dea83e9f3668f7ce62
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInstance by lazy{
        retrofit.create(ApiService::class.java)
    }

}