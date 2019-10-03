package ir.heydarii.moviefinder.repository.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.heydarii.moviefinder.pojo.MovieDetailsResponseModel
import ir.heydarii.moviefinder.pojo.MovieNameSearchResponse
import ir.heydarii.moviefinder.retrofit.RetrofitMainInterface
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val retrofitInterface: RetrofitMainInterface) {


    fun searchMovieName(apiKey: String, movieName: String): Observable<MovieNameSearchResponse> {
        return retrofitInterface.searchMovieByName(apiKey, movieName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovieDetails(movieId: Long, apiKey: String): Observable<MovieDetailsResponseModel> {
        return retrofitInterface.getMovieDetails(movieId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}