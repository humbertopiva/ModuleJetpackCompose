package br.com.treinamento.atividade1407.model

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class Servico(
    val id: String,
    val tipo: String,
    val descricao: String,
    val Icon: ImageVector
)
