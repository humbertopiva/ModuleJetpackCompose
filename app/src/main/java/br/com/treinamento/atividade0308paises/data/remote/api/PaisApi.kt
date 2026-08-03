package br.com.treinamento.atividadepaises.data.remote.api

import br.com.treinamento.atividadepaises.data.remote.dto.PaisListResponse
import retrofit2.http.GET

interface PaisApi {

    @GET("countries/flag/images")
    suspend fun getAllPaises(): PaisListResponse

}
