package gyg.amine.gygberlin.reviews.models

import com.google.gson.annotations.SerializedName


data class Review(

    @SerializedName("review_id")
    val reviewId: Int,

    @SerializedName("rating")
    var rating: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("message")
    var message: String,

    @SerializedName("author")
    var author: String,

    @SerializedName("foreignLanguage")
    var foreignLanguage: Boolean,

    @SerializedName("date")
    var date: String,

    @SerializedName("languageCode")
    var languageCode: String,

    @SerializedName("traveler_type")
    var travelerType: String,

    @SerializedName("reviewerName")
    var reviewerName: String,

    @SerializedName("reviewerCountry")
    var reviewerCountry: String
)