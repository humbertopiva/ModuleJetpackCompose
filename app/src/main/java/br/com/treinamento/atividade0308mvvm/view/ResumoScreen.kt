package br.com.treinamento.atividade0308mvvm.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import br.com.treinamento.atividade0308mvvm.viewmodel.AlunoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumoScreen(
    navController: NavController,
    viewModel: AlunoViewModel
) {

    val listaAlunos by viewModel.alunos.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resumo - Situação dos Alunos") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Default.ArrowBackIosNew, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(15.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Aprovados: ${viewModel.quantidadeAprovados()}",
                    modifier = Modifier.padding(15.dp),
                    fontSize = 18.sp
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Em recuperação: ${viewModel.quantidadeRecuperacao()}",
                    modifier = Modifier.padding(15.dp),
                    fontSize = 18.sp
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Reprovados: ${viewModel.quantidadeReprovados()}",
                    modifier = Modifier.padding(15.dp),
                    fontSize = 18.sp
                )
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Total de alunos: ${listaAlunos.size}",
                    modifier = Modifier.padding(15.dp),
                    fontSize = 18.sp
                )
            }
        }
    }

}
