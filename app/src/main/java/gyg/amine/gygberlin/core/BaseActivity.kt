package gyg.amine.gygberlin.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import gyg.amine.gygberlin.core.di.component.AppComponent
import gyg.amine.gygberlin.core.mvp.BasePresenter
import gyg.amine.gygberlin.core.mvp.BaseView

/**
 * Our activities can implement this class, so we provide the lifecycle components as well as our [BasePresenter]
 * notice that the injection is provided here through [onActivityInject]
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }

    protected abstract fun onActivityInject()

    fun getAppcomponent(): AppComponent = App.appComponent

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }
}
