package com.yandex.practicum.sample.customviews.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
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

        val width = determineSize(widthMode, widthSize)
        val height = determineSize(heightMode, heightSize)

        setMeasuredDimension(width, height)
    }

    private fun determineSize(receivedMode: Int, receivedSize: Int) = when (receivedMode) {
        MeasureSpec.EXACTLY -> receivedSize
        MeasureSpec.AT_MOST -> minOf(requiredSize, receivedSize)
        MeasureSpec.UNSPECIFIED -> requiredSize
        else -> requiredSize
    }

}
