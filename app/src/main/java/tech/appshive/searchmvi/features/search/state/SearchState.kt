package tech.appshive.searchmvi.features.search.state

import tech.appshive.searchmvi.models.City

data class SearchState(val loading:Boolean = false,val data:City? = null , val error : Throwable? = null , val suggestion : ArrayList<City>? = null )