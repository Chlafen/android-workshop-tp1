package com.example.commande_pizza

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView


class SendOrderActivity : AppCompatActivity() {
    private lateinit var size:String
    private lateinit var name:String
    private lateinit var adr:String
    private lateinit var ingredients:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Send Order"
        size = intent.getStringExtra("size").toString()
        name = intent.getStringExtra("name").toString()
        adr = intent.getStringExtra("address").toString()
        ingredients = intent.getStringArrayListExtra("ingredients").toString()
        ingredients = ingredients.substring(1, ingredients.length - 1).replace(',', ' ')

        findViewById<MaterialTextView>(R.id.nameDisplay).text = name
        findViewById<MaterialTextView>(R.id.addressDisplay).text = adr
        findViewById<MaterialTextView>(R.id.sizeDisplay).text = size
        findViewById<MaterialTextView>(R.id.ingredientsDisplay).text = ingredients

    }


    private fun constructMessageBody():String{
        return buildString {
            append("Name: $name")
            append('\n')
            append("Address: $adr")
            append('\n')
            append("Pizza Size: $size")
            append('\n')
            append("Ingredients: $ingredients")
            append('\n')
        }
    }

    fun sendSms(view: View) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:")
            putExtra("sms_body", constructMessageBody())
        }
        try {
            startActivity(intent)
        }catch (e:ActivityNotFoundException){
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show()
        }
    }
    fun sendEmail(view: View) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, "Pizza $size Command")
            putExtra(Intent.EXTRA_TEXT, constructMessageBody())
        }
        try {
            startActivity(intent)
        }catch (e:ActivityNotFoundException){
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show()
        }
    }
}