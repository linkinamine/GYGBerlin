package gyg.amine.gygberlin.reviews.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.mohamedelaminebenallouch.asana.rebel.di.DaggerMainActivityComponent
import com.mohamedelaminebenallouch.asana.rebel.di.MainActivityModule
import gyg.amine.gygberlin.R
import gyg.amine.gygberlin.core.BaseActivity
import gyg.amine.gygberlin.reviews.extensions.lazyFast
import gyg.amine.gygberlin.reviews.models.Review
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private val manager: LinearLayoutManager by lazyFast { LinearLayoutManager(this) }
    private val adapter: ReviewsHolder by lazyFast { initializeAdapter() }
    private var reviewsList: List<Review>? = null
    private val sortModeDescending = "DESC"

    //Lifecycle and  injection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initializeRecyclerView()
        presenter.fetchReviews(applicationContext)
    }

    override fun onActivityInject() {

        DaggerMainActivityComponent.builder().appComponent(getAppcomponent())
            .mainActivityModule(MainActivityModule())
            .build()
            .inject(this)

        presenter.attachView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    // MainView overridden functions
    override fun onFetchReviewsSuccess(reviews: List<Review>) {
        adapter.updateData(reviews)
        reviewsList = reviews
    }

    override fun showProgress() {
        cm_str.isRefreshing = true
    }

    override fun hideProgress() {
        cm_str.isRefreshing = false
    }

    override fun noResult() {
        createSnackBar("No Result")
    }

    override fun onError() {
        createSnackBar("There was an error fetching the reviews")
    }

    override fun reviewAdded(review: Review) {
        createSnackBar("Thanks for your review : " + review.reviewerName)
    }

    //Private methods
    private fun filterReviews(travelerType: String) {
        val sortedReviews = reviewsList?.let { it.filter { r -> r.travelerType == travelerType } }
        sortedReviews?.let {
            adapter.updateData(sortedReviews)
            manager.scrollToPosition(0)
        }
    }

    private fun sortReviewsByRatings(sortMode: String? = null) {
        val sortedReviews =
            when (sortMode) {
                sortModeDescending -> reviewsList?.let { it.sortedByDescending { it.rating } }
                else               -> reviewsList?.let { it.sortedBy { it.rating } }
            }
        sortedReviews?.let {
            adapter.updateData(sortedReviews)
            manager.scrollToPosition(0)
        }
    }


    private fun sortReviewsByDate(sortMode: String? = null) {
        val sortedReviews =
            when (sortMode) {
                sortModeDescending -> reviewsList?.let { it.sortedByDescending { forrmatReviewDate(it.date) } }
                else               -> reviewsList?.let { it.sortedBy { forrmatReviewDate(it.date) } }
            }
        sortedReviews?.let {
            adapter.updateData(sortedReviews)
            manager.scrollToPosition(0)
        }
    }

    private fun initializeRecyclerView() {
        cm_rv.layoutManager = manager
        cm_rv.adapter = adapter
        cm_str.setBackgroundResource(R.color.primary_material_dark)
        cm_str.isEnabled = true
        cm_str.setOnRefreshListener { presenter.fetchReviews(applicationContext) }
    }

    private fun createSnackBar(message: String) {
        Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
    }

    private fun initializeAdapter(): ReviewsHolder = ReviewsHolder(presenter)

    private fun forrmatReviewDate(reviewDate: String): Long {
        val sdf = SimpleDateFormat("MMM dd, yyyy")
        val date = sdf.parse(reviewDate)
        return date.time
    }

    fun onRatingsGroupClickListener(item: MenuItem) {
        when {
            item.itemId == R.id.ratings_highest -> {
                item.isChecked = true
                sortReviewsByRatings(sortModeDescending)
            }
            item.itemId == R.id.ratings_lowest  -> {
                item.isChecked = true
                sortReviewsByRatings()
            }
        }
    }

    fun onDateGroupClickListener(item: MenuItem) {

        when {
            item.itemId == R.id.date_latest -> {
                item.isChecked = true
                sortReviewsByDate(sortModeDescending)
            }
            item.itemId == R.id.date_oldest -> {
                item.isChecked = true
                sortReviewsByDate()
            }
        }
    }

    fun onFilterGroupClickListener(item: MenuItem) {
        when (item.itemId) {
            R.id.family_young -> {
                filterReviews(TravelerType.FAMILY_YOUNG.toString().toLowerCase())
                item.isChecked = true
            }
            R.id.family_old   -> {
                filterReviews(TravelerType.FAMILY_OLD.toString().toLowerCase())
                item.isChecked = true

            }
            R.id.friends      -> {
                filterReviews(TravelerType.FRIENDS.toString().toLowerCase())
                item.isChecked = true

            }
            R.id.couple       -> {
                filterReviews(TravelerType.COUPLE.toString().toLowerCase())
                item.isChecked = true

            }
            R.id.solo         -> {
                filterReviews(TravelerType.SOLO.toString().toLowerCase())
                item.isChecked = true

            }
        }
    }

}
