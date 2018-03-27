package gyg.amine.gygberlin.reviews.main

import gyg.amine.gygberlin.core.mvp.BasePresenter
import gyg.amine.gygberlin.core.schedulers.SchedulerProvider
import gyg.amine.gygberlin.reviews.api.Endpoints
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(var api: Endpoints, disposable: CompositeDisposable, scheduler: SchedulerProvider) : BasePresenter<MainView>(disposable, scheduler) {
}