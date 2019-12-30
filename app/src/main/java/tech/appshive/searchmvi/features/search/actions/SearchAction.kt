package tech.appshive.searchmvi.features.search.actions

import tech.appshive.searchmvi.framework.Action

sealed class SearchAction : Action {
    class CitySearchAction(val query:String) : SearchAction()
}