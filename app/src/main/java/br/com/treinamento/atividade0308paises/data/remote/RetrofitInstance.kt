package br.com.treinamento.atividadepaises.data.remote

import br.com.treinamento.atividadepaises.data.remote.api.PaisApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://countriesnow.space/api/v0.1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()

    val api: PaisApi = retrofit.create(PaisApi::class.java)
}
