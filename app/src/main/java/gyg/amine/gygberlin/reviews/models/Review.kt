package gyg.amine.gygberlin.reviews.models

import com.google.gson.annotations.SerializedName
import java.util.*


data class Review(

    @SerializedName("review_id")
    private val reviewId: Int,

    @SerializedName("rating")
    private var rating: String,

    @SerializedName("title")
    private var title: String,
    @SerializedName("message")
    private var message: String,

    @SerializedName("author")
    private var author: String,

    @SerializedName("foreignLanguage")
    private var foreignLanguage: Boolean,

    @SerializedName("date")
    private var date: String,

    @SerializedName("date_unformatted")
    private var dateUnformatted: Date?,

    @SerializedName("languageCode")
    private var languageCode: String,

    @SerializedName("traveler_type")
    private var travelerType: String,

    @SerializedName("reviewerName")
    private var reviewerName: String,

    @SerializedName("reviewerCountry")
    private var reviewerCountry: String
)