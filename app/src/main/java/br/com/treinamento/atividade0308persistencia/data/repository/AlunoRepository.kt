package br.com.treinamento.atividadepersistencia.data.repository

import br.com.treinamento.atividadepersistencia.model.Aluno
import kotlinx.coroutines.flow.Flow

interface AlunoRepository {

    val listar: Flow<List<Aluno>>
    suspend fun cadastrar(aluno: Aluno)
    suspend fun editar(aluno: Aluno)
    suspend fun remover(aluno: Aluno)

}
