package com.example.earthquake_app.ui.earthquakelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquake_app.R
import com.example.earthquake_app.model.EarthquakeModel
import kotlinx.android.synthetic.main.earthquake_item.view.*

class EarthquakeAdapter(private val callback: EarthquakeClickedListener): RecyclerView.Adapter<EarthquakeViewHolder>() {

    var earthquakes: List<EarthquakeModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthquakeViewHolder {
        return EarthquakeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.earthquake_item, parent, false), callback)
    }

    override fun getItemCount(): Int {
        return earthquakes.size
    }

    override fun onBindViewHolder(holder: EarthquakeViewHolder, position: Int) {
        holder.bind(earthquakes[position])
    }

}

class EarthquakeViewHolder(view: View,
                           private val callback: EarthquakeClickedListener): RecyclerView.ViewHolder(view) {
    private val dateTextView = view.earthquake_date
    private val magnitudeTextView = view.earthquake_magnitude
    private val depthTextView = view.earthquake_depth
    private val highMagnitudeIcon = view.earthquake_high_magnitude_icon

    fun bind(item: EarthquakeModel) {
        if (item.datetime == null) {
            dateTextView.visibility = View.GONE
        } else {
            dateTextView.text = itemView.resources.getString(R.string.earthquake_date, item.datetime.toString())
            dateTextView.visibility = View.VISIBLE
        }
        magnitudeTextView.text = itemView.resources.getString(R.string.earthquake_magnitude, item.magnitude.toString())
        if (item.isHighMagnitude) {
            highMagnitudeIcon.visibility = View.VISIBLE
        } else {
            highMagnitudeIcon.visibility = View.GONE
        }
        depthTextView.text = itemView.resources.getString(R.string.earthquake_depth, item.depth.toString())
        itemView.setOnClickListener { callback.onClick(item)}
    }
}

class EarthquakeClickedListener(val block: (EarthquakeModel) -> Unit) {
    fun onClick(earthquake: EarthquakeModel) = block(earthquake)
}