package com.abhishekaudupa.android.customprogressindicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

@Suppress("PrivatePropertyName")
class CustomProgressIndicator(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var color = 0
    private var animationStyle = 0
    private var barCount = 0
    private var barWidthFactor = 0f
    private var animationSpeedFactor = 0

    private var viewYCenter = 0f
    private var viewXCenter = 0f

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var barWidth = 0f
    private var gutterWidth = 0f

    private var maxBarHeight = 0f
    private var minBarHeight = 0f
    private var barHeightArray = arrayListOf<Float>()

    private val FLAG_GROW_BAR = 0
    private val FLAG_UN_GROW_BAR = 1
    private var growFlagsArray = arrayListOf<Int>()
    private var barHeightChange = 0f

    init {
        setAttrs(attrs)
        paint.color = color
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        setViewCenter(w + 0f, h + 0f)
        setGutterWidth(barCount + 1)
        setBarWidth(barWidthFactor, gutterWidth)
        setBarMaxHeight(barWidth)
        setAnimationSpeed(animationSpeedFactor)
        setAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            for (i in 1 until barCount + 1) {
                drawBar(this, i, i)
                drawTopCircle(this, i, i)
                drawBottomCircle(this, i, i)
                pulsate(i)
            }
        }
        invalidate()
    }

    private fun pulsate(currentBar: Int) {
        when (growFlagsArray[currentBar]) {
            FLAG_GROW_BAR -> {
                barHeightArray[currentBar] += barHeightChange
            }
            FLAG_UN_GROW_BAR -> {
                barHeightArray[currentBar] -= barHeightChange
            }
        }
        growFlagsArray[currentBar] = when {
            barHeightArray[currentBar] <= minBarHeight -> {
                FLAG_GROW_BAR
            }
            barHeightArray[currentBar] >= maxBarHeight -> {
                FLAG_UN_GROW_BAR
            }
            else -> {
                growFlagsArray[currentBar]
            }
        }
    }

    private fun drawBar(canvas: Canvas, horOffsetFactor: Int, currentBar: Int) {
        canvas.apply {
            drawLine(
                horOffsetFactor * gutterWidth,
                viewYCenter + 0f,
                horOffsetFactor * gutterWidth,
                viewYCenter - barHeightArray[currentBar],
                paint
            )
            drawLine(
                horOffsetFactor * gutterWidth,
                viewYCenter + 0f,
                horOffsetFactor * gutterWidth,
                viewYCenter + barHeightArray[currentBar],
                paint
            )
        }
    }

    private fun drawTopCircle(canvas: Canvas, horOffsetFactor: Int, currentBar: Int) {
        val rect = RectF()
        rect.left = horOffsetFactor * gutterWidth - barWidth / 2
        rect.right = rect.left + barWidth
        rect.bottom = viewYCenter - barHeightArray[currentBar] + barWidth / 2
        rect.top = rect.bottom - barWidth

        canvas.apply {
            drawArc(rect, 0f, -180f, true, paint)
        }
    }

    private fun drawBottomCircle(canvas: Canvas, horOffsetFactor: Int, currentBar: Int) {
        val rect = RectF()
        rect.left = horOffsetFactor * gutterWidth - barWidth / 2
        rect.right = rect.left + barWidth
        rect.top = viewYCenter + barHeightArray[currentBar] - barWidth / 2
        rect.bottom = rect.top + barWidth

        canvas.apply {
            drawArc(rect, 0f, 180f, true, paint)
        }
    }

    private fun setAttrs(attrs: AttributeSet) {
        val a =
            context.obtainStyledAttributes(attrs, R.styleable.CustomProgressIndicator, 0, 0)
        color = a.getColor(
            R.styleable.CustomProgressIndicator_barColor,
            ContextCompat.getColor(context, android.R.color.darker_gray)
        )
        animationStyle = a.getInteger(
            R.styleable.CustomProgressIndicator_animationStyle,
            resources.getInteger(R.integer.spring)
        )
        barCount = a.getInteger(R.styleable.CustomProgressIndicator_barCount, 5)
        barWidthFactor = a.getFloat(R.styleable.CustomProgressIndicator_barWidthFactor, 0.5f)
        animationSpeedFactor =
            a.getInteger(R.styleable.CustomProgressIndicator_animationSpeedFactor, 60)
        a.recycle()
    }

    private fun setViewCenter(width: Float, height: Float) {
        viewXCenter = width / 2
        viewYCenter = height / 2
    }

    private fun setGutterWidth(gutterCount: Int) {
        gutterWidth = width / gutterCount + 0f
    }

    private fun setBarWidth(widthFactor: Float, gutterWidth: Float) {
        barWidth = gutterWidth * widthFactor
        paint.strokeWidth = barWidth
    }

    private fun setBarMaxHeight(barWidth: Float) {
        maxBarHeight = height / 2 - barWidth / 2
    }

    private fun setAnimationSpeed(speedFactor: Int) {
        barHeightChange = height / speedFactor + 0f
    }

    private fun setAnimation() {
        var barHeightSelectionFlag = 0
        var barHeightIncrementer = 0f
        when (animationStyle) {
            resources.getInteger(R.integer.spring) -> {
                for (i in 0..barCount) {
                    barHeightSelectionFlag = if (barHeightSelectionFlag == 0) {
                        barHeightArray.add(0f)
                        1
                    } else {
                        barHeightArray.add(maxBarHeight)
                        0
                    }
                    growFlagsArray.add(FLAG_GROW_BAR)
                }
            }
            resources.getInteger(R.integer.wave) -> {
                for (i in 0..barCount) {
                    barHeightArray.add(0f + barHeightIncrementer)
                    if (i <= barCount / 2) {
                        barHeightIncrementer += maxBarHeight / barCount
                    } else {
                        barHeightIncrementer -= maxBarHeight / barCount
                    }
                    growFlagsArray.add(FLAG_GROW_BAR)
                }
            }
        }
    }
}