package br.com.treinamento.atividade0308mvvm.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.treinamento.atividade0308mvvm.view.AlunoScreen
import br.com.treinamento.atividade0308mvvm.view.ResumoScreen
import br.com.treinamento.atividade0308mvvm.viewmodel.AlunoViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val viewModel: AlunoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Destinos.ALUNOS
    ) {
        composable(Destinos.ALUNOS) {
            AlunoScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(Destinos.RESUMO) {
            ResumoScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }

}
