package com.example.myapplication.feature.profile.domain.model.vo

@JvmInline
value class ProfilePicturePath private constructor(val value: String) {
    init {
        require(value.isNotBlank()) {
            "La ruta de la foto no puede estar vacía"
        }
        require(value.endsWith(".png") || value.endsWith(".jpg") || value.endsWith(".jpeg")) {
            "La ruta debe terminar en .png, .jpg o .jpeg"
        }
    }

    companion object {
        fun create(raw: String): ProfilePicturePath {
            return ProfilePicturePath(raw.trim())
        }
    }
}
