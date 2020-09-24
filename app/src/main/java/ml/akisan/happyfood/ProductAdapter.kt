package ml.akisan.happyfood

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.customview.widget.ViewDragHelper.create
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import androidx.core.util.Pair


class ProductAdapter (options: FirestoreRecyclerOptions<FoodItem>, c: Context, c2 : Activity) : FirestoreRecyclerAdapter<FoodItem, ProductAdapter.ItemHolder>(options) {
    val context = c
    val mainActivity = c2

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemName : TextView
        var itemValue : Float = 0.0f
        var imageURL : String = "null"
        var URL : String = "null"
        var itemProducer : String = "null"
        var itemCountry : String = "null"
        var description : String = "null"
        var itemCarb : Float = 0.0f
        var gluten : Boolean = false
        var itemImage : ImageView


        init {
            itemImage = itemView.findViewById(R.id.productImage)
            itemName = itemView.findViewById(R.id.item_name)

            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    // Kortet er trykkbar mens den fjenes

                    /*ContextCompat.startActivity(
                        context,
                        Intent(context, FoodDetail::class.java).putExtra(
                            "PRODUCT",
                            FoodItem(itemName.text.toString(), itemValue, imageURL, URL, itemProducer, itemCountry, description, itemCarb, gluten)
                        ),
                        null
                    )*/

                    val intent = Intent(context, FoodDetail::class.java).putExtra(
                        "PRODUCT",
                        FoodItem(itemName.text.toString(), itemValue, imageURL, URL, itemProducer, itemCountry, description, itemCarb, gluten)
                    )
                    val p1: Pair<View, String> = Pair.create(itemImage as View, "productImage")
                    val p2: Pair<View, String> = Pair.create(itemName as View, "item_name")
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(mainActivity, p1, p2)
                    startActivity(context, intent, options.toBundle())
                    //val snapshot = snapshots.getSnapshot(pos) , pos // Det vi skal sende videre
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card,
            parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int, model: FoodItem) {
        if (model.navn == "null") holder.itemName.text = "Could not find name, please reload".toString()
        else holder.itemName.text = model.navn

        holder.itemValue = model.verdi
        holder.imageURL = model.imageURL
        holder.URL = model.URL
        holder.itemProducer = model.produsent
        holder.itemCountry = model.land
        holder.description = model.beskrivelse
        holder.itemCarb = model.karbohydrat
        holder.gluten = model.gluten

        if (model.imageURL == "null") Glide.with(context).load(R.drawable.defaultimg).into(holder.itemImage)
        else Glide.with(context).load(model.imageURL).into(holder.itemImage)
    }
}