package com.applab.ubike_search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun search(view: View) {
        val text = input_distance.text
        if(text.isEmpty()) {
            Toast.makeText(this, R.string.main_input_incorrect, Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, UbikeActivity::class.java)
        intent.putExtra("distance", text.toString().toInt())
        startActivity(intent)
    }
}