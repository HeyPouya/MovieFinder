package ir.heydarii.moviefinder.retrofit

import io.reactivex.Observable
import ir.heydarii.moviefinder.pojo.MovieNameSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 *
 * Retrofit interface to call the needed apis
 */
interface RetrofitMainInterface {

    @GET("search/movie")
    fun searchMovieByName(@Query("api_key") apiKey: String, @Query("query") movieName: String): Observable<MovieNameSearchResponse>
}