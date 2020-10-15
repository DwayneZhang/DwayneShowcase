package com.dwayne.com.showcase.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.dwayne.com.showcase.R


/**
 *
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/10/11 20:11
 * @change
 * @chang time
 * @class describe
 */

class WaveView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var paint: Paint
    private lateinit var path: Path
    private lateinit var circlePath: Path
    private lateinit var pathMeasure: PathMeasure
    private lateinit var circlePathMeasure: PathMeasure
    private lateinit var bitmap: Bitmap
    private var length: Float = 0F
    private var circleLength: Float = 0F
    private var waveLength: Float = 400F
    private var dx: Int = 0
    private var boatDx: Float = 0F
    private var circleDx: Float = 0F
    private var mMatrix = Matrix()
    private var circleMatrix = Matrix()

    init {
        paint = Paint()
        paint.strokeWidth = 6F
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        path = Path()
        var options = BitmapFactory.Options()
        options.inSampleSize = 6
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_boat, options)

        circlePath = Path()

        startAnimation()
    }

    private fun setPathData() {
        path.reset()
        var halfWaveLength = waveLength / 2
        var index = -waveLength
        path.moveTo(-waveLength + dx, 500F)
        do {
            path.rQuadTo(halfWaveLength / 2, -150F, halfWaveLength, 0F)
            path.rQuadTo(halfWaveLength / 2, 150F, halfWaveLength, 0F)
            index += waveLength
        } while (index < width + waveLength)

        circlePath.addCircle(width.div(2F), 1000F, 200F, Path.Direction.CW)
    }

    private fun startAnimation() {
        var animator = ValueAnimator.ofInt(0, 1000)
        animator.duration = 3000
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.RESTART
        animator.addUpdateListener { animation: ValueAnimator ->
            dx = (animation.animatedValue as Int * waveLength / 1000).toInt()
            boatDx = animation.animatedValue as Int * (length - waveLength) / 1000
            circleDx = animation.animatedValue as Int * circleLength / 1000
            invalidate()
        }
        animator.start();
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setPathData()
        pathMeasure = PathMeasure(path, false)
        length = pathMeasure.length
        mMatrix.reset()
        pathMeasure.getMatrix(boatDx, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG
                or PathMeasure.POSITION_MATRIX_FLAG)
        mMatrix.preTranslate((-bitmap.width / 2).toFloat(), (-bitmap.height).toFloat())
        canvas.drawPath(path, paint)
        canvas.drawBitmap(bitmap, mMatrix, paint)

        circlePathMeasure = PathMeasure(circlePath, false)
        circleLength = circlePathMeasure.length
        circlePathMeasure.getMatrix(circleDx, circleMatrix, PathMeasure.TANGENT_MATRIX_FLAG
                or PathMeasure.POSITION_MATRIX_FLAG)
        circleMatrix.preTranslate((-bitmap.width / 2).toFloat(), (-bitmap.height).toFloat())
        canvas.drawPath(circlePath, paint)
        canvas.drawBitmap(bitmap, circleMatrix, paint)


    }


}