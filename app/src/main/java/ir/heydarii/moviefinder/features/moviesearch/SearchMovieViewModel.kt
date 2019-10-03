package ir.heydarii.moviefinder.features.moviesearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.moviefinder.pojo.MovieNameSearchResponse
import ir.heydarii.moviefinder.repository.DataRepository

class SearchMovieViewModel(private val repository: DataRepository) : ViewModel() {

    private val disposable = CompositeDisposable()
    val movieSearchData = MutableLiveData<MovieNameSearchResponse>()

    fun searchMovie(apiKey: String, movieName: String) {
        disposable.add(repository.searchMovieName(apiKey, movieName)
                .subscribe({
                    movieSearchData.value = it
                }, {
                    Logger.d(it)
                }))
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}