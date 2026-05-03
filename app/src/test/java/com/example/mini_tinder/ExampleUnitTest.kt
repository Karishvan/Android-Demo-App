package com.example.mini_tinder

import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import io.mockk.mockk

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var apiService: ApiService
    private lateinit var viewModel: UserViewModel
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        apiService = mockk()
    }
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun `fetchUsers updates current user on success`() {
        val mockUsers = listOf(
            UserData(
                name = Name("John", "Doe"),
                email = "john@examplpe.com",
                gender = "male",
                location = Location("Toronto", "Ontario", "Canada"),
                picture = Picture("example1.com/...")
            ),
            UserData(
                name = Name("Jane", "Smith"),
                email = "jane@examplpe.com",
                gender = "female",
                location = Location("Vancouver", "British Columbia", "Canada"),
                picture = Picture("example2.com/...")
            )
        )
        val mockResponse = UserResponse(results = mockUsers)
        coEvery { apiService.getUsers(any()) } returns mockResponse

        viewModel = UserViewModel(apiService)
        assertEquals("John", viewModel.currentUser.value?.name?.first)
    }

    @Test
    fun `clicking on pass goes to next user`() {
        val mockUsers = listOf(
            UserData(
                name = Name("John", "Doe"),
                email = "john@examplpe.com",
                gender = "male",
                location = Location("Toronto", "Ontario", "Canada"),
                picture = Picture("example1.com/...")
            ),
            UserData(
                name = Name("Jane", "Smith"),
                email = "jane@examplpe.com",
                gender = "female",
                location = Location("Vancouver", "British Columbia", "Canada"),
                picture = Picture("example2.com/...")
            )
        )
        val mockResponse = UserResponse(results = mockUsers)
        coEvery { apiService.getUsers(any()) } returns mockResponse

        viewModel = UserViewModel(apiService)
        viewModel.showNextUser()
        assertEquals("Jane", viewModel.currentUser.value?.name?.first)
    }
}