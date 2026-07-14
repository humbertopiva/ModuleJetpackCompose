package br.com.treinamento.atividade1407

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.treinamento.atividade1407.rotas.NavGraph
import br.com.treinamento.atividade1407.rotas.Rotas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Atividade1407() {

    val navController = rememberNavController()
    val rotaAtual = navController.currentBackStackEntryAsState().value?.destination?.route
    val estadoDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = estadoDrawer,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text("Meu App")
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Atividade 1407") }
                )
            },
            bottomBar = {
                BottomAppBar {
                    NavigationBar {
                        NavigationBarItem(
                            selected = rotaAtual == Rotas.INICIO,
                            onClick = { navController.navigate(Rotas.INICIO) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = null
                                )
                            }
                        )
                        NavigationBarItem(
                            selected = rotaAtual == Rotas.LISTASERVICOS,
                            onClick = { navController.navigate(Rotas.LISTASERVICOS) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Work,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        ) {
        espacamento ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(espacamento)
            )
        }
    }

}