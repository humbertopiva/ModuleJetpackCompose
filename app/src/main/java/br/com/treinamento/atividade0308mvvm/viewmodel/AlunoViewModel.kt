package br.com.treinamento.atividade0308mvvm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.treinamento.atividade0308mvvm.model.Aluno
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AlunoViewModel : ViewModel() {

    private val _alunos = MutableStateFlow<List<Aluno>>(emptyList())
    val alunos: StateFlow<List<Aluno>> = _alunos.asStateFlow()

    var nomeAluno by mutableStateOf("")
    var nota1Aluno by mutableStateOf("")
    var nota2Aluno by mutableStateOf("")

    private var idAluno = 1

    fun atualizarNomeAluno(valor: String) {
        nomeAluno = valor
    }

    fun atualizarNota1Aluno(valor: String) {
        nota1Aluno = valor
    }

    fun atualizarNota2Aluno(valor: String) {
        nota2Aluno = valor
    }

    fun limparFormulario() {
        nomeAluno = ""
        nota1Aluno = ""
        nota2Aluno = ""
    }

    fun cadastrarAluno() {

        val novoAluno = Aluno(
            id = idAluno++,
            nome = nomeAluno,
            nota1 = nota1Aluno.toDoubleOrNull() ?: 0.0,
            nota2 = nota2Aluno.toDoubleOrNull() ?: 0.0
        )

        _alunos.update { listaAtual -> listaAtual + novoAluno }

        limparFormulario()
    }

    fun removerAluno(id: Int) {
        _alunos.update { listaAtual ->
            listaAtual.filter { aluno -> aluno.id != id }
        }
    }

    fun calcularMedia(aluno: Aluno): Double {
        return (aluno.nota1 + aluno.nota2) / 2
    }

    fun obterSituacao(media: Double): String {
        return when {
            media >= 7 -> "Aprovado"
            media >= 5 -> "Em recuperação"
            else -> "Reprovado"
        }
    }

    fun quantidadeAprovados(): Int {
        return _alunos.value.count { aluno -> calcularMedia(aluno) >= 7 }
    }

    fun quantidadeRecuperacao(): Int {
        return _alunos.value.count { aluno -> calcularMedia(aluno) in 5.0..6.9 }
    }

    fun quantidadeReprovados(): Int {
        return _alunos.value.count { aluno -> calcularMedia(aluno) < 5 }
    }

}
