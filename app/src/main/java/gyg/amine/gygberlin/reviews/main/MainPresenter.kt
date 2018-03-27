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
        reviews?.let { onFetchReviewsSuccess(it) }
    }

    private fun onFetchReviewsSuccess(result: ReviewsResponse) {
        view?.hideProgress()

        if (result.data == null || result.data.isEmpty()) {
            view?.noResult()
        } else {
            view?.onSearchResponse(result.data)
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
}