package br.com.treinamento.atividadepersistencia.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AlunoDao {

    @Query("SELECT * FROM alunos")
    fun listar(): Flow<List<AlunoEntity>>

    @Insert
    suspend fun cadastrar(aluno: AlunoEntity)

    @Update
    suspend fun editar(aluno: AlunoEntity)

    @Delete
    suspend fun remover(aluno: AlunoEntity)

}
