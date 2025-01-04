package com.yandex.practicum.sample.customviews.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.yandex.practicum.sample.customviews.R


class CustomPolylineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {
    private val requiredSize: Int =
        context.resources.getDimensionPixelSize(R.dimen.polyline_view_size)
    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2f, height / 2f, requiredSize / 2f, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        Log.d("CustomPolylineView", "Width mode: ${modeToString(widthMode)}, size: $widthSize")
        Log.d("CustomPolylineView", "Height mode: ${modeToString(heightMode)}, size: $heightSize")

        val width = determineSize(widthMode, widthSize)
        val height = determineSize(heightMode, heightSize)

        Log.d("CustomPolylineView", "Measured width: $width, Measured height: $height")

        setMeasuredDimension(width, height)
    }

    private fun determineSize(receivedMode: Int, receivedSize: Int) = when (receivedMode) {
        MeasureSpec.EXACTLY -> receivedSize
        MeasureSpec.AT_MOST -> minOf(requiredSize, receivedSize)
        MeasureSpec.UNSPECIFIED -> requiredSize
        else -> requiredSize
    }

    private fun modeToString(mode: Int): String {
        return when (mode) {
            MeasureSpec.EXACTLY -> "EXACTLY"
            MeasureSpec.AT_MOST -> "AT_MOST"
            MeasureSpec.UNSPECIFIED -> "UNSPECIFIED"
            else -> "UNKNOWN"
        }
    }
}
