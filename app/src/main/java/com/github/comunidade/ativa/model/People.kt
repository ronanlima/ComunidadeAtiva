package com.github.comunidade.ativa.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class People : BaseModel() {
    var id: String? = null
    var eventId: String? = null
    var name: String? = null
    var picture: String? = null
}