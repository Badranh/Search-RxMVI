package tech.appshive.searchmvi.features.search


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_search.*

import tech.appshive.searchmvi.R
import tech.appshive.searchmvi.features.search.actions.SearchAction
import tech.appshive.searchmvi.features.search.state.SearchState
import tech.appshive.searchmvi.framework.Action
import tech.appshive.searchmvi.framework.MviView
import tech.appshive.searchmvi.models.City

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(),MviView<Action,SearchState> {

    private val store = SearchStore()
    override val actions: PublishSubject<Action> = PublishSubject.create()

    override fun render(state: SearchState) {
        Log.d("renderer","rendering!")
        if(state.loading) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
        if(state.data?.cityName != null) tvIsFound.text = "Found City ${state.data.cityName} In DB"
        else tvIsFound.text = "Wait!"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.wire()
        store.bind(this)

        edtCityName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                actions.onNext(SearchAction.CitySearchAction(p0.toString()))
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }


}
