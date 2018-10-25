package com.benhurqs.customprogressbar

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.graphics.PorterDuff
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress.progress = 50
        edt_background.setText("#FF0000")
        edt_progress.setText("#00FF00")
    }

    fun onClickChangeProgressColor(view: View){
        changeProgressBG(
            onColor = convertStringToColor(edt_progress.text.toString()),
            offColor = convertStringToColor(edt_background.text.toString())
        )
    }

    private fun changeProgressBG(onColor: Int, offColor: Int){
        progress.progressDrawable = this.resources.getDrawable(R.drawable.custom_progressbar);
        val layerDrawable = progress.progressDrawable as LayerDrawable

        val bgGradientDrawable = layerDrawable
            .findDrawableByLayerId(android.R.id.background) as GradientDrawable
        bgGradientDrawable.setColor(offColor)
        val progressGradientDrawable = layerDrawable
            .findDrawableByLayerId(android.R.id.progress) as ClipDrawable
        progressGradientDrawable.setColorFilter(onColor, PorterDuff.Mode.SRC_IN)
    }

    private fun convertStringToColor(colorText: String): Int{
        var color = Color.parseColor("#FFFFFF")
        try{
            color = Color.parseColor(colorText)
        }catch (e: Exception){
            Toast.makeText(this, "Invalid color", Toast.LENGTH_SHORT).show()
        }

        return color;
    }
}
