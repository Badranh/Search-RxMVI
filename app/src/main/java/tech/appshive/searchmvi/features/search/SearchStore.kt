package tech.appshive.searchmvi.features.search

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import tech.appshive.searchmvi.features.search.reducer.SearchReducer
import tech.appshive.searchmvi.features.search.state.SearchState
import tech.appshive.searchmvi.framework.Action
import tech.appshive.searchmvi.framework.MviView
import tech.appshive.searchmvi.repository.offline.InMemoryDatabase

class SearchStore{

    private val state = BehaviorRelay.createDefault<SearchState>(SearchState())
    private val actions = PublishRelay.create<Action>()

    fun wire():Disposable{
        val disposable = CompositeDisposable()
        disposable.add(actions.distinctUntilChanged()
            .withLatestFrom(state, BiFunction<Action,SearchState,SearchState>{ action, state -> SearchReducer().reduce(state = state,action = action)})
            .subscribe(state::accept))
        disposable.add(SearchMiddleware(InMemoryDatabase()).bind(actions, state).subscribe(actions::accept))

        return disposable
    }

    fun bind(view : MviView<Action,SearchState>): Disposable {
        val disposable =  CompositeDisposable()
        disposable.add(state.observeOn(AndroidSchedulers.mainThread()).subscribe(view::render))
        disposable.add(view.actions.subscribe(actions::accept))
        return disposable
    }
}