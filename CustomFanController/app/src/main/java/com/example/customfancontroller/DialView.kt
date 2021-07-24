package com.example.customfancontroller

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.lang.Float.min
import java.lang.Math.cos
import java.lang.Math.sin

//Note that this enum is of type Int because the values are string resources rather than actual strings.
private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    //These values are created and initialized here instead of when the view is actually drawn to ensure
    // that the actual drawing step runs as fast as possible.

    private var radius = 0.0f                   // Radius of the circle.
    private var fanSpeed = FanSpeed.OFF         // The active selection.
    // position variable which will be used to draw label and indicator circle position
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = (kotlin.math.min(width, height) / 2.0 * 0.8).toFloat()
    }
    //PointF class has parameters for two float coordinates
    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        //"pos.ordinal" simply specifies the position of each FanSpeed in the enum class i.e 0,1,2,3 etc.
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        //x and y are properties of the views
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Set dial background color to green if selection not off.
        paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
        //The width and height properties are members of the View superclass and indicate the current dimensions of the view.
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        // Draw the indicator circle.
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius/12, paint)

        // Draw the text labels.
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }

    }

}