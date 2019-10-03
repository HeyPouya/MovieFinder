package ir.heydarii.moviefinder.features.moviedetail

import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import ir.heydarii.moviefinder.base.BaseViewModel
import ir.heydarii.moviefinder.pojo.MovieDetailsResponseModel
import ir.heydarii.moviefinder.repository.DataRepository

class MovieDetailsViewModel(val repository: DataRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    val movieDetailsData = MutableLiveData<MovieDetailsResponseModel>()

    fun getMovieDetails(apiKey: String, movieId: Long) {
        disposable.add(
            repository.getMovieDetails(apiKey, movieId)
                .subscribe({
                    movieDetailsData.value = it
                }, {
                    Logger.d(it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}