package com.example.minipaint

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat

private lateinit var extraCanvas: Canvas
private lateinit var extraBitmap: Bitmap
private const val STROKE_WIDTH = 12f // has to be float

private var motionTouchEventX = 0f
private var motionTouchEventY = 0f

private var currentX = 0f
private var currentY = 0f

class MyCanvasView(context: Context) : View(context) {

    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    private lateinit var frame: Rect


    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        //resources can only be declared inside the class
        var backgroundColor = ResourcesCompat.getColor(resources ,R.color.colorBackground, null)

        // So we create a caching bitmap and caching canvas b/c it is best practice even though it doesn't apply for this app
        //This can be useful if users want to undo drawings
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        //"Bitmap.Config.ARGB_8888" tells the bitmap to store each pixel as 4 bytes and is the recommended configuration
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)

        // Calculate a rectangular frame around the picture.
        //An "inset" is the distance a drawable has from another drawable. Can be useful in generating shadows since
        //you can specify how thick the shadow is using insets
        val inset = 40
        frame = Rect(inset, inset, width - inset, height - inset)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)

        // Draw a frame around the canvas.
        canvas.drawRect(frame, paint)
    }
    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }
    //The Path class encapsulates geometric paths consisting of straight line segments, quadratic curves, and cubic curves.
    private var path = Path()
    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        //tracks the last place the touch event occured
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        //If the movement was further than the touch tolerance, add a segment to the path.
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
                //Using quadTo() instead of lineTo() create a smoothly drawn line without corners. See Bezier Curves.
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to cache it.
            extraCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
       }
    /*
    In MyCanvasView, override the onTouchEvent() method to cache the x and y coordinates of the passed in event.
    Then use a when expression to handle motion events for touching down on the screen, moving on the screen,
    and releasing touch on the screen.
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }
}