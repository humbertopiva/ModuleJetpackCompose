package br.com.treinamento.atividadepaises.data.remote.service

import br.com.treinamento.atividadepaises.data.remote.api.PaisApi
import br.com.treinamento.atividadepaises.model.Pais

class PaisService(private val api: PaisApi) {

    suspend fun getAllPaises(): List<Pais> {

        val retorno = api.getAllPaises()

        return retorno.data.map {
            Pais(
                nome = it.name,
                bandeira = it.flag
            )
        }

    }

}
