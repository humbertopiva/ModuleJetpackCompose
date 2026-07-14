package br.com.treinamento.atividade0107

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.treinamento.atividade0107.componentes.Formulario
import br.com.treinamento.atividade0107.componentes.ListaFuncionario

@Composable
fun AtividadeCRUD() {

    var funcionarioNoForm by remember { mutableStateOf(Funcionario()) }
    val funcionarioList = remember { mutableStateListOf<Funcionario>() }
    var isEdicao by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Formulario(
            funcionarioNoForm,
            onCadastrarClick = {
                if (!funcionarioList.contains(funcionarioNoForm)) {
                    funcionarioList.add(funcionarioNoForm)
                }
                funcionarioNoForm = Funcionario()
                isEdicao = false
            },
            onAtualizarClick = {
                funcionarioNoForm = Funcionario()
                isEdicao = false
            },
            onRemoverClick = {
                funcionarioList.remove(funcionarioNoForm)
                funcionarioNoForm = Funcionario()
                isEdicao = false
            },
            onCancelarClick = {
                funcionarioNoForm = Funcionario()
                isEdicao = false
            },
            isEdicao
        )

        ListaFuncionario(
            funcionarioList,
            onSelecionarClick = {
                funcionario -> funcionarioNoForm = funcionario; isEdicao = true
            }
        )
    }

}