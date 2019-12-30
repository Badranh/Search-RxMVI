package tech.appshive.searchmvi.framework

interface Reducer<S,A> {
    fun reduce(state : S,action: A) : S
}