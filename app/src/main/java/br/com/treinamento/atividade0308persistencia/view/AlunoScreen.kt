package br.com.treinamento.atividadepersistencia.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.treinamento.atividadepersistencia.model.Aluno
import br.com.treinamento.atividadepersistencia.viewmodel.AlunoViewModel

@Composable
fun AlunoScreen(viewModel: AlunoViewModel) {

    val listaAlunos by viewModel.alunos.collectAsState()

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = if (uiState.emEdicao) "Editar Aluno" else "Cadastrar Aluno",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.nome,
            onValueChange = { valor -> viewModel.atualizarCampo { it.copy(nome = valor) } },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.nota1,
            onValueChange = { valor -> viewModel.atualizarCampo { it.copy(nota1 = valor) } },
            label = { Text("Nota 1") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.nota2,
            onValueChange = { valor -> viewModel.atualizarCampo { it.copy(nota2 = valor) } },
            label = { Text("Nota 2") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Row {
            Button(
                onClick = { viewModel.salvar() },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (uiState.emEdicao) "Editar" else "Cadastrar")
            }

            if (uiState.emEdicao) {
                Spacer(Modifier.width(8.dp))
                OutlinedButton(
                    onClick = { viewModel.limparFormulario() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancelar")
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        LazyColumn {
            items(listaAlunos) { aluno ->

                val media = viewModel.calcularMedia(aluno)
                val situacao = viewModel.obterSituacao(media)

                AlunoCard(
                    aluno = aluno,
                    media = media,
                    situacao = situacao,
                    onClick = { viewModel.iniciarEdicao(aluno) },
                    onDelete = { viewModel.remover(aluno) }
                )
            }
        }
    }
}

@Composable
fun AlunoCard(
    aluno: Aluno,
    media: Double,
    situacao: String,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(aluno.nome, style = MaterialTheme.typography.bodyLarge)
                Text("Média: $media", style = MaterialTheme.typography.bodySmall)
                Text("Situação: $situacao", style = MaterialTheme.typography.bodySmall)
            }
            Row {
                IconButton(onClick = onClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Excluir")
                }
            }
        }
    }
}
