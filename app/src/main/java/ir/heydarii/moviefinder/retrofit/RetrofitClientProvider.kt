package ir.heydarii.moviefinder.retrofit

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitClientProvider {

    fun getMainInterface(): RetrofitMainInterface
}