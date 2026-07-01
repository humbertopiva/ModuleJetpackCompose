package br.com.treinamento.atividade.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.treinamento.atividade.Funcionario

@Composable
fun Formulario(
    form: Funcionario,
    onCadastrarClick: () -> Unit,
    onAtualizarClick: () -> Unit,
    onRemoverClick: () -> Unit,
    onCancelarClick: () -> Unit,
    isEdicao: Boolean
) {
    var draftNome by remember(form) { mutableStateOf(form.nome) }
    var draftEmail by remember(form) { mutableStateOf(form.email) }
    var draftNivel by remember(form) { mutableStateOf(form.nivel) }

    val aplicarMudancas = {
        form.nome = draftNome
        form.email = draftEmail
        form.nivel = draftNivel
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .safeDrawingPadding()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = draftNome,
            onValueChange = { draftNome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = draftEmail,
            onValueChange = { draftEmail = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        NivelSelector(
            nivel = draftNivel,
            onNivelSelected = { draftNivel = it }
        )

        if (isEdicao) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        aplicarMudancas()
                        onAtualizarClick()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("Atualizar", color = Color.White)
                }

                Button(
                    onClick = onRemoverClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                ) {
                    Text("Excluir", color = Color.White)
                }
            }

            Button(
                onClick = onCancelarClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Cancelar", color = Color.White)
            }
        } else {
            Button(
                onClick = {
                    aplicarMudancas()
                    onCadastrarClick()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF377AFF))
            ) {
                Text("Cadastrar", color = Color.White)
            }
        }
    }
}
