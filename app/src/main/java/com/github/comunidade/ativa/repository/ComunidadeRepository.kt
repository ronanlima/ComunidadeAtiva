package com.github.comunidade.ativa.repository

import android.content.Context
import com.github.comunidade.ativa.model.Evento
import com.github.comunidade.ativa.service.EventoService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ComunidadeRepository {
    val baseUrl = "http://5f5a8f24d44d640016169133.mockapi.io"

    @Throws(Exception::class)
    suspend fun listaEventos(context: Context): Response<List<Evento>> {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        val service = retrofit.create(EventoService::class.java)
        val listaEventos = service.listaEventos()
        if (!listaEventos.isSuccessful) {
            throw Exception(listaEventos.errorBody()?.string())
        }
        return listaEventos
    }
}