package ml.akisan.happyfood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ProductAdapter (options: FirestoreRecyclerOptions<FoodItem>, c: Context) : FirestoreRecyclerAdapter<FoodItem, ProductAdapter.ItemHolder>(options) {
    val context = c

    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage : ImageView
        var itemName : TextView

        init {
            itemImage = itemView.findViewById(R.id.productImage)
            itemName = itemView.findViewById(R.id.item_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card,
            parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int, model: FoodItem) {
        holder.itemName.text = model.navn
        Glide.with(context).load(model.imageURL).into(holder.itemImage)
    }
}