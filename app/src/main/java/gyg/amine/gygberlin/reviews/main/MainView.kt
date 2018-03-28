package gyg.amine.gygberlin.reviews.main

import gyg.amine.gygberlin.core.mvp.BaseView
import gyg.amine.gygberlin.reviews.models.Review

/**
 * The following methods should be implemented on our [MainActivity]
 * our [MainPresenter] updated  our view [MainActivity] through these methods
 */
interface MainView : BaseView {
    fun onFetchReviewsSuccess(list: List<Review>)
    fun showProgress()
    fun hideProgress()
    fun noResult()
    fun onError()
    fun reviewAdded(review: Review)
}
