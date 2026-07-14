package br.com.treinamento.atividade1407.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Mouse
import androidx.compose.material.icons.filled.Square
import androidx.compose.material.icons.filled.WaterDrop
import br.com.treinamento.atividade1407.model.Servico

object ServicoRepository {
    private val servicos = listOf(
        Servico("1", "Mecânico", "Manutenção de automóveis.", Icons.Filled.CarRepair),
        Servico("2", "Azulejista", "Instalação de porcelanatos e cerâmicas.", Icons.Filled.Square),
        Servico("3", "Marceneiro", "Móveis planejados e instalação de portas.", Icons.Default.Agriculture),
        Servico("4", "Eletricista", "Instalação e reparos elétricos.", Icons.Filled.ElectricBolt),
        Servico("5", "Encanador", "Instalação e reparos hidráulicos.", Icons.Filled.WaterDrop),
        Servico("6", "Programador", "Bruxaria e alguns milagres.", Icons.Filled.Mouse),
    )

    fun getAll(): List<Servico> = servicos

    fun getById(id: String): Servico? = servicos.find { it.id == id }
}
