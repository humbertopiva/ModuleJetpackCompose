package br.com.treinamento.atividadepersistencia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.treinamento.atividadepersistencia.data.repository.AlunoRepository
import br.com.treinamento.atividadepersistencia.model.Aluno
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AlunoUiState(
    val codigo: Int = 0,
    val nome: String = "",
    val nota1: String = "",
    val nota2: String = "",
    val emEdicao: Boolean = false
)

class AlunoViewModel(private val repository: AlunoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AlunoUiState())
    val uiState: StateFlow<AlunoUiState> = _uiState

    fun atualizarCampo(transformacao: (AlunoUiState) -> AlunoUiState) {
        _uiState.update(transformacao)
    }

    fun iniciarEdicao(aluno: Aluno) {
        _uiState.value = AlunoUiState(
            codigo = aluno.codigo,
            nome = aluno.nome,
            nota1 = aluno.nota1.toString(),
            nota2 = aluno.nota2.toString(),
            emEdicao = true
        )
    }

    fun limparFormulario() {
        _uiState.value = AlunoUiState()
    }

    val alunos: StateFlow<List<Aluno>> = repository.listar
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun salvar() {
        val estado = _uiState.value
        if (estado.nome.isBlank()) return

        viewModelScope.launch {
            val aluno = Aluno(
                codigo = estado.codigo,
                nome = estado.nome,
                nota1 = estado.nota1.toDoubleOrNull() ?: 0.0,
                nota2 = estado.nota2.toDoubleOrNull() ?: 0.0
            )
            if (estado.emEdicao) {
                repository.editar(aluno)
            } else {
                repository.cadastrar(aluno)
            }
            limparFormulario()
        }
    }

    fun remover(aluno: Aluno) {
        viewModelScope.launch {
            repository.remover(aluno)
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

}

class AlunoViewModelFactory(private val repository: AlunoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlunoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AlunoViewModel(repository) as T
        }
        throw IllegalArgumentException("Falha ao disponibilizar o repository")
    }
}
