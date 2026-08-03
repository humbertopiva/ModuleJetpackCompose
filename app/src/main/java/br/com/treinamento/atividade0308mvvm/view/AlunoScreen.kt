package br.com.treinamento.atividade0308mvvm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import br.com.treinamento.atividade0308mvvm.navigation.Destinos
import br.com.treinamento.atividade0308mvvm.viewmodel.AlunoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlunoScreen(
    navController: NavController,
    viewModel: AlunoViewModel
) {

    var mostrarDialog by remember { mutableStateOf(false) }

    val listaAlunos by viewModel.alunos.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gerenciar Notas") },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Destinos.RESUMO) }
                    ) {
                        Icon(Icons.Default.BarChart, null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarDialog = true }
            ) {
                Icon(Icons.Default.Add, null)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(listaAlunos) { aluno ->

                val media = viewModel.calcularMedia(aluno)
                val situacao = viewModel.obterSituacao(media)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Nome: ${aluno.nome}")
                            Text("Nota 1: ${aluno.nota1} | Nota 2: ${aluno.nota2}")
                            Text("Média: $media")
                            Text("Situação: $situacao")
                        }

                        IconButton(
                            onClick = { viewModel.removerAluno(aluno.id) }
                        ) {
                            Icon(Icons.Default.Delete, null)
                        }
                    }
                }
            }
        }
    }

    if (mostrarDialog) {
        CadastrarAlunoDialog(
            viewModel = viewModel,
            fecharDialog = { mostrarDialog = false }
        )
    }

}
