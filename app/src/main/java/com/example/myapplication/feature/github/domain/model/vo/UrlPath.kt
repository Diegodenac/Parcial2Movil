package com.example.myapplication.feature.github.domain.model.vo

@JvmInline
value class UrlPath private constructor(val url: String){
    init {
        val regex = Regex("^https://[^s]+(\\.png|\\.jpg|\\.jpeg|\\.gif|\\.webp)?$")
        require(regex.matches(url)) {
            "La URL de la foto de perfil no es válida"
        }
    }
    companion object {
        fun create(raw: String): UrlPath {
            return UrlPath(raw.trim())
        }
    }
}