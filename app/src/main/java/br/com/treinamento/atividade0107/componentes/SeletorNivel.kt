package br.com.treinamento.atividade0107.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.treinamento.atividade0107.Nivel

@Composable
fun NivelSelector(
    nivel: Nivel?,
    onNivelSelected: (Nivel) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = nivel?.descricao ?: "Selecione um cargo",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            label = { Text("Nível/Cargo") },
            trailingIcon = {
                val icon = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(icon, contentDescription = null)
                }
            }
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = !expanded }
        )

        DropdownMenu(
            expanded = expanded,
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = { expanded = false }
        ) {
            Nivel.entries.forEach { n ->
                DropdownMenuItem(
                    text = { Text(n.descricao) },
                    onClick = {
                        onNivelSelected(n)
                        expanded = false
                    },
                )
            }
        }
    }
}
