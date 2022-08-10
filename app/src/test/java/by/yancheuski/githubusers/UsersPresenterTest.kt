package by.yancheuski.githubusers

import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo
import by.yancheuski.githubusers.presenter.UsersContract
import by.yancheuski.githubusers.presenter.UsersPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UsersPresenterTest {

    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var repo: UsersRepo

    @Mock
    private lateinit var viewContract: UsersContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(repo)
    }

    @Test
    fun `should call repository when refreshing`() {
        presenter.onRefresh()

        verify(repo, times(1)).getUsers(any(), any())
    }

    @Test
    fun `should call view showUser when getting user with success`() {
        val expectedListUsers = listOf<UserEntity>()

        `when`(repo.getUsers(any(), any()))
            .thenAnswer {
                @Suppress("UNCHECKED_CAST")
                val arg = it.arguments[0] as ((List<UserEntity>) -> Unit)
                arg.invoke(expectedListUsers)
                return@thenAnswer null
            }

        presenter.attach(viewContract)
        presenter.onRefresh()

        verify(viewContract, times(1))
            .showUsers(expectedListUsers)
    }

    @Test
    fun `should call view showError when getting user with error`() {
        val expectedError = Throwable("This is sparta!")

        `when`(repo.getUsers(any(), any()))
            .thenAnswer {
                @Suppress("UNCHECKED_CAST")
                val arg = it.arguments[1] as ((Throwable) -> Unit)?
                arg?.invoke(expectedError)
                return@thenAnswer null
            }

        presenter.attach(viewContract)
        presenter.onRefresh()

        verify(viewContract, times(1))
            .showError(eq(expectedError))
    }
}