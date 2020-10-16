package com.example.earthquake_app.ui.earthquakedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.earthquake_app.R
import com.example.earthquake_app.model.EarthquakeModel
import com.example.earthquake_app.ui.MainActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class EarthquakeDetailsFragment: Fragment(), OnMapReadyCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.title = getString(R.string.earthquake_details_title)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        return inflater.inflate(R.layout.fragment_earthquake_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fm = childFragmentManager
        val map = fm.findFragmentById(R.id.map) as SupportMapFragment?
        map?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val earthquake = arguments?.getSerializable("earthquake") as EarthquakeModel
        googleMap?.apply {
            val location = LatLng(earthquake.lat, earthquake.lng)
            addMarker(
                MarkerOptions()
                    .position(location)
                    .title(getString(R.string.earthquake_label)))
        }
    }


}