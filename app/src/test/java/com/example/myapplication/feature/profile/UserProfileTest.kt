package com.example.myapplication.feature.profile

import org.junit.Assert.*
import org.junit.Test
import com.example.myapplication.feature.profile.domain.model.*
import com.example.myapplication.feature.profile.domain.model.vo.*

class UserProfileTest {

    @Test
    fun `Crear UserProfile exitosamente`() {
        val user = UserProfile(
            UserName.create("Diego123"),
            Email.create("diego@test.com"),
            ProfilePicturePath.create("https://pic.png")
        )

        assertEquals("diego123", user.userName.userName)
        assertEquals("diego@test.com", user.email.value)
        assertEquals("https://pic.png", user.pathPicture.value)
    }
}
