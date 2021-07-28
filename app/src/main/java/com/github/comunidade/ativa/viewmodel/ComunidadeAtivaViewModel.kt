package com.github.comunidade.ativa.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.comunidade.ativa.model.Evento
import com.github.comunidade.ativa.repository.ComunidadeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComunidadeAtivaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ComunidadeRepository by lazy {
        ComunidadeRepository()
    }

    private val eventoResponse = MutableLiveData<Result<List<Evento>>>()
    val eventoObserver: LiveData<Result<List<Evento>>>
        get() = eventoResponse

    open val eventoSelecionadoResponse = MutableLiveData<Evento>()
    val eventoSelecionadoObserver: LiveData<Evento>
        get() = eventoSelecionadoResponse

    fun consultaEventos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val eventos = repository.listaEventos()
                eventoResponse.postValue(Result.success(eventos.body().orEmpty()))
            } catch (e: Exception) {
                eventoResponse.postValue(Result.failure(Throwable(e.message)))
            }
        }
    }
}