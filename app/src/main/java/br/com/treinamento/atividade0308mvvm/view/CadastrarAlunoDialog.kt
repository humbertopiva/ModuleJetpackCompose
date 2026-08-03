package br.com.treinamento.atividade0308mvvm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.treinamento.atividade0308mvvm.viewmodel.AlunoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastrarAlunoDialog(
    viewModel: AlunoViewModel,
    fecharDialog: () -> Unit
) {

    val nome = viewModel.nomeAluno
    val nota1 = viewModel.nota1Aluno
    val nota2 = viewModel.nota2Aluno

    AlertDialog(
        onDismissRequest = {
            viewModel.limparFormulario()
            fecharDialog()
        },
        title = { Text("Cadastro de Alunos") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { viewModel.atualizarNomeAluno(it) },
                    label = { Text("Nome do aluno") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = nota1,
                    onValueChange = { viewModel.atualizarNota1Aluno(it) },
                    label = { Text("Nota 1") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = nota2,
                    onValueChange = { viewModel.atualizarNota2Aluno(it) },
                    label = { Text("Nota 2") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.cadastrarAluno()
                    fecharDialog()
                }
            ) { Text("Cadastrar") }
        },
        dismissButton = {
            Button(
                onClick = {
                    viewModel.limparFormulario()
                    fecharDialog()
                }
            ) { Text("Cancelar") }
        }
    )

}
