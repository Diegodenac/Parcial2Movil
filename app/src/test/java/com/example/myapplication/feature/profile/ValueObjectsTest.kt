import org.junit.Assert.*
import org.junit.Test
import com.example.myapplication.feature.profile.domain.model.vo.*

class ValueObjectsTest {

    @Test
    fun `UserName valido`() {
        val userName = UserName.create("Diego-123")
        assertEquals("diego-123", userName.userName)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `UserName invalido`() {
        UserName.create("Inv@lido!!")
    }

    @Test
    fun `Email valido`() {
        val email = Email.create("Test@Gmail.com")
        assertEquals("test@gmail.com", email.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Email invalido`() {
        Email.create("no-es-email")
    }

    @Test
    fun `ProfilePicturePath valido`() {
        val path = ProfilePicturePath.create("profile.jpg")
        assertEquals("profile.jpg", path.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `ProfilePicturePath invalido`() {
        ProfilePicturePath.create("profile.txt")
    }
}
