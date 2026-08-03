package br.com.treinamento.atividadepaises.data.remote.dto

data class PaisListResponse(
    val error: Boolean,
    val msg: String,
    val data: List<PaisResult>
)

data class PaisResult(
    val name: String,
    val flag: String
)
