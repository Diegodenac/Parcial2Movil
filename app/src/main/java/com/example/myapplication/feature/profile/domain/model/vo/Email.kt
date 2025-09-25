package com.example.myapplication.feature.profile.domain.model.vo

@JvmInline
value class Email private constructor(val value: String) {
    init {
        val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        require(regex.matches(value)) {
            "El email no es válido"
        }
    }

    companion object {
        fun create(raw: String): Email {
            return Email(raw.trim().lowercase())
        }
    }
}
