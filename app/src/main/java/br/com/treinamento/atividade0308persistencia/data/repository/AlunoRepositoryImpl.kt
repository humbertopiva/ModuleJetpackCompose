package br.com.treinamento.atividadepersistencia.data.repository

import br.com.treinamento.atividadepersistencia.data.local.AlunoDao
import br.com.treinamento.atividadepersistencia.data.local.AlunoEntity
import br.com.treinamento.atividadepersistencia.model.Aluno
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlunoRepositoryImpl(private val dao: AlunoDao) : AlunoRepository {
    override val listar: Flow<List<Aluno>> = dao.listar().map { lista -> lista.map { it.toModel() } }

    override suspend fun cadastrar(aluno: Aluno) {
        dao.cadastrar(aluno.toEntity())
    }

    override suspend fun editar(aluno: Aluno) {
        dao.editar(aluno.toEntity())
    }

    override suspend fun remover(aluno: Aluno) {
        dao.remover(aluno.toEntity())
    }
}

private fun AlunoEntity.toModel() = Aluno(
    codigo = codigo,
    nome = nome,
    nota1 = nota1,
    nota2 = nota2
)

private fun Aluno.toEntity() = AlunoEntity(
    codigo = codigo,
    nome = nome,
    nota1 = nota1,
    nota2 = nota2
)
