package br.com.treinamento.atividade.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.treinamento.atividade.Funcionario
import br.com.treinamento.atividade.Nivel

@Composable
fun ListaFuncionario(lista: MutableList<Funcionario>, onSelecionarClick: (funcionario: Funcionario) -> Unit) {


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        itemsIndexed(lista) {index, funcionario ->

            val backgroundColor = when (index % 3) {
                0 -> Color(0xFFE3F2FD)
                1 -> Color(0xFFE8F5E9)
                else -> Color(0xFFFFFDE7)
            }

            val icon = when (funcionario.nivel) {
                Nivel.ADMINISTRATIVO -> Icons.Filled.Work
                Nivel.FINANCEIRO -> Icons.Filled.Payments
                Nivel.GERENCIA -> Icons.Filled.Badge
                Nivel.SUPORTE -> Icons.Filled.SupportAgent
                null -> Icons.Filled.Person
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = backgroundColor),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column() {
                        Icon(
                            icon,
                            contentDescription = null
                        )
                    }

                    Column() {
                        Text(
                            text = "Nome: ${funcionario.nome}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "E-mail: ${funcionario.email}",
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Nível: ${funcionario.nivel.toString()}",
                            fontSize = 12.sp
                        )

                        Button(
                            onClick = {
                                onSelecionarClick(funcionario)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF377AFF)
                            )
                        ) {
                            Text(
                                text = "Selecionar",
                                color = Color.White,
                            )
                        }
                    }

                }
            }


        }


    }
}


