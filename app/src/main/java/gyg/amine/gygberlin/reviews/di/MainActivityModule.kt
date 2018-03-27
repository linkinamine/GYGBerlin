package com.mohamedelaminebenallouch.asana.rebel.di

import dagger.Module
import dagger.Provides
import gyg.amine.gygberlin.core.di.ActivityScope
import gyg.amine.gygberlin.core.schedulers.AppSchedulerProvider
import gyg.amine.gygberlin.reviews.api.Endpoints
import gyg.amine.gygberlin.reviews.main.MainPresenter
import io.reactivex.disposables.CompositeDisposable

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    internal fun providesMainPresenter(api: Endpoints, disposable: CompositeDisposable, scheduler: AppSchedulerProvider): MainPresenter =
        MainPresenter(api, disposable, scheduler)
}
