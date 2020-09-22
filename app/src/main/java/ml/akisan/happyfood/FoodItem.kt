package ml.akisan.happyfood

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodItem (
    val navn : String = "null",
    val verdi : Float = 0.0f,
    val imageURL : String = "null",
    val URL : String = "null",
    val produsent : String = "null",
    val land : String = "null",
    val beskrivelse : String = "null",
    val karbohydrat : Float = 0.0f,
    val gluten : Boolean = false
) : Parcelable