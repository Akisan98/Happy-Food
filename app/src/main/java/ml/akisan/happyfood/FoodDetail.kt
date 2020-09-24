package ml.akisan.happyfood

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.github.florent37.androidslidr.Slidr
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class FoodDetail : AppCompatActivity() {
    private lateinit var nestedScroll : NestedScrollView
    private lateinit var myHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        val product = intent.getParcelableExtra<FoodItem>("PRODUCT")

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = product?.navn
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        nestedScroll = findViewById<NestedScrollView>(R.id.nestedScroll)


        //title = product?.navn

        val navn = findViewById<TextView>(R.id.navn)
        val verdi = findViewById<TextView>(R.id.laktose)
        val bilde = findViewById<ImageView>(R.id.bilde)
        val url = findViewById<TextView>(R.id.url)
        val produsent = findViewById<TextView>(R.id.produsent)
        val land = findViewById<TextView>(R.id.land)
        val beskrivelse = findViewById<TextView>(R.id.beskrivelse)
        val karbohydrat = findViewById<TextView>(R.id.karbohydrat)
        val gluten = findViewById<TextView>(R.id.gluten)
        val laktose = findViewById<TextView>(R.id.laktose2)

        val bigImage = findViewById<ImageView>(R.id.productImage)
        val website = findViewById<ExtendedFloatingActionButton>(R.id.extended_fab)

        val slider = findViewById<Slidr>(R.id.slider)

        slider.max = 500F
        slider.setTextFormatter {
            "%.1f".format(it).toString() + "g"
        }
        slider.currentValue = 100f
        slider.setListener(object : Slidr.Listener {
            override fun valueChanged(slidr: Slidr, currentValue: Float) {
                verdi.text = "%.2f".format((product?.verdi?.div(100))?.times(currentValue)).toString() + "g"
                verdi.setTypeface(verdi.getTypeface(), Typeface.BOLD)
                karbohydrat.text = "%.2f".format((product?.karbohydrat?.div(100))?.times(currentValue)).toString() + "g"
                karbohydrat.setTypeface(karbohydrat.getTypeface(), Typeface.BOLD)
            }
            override fun bubbleClicked(slidr: Slidr) {

            }
        })



        website.setOnClickListener {
            val uri: Uri = Uri.parse(product?.URL)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        navn.text = product?.navn
        verdi.text = product?.verdi.toString() + "g / 100g"
        //Glide.with(this).load(product?.imageURL).into(bilde)
        Glide.with(this).load(product?.imageURL).into(bigImage)
        url.text = product?.URL
        produsent.text = product?.produsent
        land.text = product?.land
        beskrivelse.text = product?.beskrivelse
        karbohydrat.text = product?.karbohydrat.toString() + "g / 100g"
        if (product?.gluten == true) gluten.visibility = View.VISIBLE
        else gluten.visibility = View.GONE

        if (product?.verdi!! > 2.0f) laktose.visibility = View.VISIBLE
        else laktose.visibility = View.INVISIBLE

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        /*if (id == android.R.id.home) {
            //onBackPressed()
            supportFinishAfterTransition();
            return true
        }*/
        when (id) {
            android.R.id.home -> {
                nestedScroll.fullScroll(NestedScrollView.FOCUS_UP)
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}