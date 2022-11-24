package co.veo.project

import co.veo.project.data.enum.MediaType
import co.veo.project.data.enum.TimeWindowType
import co.veo.project.data.repository.MovieRepository
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

    private lateinit var fakeRepository: MovieRepositoryFake

    @Before
    fun setup() {
        fakeRepository = MovieRepositoryFake()
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
}