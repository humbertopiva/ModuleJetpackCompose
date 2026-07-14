package br.com.treinamento.atividade1407.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.treinamento.atividade1407.rotas.Rotas

@Composable
fun Inicio() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            Rotas.INICIO,
            fontSize = 32.sp
        )

        Spacer(
            modifier = Modifier.size(16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "Os melhores prestadores de serviço ao seu alcance",
                fontSize = 16.sp
            )
        }
    }
}