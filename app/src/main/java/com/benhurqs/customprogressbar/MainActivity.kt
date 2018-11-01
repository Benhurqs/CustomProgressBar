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
import android.os.CountDownTimer
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val totalTime = 15000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress.progress = 50
        edt_background.setText("#FF0000")
        edt_progress.setText("#00FF00")
        progressColor()
    }

    fun onClickChangeProgressColor(view: View){
        changeProgressBG(
            progressbar = progress,
            onColor = convertStringToColor(edt_progress.text.toString()),
            offColor = convertStringToColor(edt_background.text.toString())
        )
    }

    private fun changeProgressBG(progressbar: ProgressBar, onColor: Int, offColor: Int){
        progressbar.progressDrawable = this.resources.getDrawable(R.drawable.custom_progressbar);
        val layerDrawable = progressbar.progressDrawable as LayerDrawable

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

        return color
    }

    private fun progressColor(){
        val countDouwn = object :CountDownTimer(totalTime, 100){
            override fun onFinish() {
                btn_reset.visibility = View.VISIBLE
            }

            override fun onTick(millisUntilFinished: Long) {
                btn_reset.visibility = View.INVISIBLE
                val percent= (millisUntilFinished*100/totalTime)

                progress_count.progress = percent.toInt()

                val offColor = Color.parseColor("#C1C1C1")
                var progressColor = Color.rgb(255,0,0)
                if(percent < 50){
                    val greenColor = (255*percent.toInt()*2)/100
                    progressColor = Color.rgb(255, greenColor, 0)
                }else{
                    val redColor = (255*2*(100 - percent.toInt()))/100
                    progressColor = Color.rgb( redColor, 255, 0)
                }

                changeProgressBG(
                    progressbar = progress_count,
                    onColor = progressColor,
                    offColor = offColor
                )

            }
        }

        countDouwn.start()
    }

    fun onClickReset(view: View){
        progress_count.progress = 0
        progressColor()
    }
}
