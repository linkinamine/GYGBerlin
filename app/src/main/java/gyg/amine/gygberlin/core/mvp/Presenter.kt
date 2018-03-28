package gyg.amine.gygberlin.core.mvp

/**
 *
 * We attach our view after the activity injection is done [gyg.amine.gygberlin.reviews.main.MainActivity.onActivityInject]
 * We detach our view in Ondestroy methof [gyg.amine.gygberlin.reviews.main.MainActivity.onDestroy]

 */
interface Presenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}