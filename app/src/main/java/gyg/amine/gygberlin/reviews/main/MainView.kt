package gyg.amine.gygberlin.reviews.main

import gyg.amine.gygberlin.core.mvp.BaseView
import gyg.amine.gygberlin.reviews.models.ReviewsResponse

interface MainView : BaseView {
    fun onSearchResponse(list: List<ReviewsResponse>?)
    fun showProgress()
    fun hideProgress()
    fun noResult()
}
