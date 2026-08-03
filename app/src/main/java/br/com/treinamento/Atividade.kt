package br.com.treinamento

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.treinamento.atividade0107.AtividadeCRUD
import br.com.treinamento.atividade1407.Atividade1407
import br.com.treinamento.atividade0308mvvm.Atividade0308
import br.com.treinamento.atividadepaises.view.PaisScreen
import br.com.treinamento.atividadepersistencia.data.local.AppDatabase
import br.com.treinamento.atividadepersistencia.data.repository.AlunoRepositoryImpl
import br.com.treinamento.atividadepersistencia.view.AlunoScreen
import br.com.treinamento.atividadepersistencia.viewmodel.AlunoViewModel
import br.com.treinamento.atividadepersistencia.viewmodel.AlunoViewModelFactory


// Professor, criei esse arquivo usando gemini mesmo, só para facilitar entrar nas atividades.
@Composable
fun AtividadeMain() {
    var atividadeSelecionada by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (atividadeSelecionada == 0) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Selecione a Atividade",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Button(
                    onClick = { atividadeSelecionada = 1 },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("1. Atividade 01/07 (CRUD Funcionário)")
                }

                Button(
                    onClick = { atividadeSelecionada = 2 },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("2. Atividade 14/07 (Grid Serviços)")
                }

                Button(
                    onClick = { atividadeSelecionada = 3 },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("3. Atividade 03/08 MVVM (Notas Alunos)")
                }

                Button(
                    onClick = { atividadeSelecionada = 4 },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("4. Atividade 03/08 Persistência (Notas Alunos)")
                }

                Button(
                    onClick = { atividadeSelecionada = 5 },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text("5. Atividade 03/08 Países (API/Retrofit)")
                }
            }
        } else {
            when (atividadeSelecionada) {
                1 -> AtividadeCRUD()
                2 -> Atividade1407()
                3 -> Atividade0308()
                4 -> {
                    val context = LocalContext.current
                    val database = AppDatabase.getDatabase(context)
                    val repository = AlunoRepositoryImpl(database.alunoDao())
                    val viewModel: AlunoViewModel = viewModel(factory = AlunoViewModelFactory(repository))
                    AlunoScreen(viewModel = viewModel)
                }
                5 -> PaisScreen()
            }
        }
    }
}
