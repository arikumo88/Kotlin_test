package com.example.pixelart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Simple color circle that lets the user pick a color.
 * For the prototype the circle is generated manually and selection uses touch.
 */
class ColorCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var listener: ((Int) -> Unit)? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    fun setOnColorChangedListener(cb: (Int) -> Unit) {
        listener = cb
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = (width.coerceAtMost(height) / 2).toFloat()
        val colors = intArrayOf(Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.RED)
        for (i in 0 until colors.size - 1) {
            paint.color = colors[i]
            canvas.drawArc(
                0f, 0f, width.toFloat(), height.toFloat(),
                i * 60f, 60f, true, paint
            )
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
            // Use simple color picking based on angle
            val cx = width / 2f
            val cy = height / 2f
            val angle = Math.atan2((event.y - cy).toDouble(), (event.x - cx).toDouble())
            val deg = Math.toDegrees(angle) + 180
            val color = when ((deg / 60).toInt()) {
                0 -> Color.RED
                1 -> Color.YELLOW
                2 -> Color.GREEN
                3 -> Color.CYAN
                4 -> Color.BLUE
                else -> Color.MAGENTA
            }
            listener?.invoke(color)
        }
        return true
    }
}
