package ir.heydarii.moviefinder.features.moviesearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.moviefinder.R
import ir.heydarii.moviefinder.base.BaseActivity
import ir.heydarii.moviefinder.features.moviedetail.MovieDetailsActivity
import ir.heydarii.moviefinder.pojo.Result
import ir.heydarii.moviefinder.repository.DaggerDataRepositoryProvider
import ir.heydarii.moviefinder.utils.Consts
import ir.heydarii.moviefinder.utils.Consts.Companion.MOVIE_ID
import ir.heydarii.moviefinder.utils.ViewModelFactoryProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var viewModel: SearchMovieViewModel
    lateinit var factory: ViewModelFactoryProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataRepository = DaggerDataRepositoryProvider.create().getRepository()
        factory = ViewModelFactoryProvider(dataRepository)
        viewModel = ViewModelProvider(this, factory).get(SearchMovieViewModel::class.java)


        viewModel.movieSearchData.observe(this, Observer {
            progress.visibility = View.INVISIBLE
            showRecyclerList(it.results)
        })

        imgSearch.setOnClickListener {
            progress.visibility = View.VISIBLE
            viewModel.searchMovie(Consts.API_KEY, edtSearchMovie.text.toString())
        }
    }

    private fun showRecyclerList(results: List<Result>) {
        recycler.adapter = MovieSearchAdapter(results) { showDetailsPage(it) }
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun showDetailsPage(movieId: Long) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID,movieId)
        startActivity(intent)
    }
}
