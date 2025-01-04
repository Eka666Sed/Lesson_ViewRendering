package com.yandex.practicum.sample.customviews.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


class CustomPolylineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val path = Path()

    init {
        path.moveTo(50f, 50f)
        path.lineTo(150f, 100f)
        path.lineTo(250f, 50f)
        path.lineTo(350f, 150f)
        path.lineTo(250f, 200f)
        path.lineTo(150f, 250f)
        path.lineTo(50f, 50f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()

        canvas.translate(100f, 100f)

        // canvas.clipRect(0f, 0f, width.toFloat() / 4, height.toFloat() / 4)

        canvas.drawPath(path, paint)

        canvas.restore()
    }
}
