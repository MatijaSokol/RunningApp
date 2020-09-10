package hr.ferit.matijasokol.runningapp.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import hr.ferit.matijasokol.runningapp.other.Constants.RUNNING_TABLE

@Entity(tableName = RUNNING_TABLE)
data class Run(
    var image: Bitmap? = null,
    var timestamp: Long = 0L,
    var avgSpeedInKMH: Float = 0f,
    var distanceInMeters: Int = 0,
    var timeInMillis: Long = 0L,
    var caloriesBurned: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}