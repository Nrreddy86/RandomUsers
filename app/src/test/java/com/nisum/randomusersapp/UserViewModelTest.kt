import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nisum.randomusersapp.model.ApiResult
import com.nisum.randomusersapp.model.Dob
import com.nisum.randomusersapp.model.Location
import com.nisum.randomusersapp.model.Login
import com.nisum.randomusersapp.model.Name
import com.nisum.randomusersapp.model.Picture
import com.nisum.randomusersapp.model.Street
import com.nisum.randomusersapp.model.User
import com.nisum.randomusersapp.model.UserRepository
import com.nisum.randomusersapp.viewModel.UserViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: UserViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set the Main dispatcher for tests
        userRepository = mockk()
        viewModel = UserViewModel(userRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the Main dispatcher after tests
    }

    @Test
    fun `fetchUsers emits loading and success states`() = runTest {

        val mockUsers = listOf(
            User(
                gender = "male",
                name = Name("Mr", "Rama", "Reddy"),
                location = Location(
                    street = Street(1 - 59, "Street"),
                    city = "Hyderabad",
                    state = "Telangana",
                    country = "India",
                    postcode = "123456"
                ),
                city = "Hyderabad",
                state = "Telangana",
                country = "India",
                postcode = "534265",
                email = "abc@gmail.com",
                dob = Dob(date = "05-05-1995", age = 35),
                phone = "",
                picture = Picture(large = "", medium = "", thumbnail = ""),
                login = Login("")
            )
        )
        coEvery { userRepository.fetchUsers(2) } returns ApiResult.Success(mockUsers)

        // Act
        viewModel.fetchUsers(2)

        // Assert
        assertTrue(viewModel.usersState.value is ApiResult.Loading)
        assertEquals(mockUsers, ApiResult.Success(mockUsers).data)

    }

    @Test
    fun `fetchUsers emits loading and error states`() = runTest {
        // Arrange
        val mockError = "Unable to fetch users"
        coEvery { userRepository.fetchUsers(2) } returns ApiResult.Error(mockError)

        // Act
        viewModel.fetchUsers(2)

        // Assert
        assertTrue(viewModel.usersState.value is ApiResult.Loading)
        assertEquals(mockError, ApiResult.Error(mockError).message)
    }

    @Test
    fun `fetchUsers handles repository exception gracefully`() = runTest {
        // Arrange
        val errorMessage = "Network error"
        coEvery { userRepository.fetchUsers(2) } throws RuntimeException(errorMessage)

        // Act
        viewModel.fetchUsers(2)

        // Assert
        assertTrue(viewModel.usersState.value is ApiResult.Loading)
        assertEquals(errorMessage, ApiResult.Error(errorMessage).message)
    }

}

