package br.com.treinamento.atividade

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Funcionario(
    nomeInicial: String = "",
    emailInicial: String = "",
    nivelInicial: Nivel? = null
) {
    var nome by mutableStateOf(nomeInicial)
    var email by mutableStateOf(emailInicial)
    var nivel by mutableStateOf(nivelInicial)
}
enum class Nivel(val descricao: String) {
    ADMINISTRATIVO("Administrativo"),
    FINANCEIRO("Financeiro"),
    GERENCIA("Gerência"),
    SUPORTE("Suporte")
}
