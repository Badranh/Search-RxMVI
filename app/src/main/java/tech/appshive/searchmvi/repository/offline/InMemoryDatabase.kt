package tech.appshive.searchmvi.repository.offline

import io.reactivex.Observable
import tech.appshive.searchmvi.models.City
import tech.appshive.searchmvi.repository.Repository
import java.util.concurrent.TimeUnit

class InMemoryDatabase : Repository {

    private val cities : ArrayList<City> = ArrayList()

    init {
        cities.add(City(1,"Beirut"))
        cities.add(City(2,"Berlin"))
        cities.add(City(3,"Nairobi"))
        cities.add(City(4,"Moscow"))
    }

    override fun city(name: String): Observable<City>? {
        return Observable.just(cities.find { it.cityName == name}?:City(null,null)).delay(3000,TimeUnit.MILLISECONDS)
    }

    override fun deleteCity(id: Int) = cities.remove(cities.find { it.id  == id})


    override fun city(id: Int) = cities.find { it.id == id}

    override fun addCity(city: City) {
        if(!cities.contains(city)) cities.add(city)
    }
}