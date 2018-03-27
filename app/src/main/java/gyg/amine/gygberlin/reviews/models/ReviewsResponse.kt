package gyg.amine.gygberlin.reviews.models

import com.google.gson.annotations.SerializedName


data class ReviewsResponse(

    @SerializedName("status")
    private val status: Boolean,

    @SerializedName("total_reviews_comments")
    private val totalReviewsComments: Int,

    @SerializedName("data")
    private val data: List<Review>?
)
