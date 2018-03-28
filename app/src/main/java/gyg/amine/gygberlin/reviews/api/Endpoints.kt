package gyg.amine.gygberlin.reviews.api

import gyg.amine.gygberlin.reviews.models.Review
import gyg.amine.gygberlin.reviews.models.ReviewsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * We communicate with our api through this interface, network calls can be defined here
 */
interface Endpoints {

    @GET("/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json?count=5&page=0&rating=0&sortBy=date_of_review&direction=DESC")
    fun fetchReviews(): Observable<ReviewsResponse>

    @POST("reviews/create")
    @FormUrlEncoded
    fun addReview(@Body review: Review): Single<Review>
}