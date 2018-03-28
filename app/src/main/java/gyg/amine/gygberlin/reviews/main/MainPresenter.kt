package gyg.amine.gygberlin.reviews.main

import android.content.Context
import com.google.gson.Gson
import gyg.amine.gygberlin.R
import gyg.amine.gygberlin.core.mvp.BasePresenter
import gyg.amine.gygberlin.core.schedulers.SchedulerProvider
import gyg.amine.gygberlin.reviews.api.Endpoints
import gyg.amine.gygberlin.reviews.models.Review
import gyg.amine.gygberlin.reviews.models.ReviewsResponse
import io.reactivex.disposables.CompositeDisposable
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject


class MainPresenter @Inject constructor(private var api: Endpoints, disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<MainView>(disposable, scheduler), ReviewsHolder.Callbacks {

    override fun onReviewItemSelected(review: Review) {
        //we have access to the selected review
    }

    fun fetchReviews(applicationContext: Context) {
        view?.showProgress()

        //The following commented code is the normal call to the Api

        /*val url = "/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json"
        val observable = api.fetchReviews(url)
        val subscription = observable.subscribeOn(scheduler.io()).observeOn(scheduler.ui()).subscribe({ onFetchReviewsSuccess(it) }, { onFetchReviewsFailure() })
        disposable.add(subscription)*/

        val myJson = inputStreamToString(applicationContext.resources.openRawResource(R.raw.reviews))
        val reviews = myJson?.let { Gson().fromJson(it, ReviewsResponse::class.java) }
        if (reviews != null) {
            onFetchReviewsSuccess(reviews)
        } else {
            onFetchReviewsFailure()
        }
    }

    private fun onFetchReviewsSuccess(result: ReviewsResponse) {
        view?.hideProgress()

        if (result.data == null || result.data.isEmpty()) {
            view?.noResult()
        } else {
            view?.onFetchReviewsSuccess(result.data)
        }
    }

    private fun onFetchReviewsFailure() {
        view?.hideProgress()
        view?.onError()
    }

    private fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }

    }

    /**
     * We create the post request
     */
    fun addReview(review: Review) {
        view?.showProgress()

        val observable = api.addReview(review)
        val subscription = observable.subscribeOn(scheduler.io()).observeOn(scheduler.ui()).subscribe({ onAddReviewSuccess(it) }, { onAddReviewFailure() })
        disposable.add(subscription)

    }

    private fun onAddReviewSuccess(result: Review) {
        view?.hideProgress()

        view?.reviewAdded()
    }

    private fun onAddReviewFailure() {
        view?.hideProgress()
        view?.onError()
    }

}