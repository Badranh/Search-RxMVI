package tech.appshive.searchmvi.features.search

import android.util.Log
import io.reactivex.Observable
import tech.appshive.searchmvi.features.search.actions.SearchAction
import tech.appshive.searchmvi.features.search.actions.SearchInternalActions
import tech.appshive.searchmvi.features.search.state.SearchState
import tech.appshive.searchmvi.framework.Action
import tech.appshive.searchmvi.framework.Middleware
import tech.appshive.searchmvi.repository.Repository

class SearchMiddleware(private val repo : Repository): Middleware<Action,SearchState> {
    override fun bind(
        actions: Observable<Action>,
        state: Observable<SearchState>
    ): Observable<Action> {
        return actions.ofType<SearchAction.CitySearchAction>(SearchAction.CitySearchAction::class.java)
            .flatMap { action ->
                repo.city(action.query)!!
                    .map<SearchInternalActions>{ city -> SearchInternalActions.SearchInternalSuccessAction(city) }
                    .onErrorReturn { e->SearchInternalActions.SearchInternalFailureAction(e) }
                    .startWith(SearchInternalActions.LoadingAction)
            }
    }
}