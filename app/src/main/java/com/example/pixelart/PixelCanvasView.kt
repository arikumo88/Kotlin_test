package com.example.pixelart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Custom view that draws layers of pixel art onto a canvas.
 * Features are deliberately simplified for the prototype.
 */
class PixelCanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private val layers = mutableListOf<Bitmap>()
    private var currentLayer = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888)
    private val paint = Paint()
    private var maskPath: Path? = null

    init {
        layers += currentLayer
    }

    fun addLayer() {
        currentLayer = Bitmap.createBitmap(width.coerceAtLeast(64), height.coerceAtLeast(64), Bitmap.Config.ARGB_8888)
        layers += currentLayer
        invalidate()
    }

    fun setMask(path: Path?) {
        maskPath = path
        invalidate()
    }

    fun drawPixel(x: Int, y: Int, color: Int) {
        if (x in 0 until currentLayer.width && y in 0 until currentLayer.height) {
            currentLayer.setPixel(x, y, color)
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        layers.forEach { layerBitmap ->
            canvas.drawBitmap(layerBitmap, 0f, 0f, paint)
        }
        maskPath?.let { path ->
            paint.style = Paint.Style.STROKE
            paint.color = Color.RED
            canvas.drawPath(path, paint)
        }
    }
}
