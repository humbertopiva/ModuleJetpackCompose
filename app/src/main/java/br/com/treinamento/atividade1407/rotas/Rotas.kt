package br.com.treinamento.atividade1407.rotas

import kotlinx.serialization.Serializable

object Rotas {
    const val INICIO = "Inicio"
    const val LISTASERVICOS = "Lista Serviços"
    const val SERVICO = "Serviço"

    @Serializable
    data class DetalhesServico(val id: String)
}
