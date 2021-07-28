package com.github.comunidade.ativa.interfaces

import com.github.comunidade.ativa.model.Evento

interface EventoListener {
    fun onClick(evento: Evento)
}