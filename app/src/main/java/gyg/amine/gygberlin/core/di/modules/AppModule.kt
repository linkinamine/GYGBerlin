package gyg.amine.gygberlin.core.di.modules

import android.app.Application
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import gyg.amine.gygberlin.core.schedulers.AppSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    @Singleton
    fun providesApplication() = application

    @Provides
    @Singleton
    fun providesResources() = application.resources

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()

}