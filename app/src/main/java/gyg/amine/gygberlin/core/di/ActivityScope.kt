package gyg.amine.gygberlin.core.di

import javax.inject.Scope

/**
 * @ActivityScope keeps references as long as Activity exists
 */
@Scope
@Retention
annotation class ActivityScope