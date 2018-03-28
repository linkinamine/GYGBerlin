package gyg.amine.gygberlin.reviews.api

import gyg.amine.gygberlin.reviews.models.Review
import gyg.amine.gygberlin.reviews.models.ReviewsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface Endpoints {

    @GET
    fun fetchReviews(@Url url: String): Observable<ReviewsResponse>

    @POST("reviews/create")
    @FormUrlEncoded
    fun addReview(@Body review: Review): Single<Review>
}