package tech.appshive.searchmvi.framework

import io.reactivex.disposables.Disposable

class Store<A,S>(
    private val reducer : Reducer<S,A>,
    private val middlewares: List<Middleware<A,S>>,
    private val initialState : S

){
}