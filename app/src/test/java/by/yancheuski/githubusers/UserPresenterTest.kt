package by.yancheuski.githubusers

import by.yancheuski.githubusers.domain.entities.UserEntity
import by.yancheuski.githubusers.domain.repos.UsersRepo
import by.yancheuski.githubusers.presenter.UserContract
import by.yancheuski.githubusers.presenter.UserPresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UserPresenterTest {

    private lateinit var presenter: UserPresenter

    @Mock
    private lateinit var repo: UsersRepo

    @Mock
    private lateinit var viewContract: UserContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(repo)
    }

    @Test
    fun `should call repository when refreshing`() {
        val expectedParameter = "username"

        presenter.onRefresh(expectedParameter)

        verify(repo, times(1))
            .getUser(eq(expectedParameter), any(), any())
    }

    @Test
    fun `should call view showUser when getting user with success`() {
        val expectedUserEntity = UserEntity(
            login = "pashtet",
            id = 11,
            avatarUrl = ""
        )

        `when`(repo.getUser(any(), any(), any()))
            .thenAnswer {
                @Suppress("UNCHECKED_CAST")
                val arg = it.arguments[1] as ((user: UserEntity) -> Unit)
                arg.invoke(expectedUserEntity)
                return@thenAnswer null
            }

        presenter.attach(viewContract)
        presenter.onRefresh("username")

        verify(viewContract, times(1))
            .showUser(eq(expectedUserEntity))
    }

    @Test
    fun `should call view showError when getting user with error`() {
        val expectedError = Throwable("This is sparta!")

        `when`(repo.getUser(any(), any(), any()))
            .thenAnswer {
                @Suppress("UNCHECKED_CAST")
                val arg = it.arguments[2] as ((Throwable) -> Unit)?
                arg?.invoke(expectedError)
                return@thenAnswer null
            }

        presenter.attach(viewContract)
        presenter.onRefresh("username")

        verify(viewContract, times(1))
            .showError(eq(expectedError))
    }
}