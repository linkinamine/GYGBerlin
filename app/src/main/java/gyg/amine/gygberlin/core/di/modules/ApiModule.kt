package gyg.amine.gygberlin.core.di.modules

import dagger.Module
import dagger.Provides
import gyg.amine.gygberlin.reviews.api.Endpoints
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesEndpoints(retrofit: Retrofit): Endpoints = retrofit.create(Endpoints::class.java)
}