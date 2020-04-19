package com.example.progressfeaturesample.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.example.progressfeaturesample.R

/**
 * Вью для отрисовки пошагового прогресса
 */
class StepProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private var lastActiveStep = 0
    private var currentSegmentProgressInPx = 0

    private var stepCount: Int
    private var activeColor: Int
    private var inactiveColor: Int

    private var inactiveRectPaint: Paint
    private var activeRectPaint: Paint

    private var segmentGapWidth: Int = resources.getDimensionPixelSize(R.dimen.dp4)
    private var segmentWidth: Int = resources.getDimensionPixelSize(R.dimen.dp8)
//        get() = width / stepCount - segmentGapWidth

    init {
        val styledAttrs = context.theme.obtainStyledAttributes(attrs, R.styleable.StepProgressBar, 0, 0)
        stepCount = styledAttrs.getInt(R.styleable.StepProgressBar_stepCount, 5)
        activeColor = styledAttrs.getColor(R.styleable.StepProgressBar_activeColor, Color.BLUE)
        inactiveColor = styledAttrs.getColor(R.styleable.StepProgressBar_inactiveColor, Color.LTGRAY)
        segmentGapWidth = styledAttrs.getDimensionPixelSize(R.styleable.StepProgressBar_gapBetween, segmentGapWidth)
        segmentWidth = styledAttrs.getDimensionPixelSize(R.styleable.StepProgressBar_segmentWidth, segmentWidth)
        inactiveRectPaint = buildPaint(inactiveColor)
        activeRectPaint = buildPaint(activeColor)
        styledAttrs.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        draw(canvas, inactiveRectPaint, stepCount)
        draw(canvas, activeRectPaint, lastActiveStep)
    }

    fun setActiveColor(@ColorInt color: Int) {
        activeRectPaint = buildPaint(color)
    }

    fun setInactiveColor(@ColorInt color: Int) {
        inactiveRectPaint = buildPaint(color)
    }

    fun setStepCount(stepCount: Int) {
        this.stepCount = stepCount
    }

    fun setCurrentStepNum(stepNum: Int) {
        if (stepNum <= stepCount) {
            currentSegmentProgressInPx = 0
            lastActiveStep = stepNum
            invalidate()
        }
    }

    private fun draw(canvas: Canvas, paint: Paint, endRange: Int) {
        val segmentWidth = segmentWidth

        var leftX = width / 2 - (segmentWidth * stepCount + segmentGapWidth * (stepCount - 1)) / 2
        var rightX = leftX + segmentWidth
        val topY = 0
        val botY = height

        for (i in 0 until endRange) {
            drawRoundedRect(canvas, leftX.toFloat(), topY.toFloat(), rightX.toFloat(), botY.toFloat(), paint)
            leftX += segmentWidth + segmentGapWidth
            rightX = leftX + segmentWidth
        }
    }

    private fun drawRoundedRect(canvas: Canvas, left: Float, top: Float, right: Float, bottom: Float, paint: Paint) {
        val path = Path()
        var rx = 6f
        var ry = 6f
        val width = right - left
        val height = bottom - top
        if (rx > width / 2) rx = width / 2
        if (ry > height / 2) ry = height / 2
        val widthMinusCorners = width - 2 * rx
        val heightMinusCorners = height - 2 * ry

        with(path) {
            moveTo(right, top + ry)
            rQuadTo(0f, -ry, -rx, -ry)
            rLineTo(-widthMinusCorners, 0f)
            rQuadTo(-rx, 0f, -rx, ry)
            rLineTo(0f, heightMinusCorners)

            rQuadTo(0f, ry, rx, ry)
            rLineTo(widthMinusCorners, 0f)
            rQuadTo(rx, 0f, rx, -ry)

            rLineTo(0f, -heightMinusCorners)

            close()
        }

        canvas.drawPath(path, paint)
    }

    private fun buildPaint(@ColorInt color: Int): Paint {
        val paint = Paint()
        paint.color = color
        paint.style = Paint.Style.FILL
        return paint
    }
}