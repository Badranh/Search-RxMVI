package tech.appshive.searchmvi.repository

import io.reactivex.Observable
import tech.appshive.searchmvi.models.City

interface Repository {
    fun addCity(city : City)
    fun deleteCity(id : Int) : Boolean
    fun city(id : Int): City?
    fun city(name : String): Observable<City>?

}