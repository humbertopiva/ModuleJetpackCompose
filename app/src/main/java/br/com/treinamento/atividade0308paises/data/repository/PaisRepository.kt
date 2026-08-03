package br.com.treinamento.atividadepaises.data.repository

import br.com.treinamento.atividadepaises.data.remote.service.PaisService
import br.com.treinamento.atividadepaises.model.Pais

class PaisRepository(private val service: PaisService) {

    suspend fun getAllPaises(): List<Pais> {
        return service.getAllPaises()
    }

}
