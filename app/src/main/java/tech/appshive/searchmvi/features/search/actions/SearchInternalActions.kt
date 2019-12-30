package tech.appshive.searchmvi.features.search.actions

import tech.appshive.searchmvi.framework.Action
import tech.appshive.searchmvi.models.City

sealed class SearchInternalActions: Action{
    object LoadingAction : SearchInternalActions()
    class SearchInternalSuccessAction(val data:City): SearchInternalActions()
    class SearchInternalFailureAction(val error:Throwable): SearchInternalActions()
    class SuggestionsLoadedAction(val suggestions: ArrayList<City>) : SearchInternalActions()
}