package com.github.comunidade.ativa.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Evento : BaseModel() {
    var date: Long? = null
    var description: String? = null
    var image: String? = null
    var longitude: Long? = null
    var latitude: Long? = null
    var price: Double? = null
    var title: String? = null
    var id: String? = null
    var cupons: Array<String>? = null
}