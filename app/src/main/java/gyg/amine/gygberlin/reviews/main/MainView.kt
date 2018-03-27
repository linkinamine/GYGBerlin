package gyg.amine.gygberlin.reviews.main

import gyg.amine.gygberlin.core.mvp.BaseView
import gyg.amine.gygberlin.reviews.models.Review

interface MainView : BaseView {
    fun onSearchResponse(list: List<Review>)
    fun showProgress()
    fun hideProgress()
    fun noResult()
    fun onError()
}
