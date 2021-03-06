package tech.appshive.searchmvi.framework

import io.reactivex.Observable


interface Middleware<A,S> {
    fun bind(actions : Observable<A>, state: Observable<S>):Observable<A>
}