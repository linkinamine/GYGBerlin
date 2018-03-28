package gyg.amine.gygberlin.reviews.models

import com.google.gson.annotations.SerializedName


data class ReviewsResponse(

    @SerializedName("status")
     val status: Boolean,

    @SerializedName("total_reviews_comments")
     val totalReviewsComments: Int,

    @SerializedName("data")
     val data: List<Review>?
)
