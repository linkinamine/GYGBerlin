package gyg.amine.gygberlin.core

import android.app.Application
import gyg.amine.gygberlin.core.di.component.AppComponent
import gyg.amine.gygberlin.core.di.modules.AppModule

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}.