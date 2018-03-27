package gyg.amine.gygberlin.core.mvp

interface BaseView {
    fun setPresenter(presenter: BasePresenter<*>)
    fun onError() {}
}