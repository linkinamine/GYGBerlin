package gyg.amine.gygberlin.core.di.component

import android.app.Application
import android.content.res.Resources
import com.google.gson.Gson
import dagger.Component
import gyg.amine.gygberlin.core.di.modules.ApiModule
import gyg.amine.gygberlin.core.di.modules.AppModule
import gyg.amine.gygberlin.core.di.modules.NetworkModule
import gyg.amine.gygberlin.core.di.modules.RetrofitModule
import gyg.amine.gygberlin.core.schedulers.AppSchedulerProvider
import gyg.amine.gygberlin.reviews.api.Endpoints
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, NetworkModule::class))
interface AppComponent {
    fun application(): Application
    fun gson(): Gson
    fun resources(): Resources
    fun retrofit(): Retrofit
    fun endpoints(): Endpoints
    fun client(): OkHttpClient
    fun loggingInterceptor(): HttpLoggingInterceptor
    fun compositeDisposable(): CompositeDisposable
    fun schedulerProvider(): AppSchedulerProvider
}