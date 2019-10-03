package ir.heydarii.moviefinder.repository

import io.reactivex.Observable
import ir.heydarii.moviefinder.pojo.MovieNameSearchResponse
import ir.heydarii.moviefinder.repository.network.NetworkRepository

class DataRepository(private val network: NetworkRepository) {


    fun searchMovieName(apiKey: String, movieName: String): Observable<MovieNameSearchResponse>? {
        return network.searchMovieName(apiKey, movieName)
    }
}