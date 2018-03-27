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
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private val manager: LinearLayoutManager by lazyFast { LinearLayoutManager(this) }
    private val adapter: ReviewsHolder by lazyFast { initializeAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        initializeRecyclerView()
        fetchReviews()
    }

    private fun fetchReviews() {
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
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                fetchReviews()
                return true
            }
            else                 -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSearchResponse(reviews: List<Review>) {
        adapter.updateData(reviews)
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun noResult() {
    }

    override fun onError() {

    }

    private fun initializeRecyclerView() {
        cm_rv.layoutManager = manager
        cm_rv.adapter = adapter
    }

    private fun initializeAdapter(): ReviewsHolder = ReviewsHolder(presenter)
}
