package pakultie.julxian.simpleproject.utils

import androidx.room.Entity
import androidx.room.PrimaryKey
import pakultie.julxian.simpleproject.data.model.SampleItem

@Entity(tableName = "DataItems")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val position: SampleItem,
    val id: String,
    val remarks: String,
    val pickupTime: String,
    val goodsPicture: String,
    val deliveryFee: String,
    val surcharge: String,
    val start: String,
    val end: String,
    val phone: String,
    val name: String,
    val email: String,
    val fav: String,
)
