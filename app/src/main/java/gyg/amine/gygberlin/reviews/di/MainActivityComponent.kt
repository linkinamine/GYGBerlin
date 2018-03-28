package com.mohamedelaminebenallouch.asana.rebel.di

import dagger.Component
import gyg.amine.gygberlin.core.di.ActivityScope
import gyg.amine.gygberlin.core.di.component.AppComponent
import gyg.amine.gygberlin.reviews.main.MainActivity

/**
 * Enable [MainActivityModule] that is used for performing dependency injection
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}
