package com.example.earthquake_app.ui.earthquakelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquake_app.R
import com.example.earthquake_app.model.EarthquakeModel
import com.example.earthquake_app.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_earthquake_list.*

class EarthquakeListFragment: Fragment() {

    private val viewModel: EarthquakeListViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewmodel after onActivityCreated"
        }
        ViewModelProvider(this, EarthquakeListViewModel.Factory(activity.application))
            .get(EarthquakeListViewModel::class.java)
    }

    private var adapter: EarthquakeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.title = getString(R.string.earthquake_list_title)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)

        viewModel.isNetworkError.observe(viewLifecycleOwner, Observer<Boolean> {isNetworkError ->
            if (isNetworkError) showNetworkError()
        })

        adapter = EarthquakeAdapter(EarthquakeClickedListener {
            val bundle = bundleOf("earthquake" to it)
            view?.findNavController()?.navigate(R.id.action_earthquake_list_fragment_to_earthquake_details_fragment, bundle)
        })

        return inflater.inflate(R.layout.fragment_earthquake_list, container, false)
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        earthquakes_recycler_view.adapter = adapter
        earthquakes_recycler_view.layoutManager = LinearLayoutManager(context)
        viewModel.earthquakeList.observe(viewLifecycleOwner, Observer<List<EarthquakeModel>> { earthquakes ->
            earthquakes?.apply {
                adapter?.earthquakes = earthquakes
            }
        })
    }

    private fun showNetworkError() {
        Toast.makeText(activity, getString(R.string.earthquake_error_message), Toast.LENGTH_LONG).show()
    }
}

