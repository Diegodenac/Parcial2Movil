package com.example.myapplication.feature.profile

import com.example.myapplication.feature.profile.domain.model.UserProfile
import com.example.myapplication.feature.profile.domain.model.vo.Email
import com.example.myapplication.feature.profile.domain.model.vo.ProfilePicturePath
import com.example.myapplication.feature.profile.domain.model.vo.UserName
import com.example.myapplication.feature.profile.domain.repository.IuserProfileRepository
import com.example.myapplication.feature.profile.domain.usercases.GetProfileUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test

class GetProfileUseCaseTest {

    @Test
    fun `GetProfileUseCase devuelve perfil exitoso`() {
        // Arrange
        val repository = mockk<IuserProfileRepository>()
        val expectedProfile = UserProfile(
            UserName.create("Diego"),
            Email.create("diego@test.com"),
            ProfilePicturePath.create("https://server.com/profile.jpg")
        )

        every { repository.getProfile() } returns Result.success(expectedProfile)

        val useCase = GetProfileUseCase(repository)

        // Act
        val result = useCase.invoke()

        // Assert
        assertTrue(result.isSuccess)
        val profile = result.getOrNull()
        assertEquals("diego", profile?.userName?.userName)
        assertEquals("diego@test.com", profile?.email?.value)
        assertEquals("https://server.com/profile.jpg", profile?.pathPicture?.value)
    }

    @Test
    fun `GetProfileUseCase devuelve error`() {
        // Arrange
        val repository = mockk<IuserProfileRepository>()
        val exception = RuntimeException("No se pudo obtener el perfil")

        every { repository.getProfile() } returns Result.failure(exception)

        val useCase = GetProfileUseCase(repository)

        // Act
        val result = useCase.invoke()

        // Assert
        assertTrue(result.isFailure)
        assertEquals("No se pudo obtener el perfil", result.exceptionOrNull()?.message)
    }
}
