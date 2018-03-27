package gyg.amine.gygberlin.reviews.api

import gyg.amine.gygberlin.reviews.models.ReviewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface Endpoints {

    @GET
    fun fetchReviews(@Url url: String): Observable<ReviewsResponse>
}