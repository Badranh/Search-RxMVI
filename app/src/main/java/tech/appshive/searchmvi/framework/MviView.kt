package tech.appshive.searchmvi.framework

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


interface MviView<A,S> {
    val actions : Observable<A>
    fun render(state:S)
}