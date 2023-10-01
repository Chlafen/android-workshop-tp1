package com.example.commande_pizza

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.checkbox.MaterialCheckBox

class CustomPizzaActivity : AppCompatActivity() {
    private val sizes = listOf("Maxi", "Moyenne", "Mini")
    private lateinit var name: String
    private lateinit var adr: String
    private lateinit var checkChampignon: MaterialCheckBox
    private lateinit var checkFromage: MaterialCheckBox
    private lateinit var checkThon: MaterialCheckBox
    private lateinit var sizeGrp:RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_pizza)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Customize your Pizza"
        sizeGrp = findViewById(R.id.radioGroup)

        checkChampignon = findViewById(R.id.checkChamignon)
        checkFromage = findViewById(R.id.checkFromage)
        checkThon = findViewById(R.id.checkThon)

        name = intent.getStringExtra("name").toString()
        adr = intent.getStringExtra("address").toString()

    }

    fun submitPizza(view: View) {
        val idx: Int = sizeGrp.indexOfChild(sizeGrp.findViewById(sizeGrp.checkedRadioButtonId))
        val size = sizes[idx]
        val ingredients: ArrayList<String> = arrayListOf()

        if(checkChampignon.isChecked) {
            ingredients.add("Champignon")
        }
        if(checkFromage.isChecked) {
            ingredients.add("Fromage")
        }
        if(checkThon.isChecked) {
            ingredients.add("Thon")
        }

        if(ingredients.isNotEmpty() && size.isNotEmpty()) {
            val intent = Intent(this@CustomPizzaActivity, SendOrderActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("address", adr)
            intent.putExtra("size", size)
            intent.putStringArrayListExtra("ingredients", ingredients)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Please select at least one Ingredient", Toast.LENGTH_SHORT).show()
        }
    }

}