package com.mohamedelaminebenallouch.asana.rebel.di

import dagger.Component
import gyg.amine.gygberlin.reviews.main.MainActivity
import gyg.amine.gygberlin.core.di.ActivityScope
import gyg.amine.gygberlin.core.di.component.AppComponent

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}
