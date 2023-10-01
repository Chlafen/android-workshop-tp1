package com.example.commande_pizza

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var fname: TextInputLayout
    private lateinit var lname: TextInputLayout
    private lateinit var adr:   TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fname = findViewById(R.id.editFirstname)
        lname = findViewById(R.id.editLastname)
        adr = findViewById(R.id.editAddress)

    }

    fun submitPersonal(view: View) {
        val vfname = fname.editText?.text.toString()
        val vlname = lname.editText?.text.toString()
        val vadr = adr.editText?.text.toString()
        if(vfname.isEmpty() || vlname.isEmpty() || vadr.isEmpty()){
            Toast.makeText(this, "Please Fill out all the fields: $vadr $vfname $vlname", Toast.LENGTH_SHORT).show()
        }else{
            val intent = Intent(this@MainActivity, CustomPizzaActivity::class.java)
            intent.putExtra("name", "$vfname $vlname")
            intent.putExtra("address", vadr)
            startActivity(intent)
        }
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            // Get the currently focused View
            val focusedView = currentFocus
            if (focusedView is View) {
                // Check if the touch event was inside the currently focused View
                val rect = Rect()
                focusedView.getGlobalVisibleRect(rect)
                if (!rect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    // The touch event was outside the currently focused View,
                    // so hide the soft keyboard
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}