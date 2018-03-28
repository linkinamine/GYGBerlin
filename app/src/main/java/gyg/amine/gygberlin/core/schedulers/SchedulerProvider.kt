package gyg.amine.gygberlin.core.schedulers

import io.reactivex.Scheduler

/**
 * Threading in RxJava is done with help of Schedulers. Scheduler can be thought of as a thread pool managing 1 or more threads.
 * Whenever a Scheduler needs to execute a task, it will take a thread from its pool and run the task in that thread.
 * more details here : https://proandroiddev.com/understanding-rxjava-subscribeon-and-observeon-744b0c6a41ea
 */
interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}