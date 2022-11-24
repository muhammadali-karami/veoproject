package co.veo.project

import co.veo.project.data.enum.MediaType
import co.veo.project.data.enum.TimeWindowType
import co.veo.project.utility.Const
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MovieRepositoryUnitTest {

    private lateinit var fakeRepository: MovieRepositoryMock

    @Before
    fun setup() {
        fakeRepository = MovieRepositoryMock()
    }

    @Test
    fun `returns false when server sent empty list`() {
        val movie = fakeRepository.getMovieList(
            MediaType.MOVIE.type,
            TimeWindowType.DAY.type,
            Const.API_KEY
        )

        assertNotNull(movie)
    }

    @Test
    fun `returns true when server sent null movie id`() {
        val movie = fakeRepository.getMovieDetail(
            436270,
            Const.API_KEY
        )

        assertNull(movie.id)
    }
}