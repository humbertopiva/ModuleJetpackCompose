package br.com.treinamento.atividadepaises.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.treinamento.atividadepaises.data.remote.RetrofitInstance
import br.com.treinamento.atividadepaises.data.remote.service.PaisService
import br.com.treinamento.atividadepaises.data.repository.PaisRepository
import br.com.treinamento.atividadepaises.model.Pais
import kotlinx.coroutines.launch

class PaisViewModel : ViewModel() {

    private val _service = PaisService(RetrofitInstance.api)

    private val _repository = PaisRepository(_service)

    var paises by mutableStateOf<List<Pais>>(emptyList())

    var isLoading by mutableStateOf<Boolean>(false)

    init {
        loadPaises()
    }

    private fun loadPaises() {

        viewModelScope.launch {

            try {
                isLoading = true
                paises = _repository.getAllPaises()
            } finally {
                isLoading = false
            }

        }

    }

}
