package com.example.myapplication.feature.github.domain.model.vo

@JvmInline
value class Nickname private constructor(val nickname: String) {
    init{
        val regex = Regex("^(?!-)(?!.*--)(?!.*\\\\s)[a-zA-Z0-9-]{1,39}(?<!-)$")
        require(regex.matches(nickname)){
            "El nickname no es valido"
        }
    }

    companion object{
        fun create(raw:String): Nickname{
            return Nickname(raw.lowercase().trim())
        }
    }
}