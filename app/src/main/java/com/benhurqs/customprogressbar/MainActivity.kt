package com.benhurqs.customprogressbar

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClicChangeProgressColor(view: View){

    }
    

    fun convertStringToColor(colorText: String): Int{
        var color = Color.parseColor("#FFFFFF");
        try{
            color = Color.parseColor(colorText);
        }catch (e: Exception){
            Toast.makeText(this, "Invalid color", Toast.LENGTH_SHORT).show()
        }

        return color;
    }
}
