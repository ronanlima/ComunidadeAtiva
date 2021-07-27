package com.github.comunidade.ativa.service

import com.github.comunidade.ativa.model.Evento
import retrofit2.Response
import retrofit2.http.GET

interface EventoService {
    @GET("/api/events")
    suspend fun listaEventos(): Response<List<Evento>>
}