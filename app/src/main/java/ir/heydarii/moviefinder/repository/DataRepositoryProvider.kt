package ir.heydarii.moviefinder.repository

import dagger.Component
import ir.heydarii.moviefinder.retrofit.RetrofitModule
import javax.inject.Singleton

@Singleton
@Component (modules = [RetrofitModule::class])
interface DataRepositoryProvider {

    fun getRepository() : DataRepository
}