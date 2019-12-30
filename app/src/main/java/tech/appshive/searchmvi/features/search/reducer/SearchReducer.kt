package tech.appshive.searchmvi.features.search.reducer

import tech.appshive.searchmvi.features.search.actions.SearchAction
import tech.appshive.searchmvi.features.search.actions.SearchInternalActions
import tech.appshive.searchmvi.features.search.state.SearchState
import tech.appshive.searchmvi.framework.Action
import tech.appshive.searchmvi.framework.Reducer

class SearchReducer : Reducer<SearchState,Action> {
    override fun reduce(state: SearchState, action: Action): SearchState {
        if(action is SearchInternalActions.LoadingAction && state.loading) return  state
        return when(action){
            SearchInternalActions.LoadingAction -> state.copy(loading = true,error = null,suggestion = null)
            is SearchInternalActions.SearchInternalSuccessAction -> state.copy(loading = false,data = action.data,error = null,suggestion = null)
            is SearchInternalActions.SearchInternalFailureAction -> state.copy(loading = false,error = action.error)
            is SearchInternalActions.SuggestionsLoadedAction -> state.copy(suggestion = action.suggestions)
            is SearchInternalActions,is SearchAction -> state
            else -> state
        }
    }

}