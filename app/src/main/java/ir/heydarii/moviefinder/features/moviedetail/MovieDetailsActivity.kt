package ir.heydarii.moviefinder.features.moviedetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ir.heydarii.moviefinder.R
import ir.heydarii.moviefinder.base.BaseActivity
import ir.heydarii.moviefinder.pojo.Genre
import ir.heydarii.moviefinder.pojo.MovieDetailsResponseModel
import ir.heydarii.moviefinder.repository.DaggerDataRepositoryProvider
import ir.heydarii.moviefinder.utils.Consts
import ir.heydarii.moviefinder.utils.ViewModelFactoryProvider
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {

    lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getLongExtra(Consts.MOVIE_ID, -1L)

        check(movieId != -1L) { "You must pass the movie Id" }

        val repository = DaggerDataRepositoryProvider.create().getRepository()
        val factoryProvider = ViewModelFactoryProvider(repository)
        viewModel = ViewModelProvider(this, factoryProvider).get(MovieDetailsViewModel::class.java)

        viewModel.movieDetailsData.observe(this, Observer {
            showMovieDetails(it)
        })

        viewModel.getMovieDetails(Consts.API_KEY, movieId)

    }

    private fun showMovieDetails(details: MovieDetailsResponseModel) {

        Picasso.get().load(Consts.BIG_PIC_URL + details.backdrop_path).into(imgCover)
        Picasso.get().load(Consts.SMALL_PIC_URL + details.poster_path).into(imgPoster)
        txtRate.text = details.vote_average.toString()
        txtTitle.text = details.title
        txtReleaseDate.text = getString(R.string.movie_release_date, details.release_date)
        txtDuration.text = getString(R.string.movie_length, details.runtime.toString())
        txtGenrs.text = getGenres(details.genres)
        txtDescription.text = details.overview
    }

    private fun getGenres(genres: List<Genre>): String {
        var genresText = ""
        genres.forEachIndexed { index, genre ->

            genresText += genre.name
            if (index != genres.size - 1)
                genresText += ", "
        }
        return genresText
    }
}
