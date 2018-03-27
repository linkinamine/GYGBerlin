package gyg.amine.gygberlin.core.mvp

interface Presenter<V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}