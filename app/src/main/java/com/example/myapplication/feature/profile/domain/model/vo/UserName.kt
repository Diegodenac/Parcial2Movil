package com.example.myapplication.feature.profile.domain.model.vo

@JvmInline
value class UserName private constructor(val userName: String){
    init{
        val regex = Regex("^(?!-)(?!.*--)(?!.*\\\\s)[a-zA-Z0-9-]{1,39}(?<!-)$")
        require(regex.matches(userName)){
            "El nickname no es valido"
        }
    }

    companion object{
        fun create(raw:String): UserName {
            return UserName(raw.lowercase().trim())
        }
    }
}