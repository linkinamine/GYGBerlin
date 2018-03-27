package gyg.amine.gygberlin.reviews.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gyg.amine.gygberlin.R
import gyg.amine.gygberlin.reviews.extensions.notifyNewData
import gyg.amine.gygberlin.reviews.models.Review
import kotlinx.android.synthetic.main.review_row.view.*
import java.text.DateFormat
import java.util.*
import kotlin.properties.Delegates


class ReviewsHolder(val callbacks: Callbacks) : RecyclerView.Adapter<ReviewsHolder.ViewHolder>() {

    //Notifying every time the set of items change, using Delegates.observer
    //more details https://antonioleiva.com/recyclerview-diffutil-kotlin/
    private var items: List<Review> by Delegates.observable(emptyList()) { _, old, new ->
        notifyNewData(old, new) { o, n -> o.reviewId == n.reviewId }
    }

    interface Callbacks {
        fun onReviewItemSelected(review: Review)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.review_row, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    fun updateData(newData: List<Review>) {
        items = newData
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: Review) {
            with(itemView) {
                //Review
                review_rating_iv.rating = item.rating.toFloat()
                //setImageResource(handleRatingImageView(item.rating))
                review_title_tv.text = item.title
                review_date_tv.text = item.date
                review_language_iv.setImageResource(handleReviewLanguageImageView(item.languageCode))
                //Reviewer
                reviewer_name_tv.text = item.reviewerName
                reviewer_country_tv.text = item.reviewerCountry
            }
        }

    }

    private fun handleReviewLanguageImageView(language: String): Int {
        return when (language) {
            "de" -> R.drawable.ic_flag_germany
            else -> R.drawable.ic_flag_uk
        }
    }

    private fun formatReviewDate(date: Date): String {
        val locale = Locale.getDefault()
        val dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale)
        return dateFormat.format(date)
    }
}
