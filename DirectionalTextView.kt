package com.muhammetkonukcu.examples.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.muhammetkonukcu.examples.R

/***
 * @author MuhammetKonukcu
 * createdAt 19.04.2025
 */

class DirectionalTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    companion object {
        const val ORIENTATION_UP_TO_DOWN = 0
        const val ORIENTATION_DOWN_TO_UP = 1
        const val ORIENTATION_LEFT_TO_RIGHT = 2
        const val ORIENTATION_RIGHT_TO_LEFT = 3
    }

    private val textBoundsRect = Rect()
    private var orientation = ORIENTATION_UP_TO_DOWN

    init {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.DirectionalTextView).apply {
                orientation = getInt(
                    R.styleable.DirectionalTextView_direction,
                    ORIENTATION_UP_TO_DOWN
                )
                recycle()
            }
        }
        requestLayout()
        invalidate()
    }

    fun setOrientation(newOrientation: Int) {
        orientation = newOrientation
        requestLayout()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        paint.getTextBounds(text.toString(), 0, text.length, textBoundsRect)

        val isHorizontal = orientation == ORIENTATION_LEFT_TO_RIGHT ||
                orientation == ORIENTATION_RIGHT_TO_LEFT

        val measuredW = if (isHorizontal)
            calculateHeightSpec(widthMeasureSpec)
        else
            calculateWidthSpec(widthMeasureSpec)

        val measuredH = if (isHorizontal)
            calculateWidthSpec(heightMeasureSpec)
        else
            calculateHeightSpec(heightMeasureSpec)

        setMeasuredDimension(measuredW, measuredH)
    }

    private fun calculateWidthSpec(measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> {
                val content = textBoundsRect.height() + paddingTop + paddingBottom
                content.coerceAtMost(size)
            }
            else -> textBoundsRect.height() + paddingTop + paddingBottom
        }
    }

    private fun calculateHeightSpec(measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> {
                val content = textBoundsRect.width() + paddingLeft + paddingRight
                content.coerceAtMost(size)
            }
            else -> textBoundsRect.width() + paddingLeft + paddingRight
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        val path = buildTextPath()
        paint.color = currentTextColor
        canvas.drawTextOnPath(text.toString(), path, 0f, 0f, paint)
        canvas.restore()
    }

    private fun buildTextPath(): Path {
        val w = width
        val h = height
        val tW = textBoundsRect.width()
        val tH = textBoundsRect.height()
        val path = Path()

        val (startX, startY, endX, endY) = when (orientation) {
            ORIENTATION_UP_TO_DOWN -> {
                val x = (w - tH) / 2
                val y = (h - tW) / 2
                listOf(x, y, x, (h + tW) / 2)
            }
            ORIENTATION_DOWN_TO_UP -> {
                val x = (w + tH) / 2
                val y = (h + tW) / 2
                listOf(x, y, x, (h - tW) / 2)
            }
            ORIENTATION_LEFT_TO_RIGHT -> {
                val y = (h + tH) / 2
                val x = (w - tW) / 2
                listOf(x, y, (w + tW) / 2, y)
            }
            else /* RIGHT_TO_LEFT */ -> {
                val y = (h - tH) / 2
                val x = (w + tW) / 2
                listOf(x, y, (w - tW) / 2, y)
            }
        }

        path.moveTo(startX.toFloat(), startY.toFloat())
        path.lineTo(endX.toFloat(), endY.toFloat())
        return path
    }
}

