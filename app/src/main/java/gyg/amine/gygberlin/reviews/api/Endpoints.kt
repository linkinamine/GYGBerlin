package gyg.amine.gygberlin.reviews.api

import gyg.amine.gygberlin.reviews.models.ReviewsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface Endpoints {

    @GET("/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json?count=5&page=0&rating=0&sortBy=date_of_review&direction=DESC")
    fun fetchReviews(): Observable<ReviewsResponse>

}