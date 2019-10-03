package ir.heydarii.moviefinder.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.heydarii.moviefinder.features.moviesearch.SearchMovieViewModel
import ir.heydarii.moviefinder.repository.DataRepository
import java.lang.IllegalStateException

class ViewModelFactoryProvider(private val repository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when{
            modelClass.isAssignableFrom(SearchMovieViewModel::class.java) -> SearchMovieViewModel(repository) as T
            else -> throw IllegalStateException("You haven't provided factory for this ViewModel")
        }
    }
}