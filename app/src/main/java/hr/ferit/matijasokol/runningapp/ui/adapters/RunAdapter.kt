package hr.ferit.matijasokol.runningapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hr.ferit.matijasokol.runningapp.R
import hr.ferit.matijasokol.runningapp.models.Run
import hr.ferit.matijasokol.runningapp.other.TrackingUtility
import kotlinx.android.synthetic.main.item_run.view.*
import java.text.SimpleDateFormat
import java.util.*

class RunAdapter : ListAdapter<Run, RunAdapter.RunViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Run>() {
            override fun areItemsTheSame(oldItem: Run, newItem: Run): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Run, newItem: Run): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder = RunViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_run,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) = holder.bind(currentList[position])

    inner class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(run: Run) {
            itemView.apply {
                Glide.with(this).load(run.image).into(ivRunImage)

                val calendar = Calendar.getInstance().apply {
                    timeInMillis = run.timestamp
                }
                val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
                tvDate.text = dateFormat.format(calendar.time)

                val avgSpeed = "${run.avgSpeedInKMH}km/h"
                tvAvgSpeed.text = avgSpeed

                val distanceInKm = "${run.distanceInMeters / 1000}km"
                tvDistance.text = distanceInKm

                tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

                val caloriesBurned = "${run.caloriesBurned}kcal"
                tvCalories.text = caloriesBurned
            }
        }
    }
}