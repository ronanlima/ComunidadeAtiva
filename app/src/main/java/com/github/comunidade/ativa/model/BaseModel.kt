package com.github.comunidade.ativa.model

import com.google.gson.Gson
import java.io.Serializable

abstract class BaseModel : Serializable {

    open fun toJson(): String? {
        val gson = Gson()
        return gson.toJson(this)
    }

}