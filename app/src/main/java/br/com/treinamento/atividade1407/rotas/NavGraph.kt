package br.com.treinamento.atividade1407.rotas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import br.com.treinamento.atividade1407.repository.ServicoRepository
import br.com.treinamento.atividade1407.telas.ListaServicos
import br.com.treinamento.atividade1407.telas.Servico
import br.com.treinamento.atividade1407.telas.Inicio

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Rotas.INICIO,
        modifier = modifier
    ) {
        composable(Rotas.INICIO)        { Inicio()        }
        composable(Rotas.LISTASERVICOS) { 
            ListaServicos(navController = navController) 
        }
        composable<Rotas.DetalhesServico> { backStackEntry ->
            val route = backStackEntry.toRoute<Rotas.DetalhesServico>()
            val servico = ServicoRepository.getById(route.id)
            
            if (servico != null) {
                Servico(servico)
            }
        }
    }
}
