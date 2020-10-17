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
    private var tan = floatArrayOf(0.0F, 0.0F)
    private var pos = floatArrayOf(0.0F, 0.0F)

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
        //绘制二阶贝塞尔曲线
        path.moveTo(-waveLength + dx, 500F)
        do {
            path.rQuadTo(halfWaveLength / 2, -150F, halfWaveLength, 0F)
            path.rQuadTo(halfWaveLength / 2, 150F, halfWaveLength, 0F)
            index += waveLength
        } while (index < width + waveLength)

        //绘制圆形
        circlePath.reset()
        circlePath.addCircle(width.div(2F), 1000F, 200F, Path.Direction.CW)
    }

    private fun startAnimation() {
        //属性动画
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
        mMatrix.reset()
        pathMeasure = PathMeasure(path, false)
        length = pathMeasure.length

        /*
        * 因为路径是不规则的，为了让小船在移动的时候，紧贴着路径，所以我们需要移动小船并旋转一定的角度
        * 为了达到目的，有两种方法
        */

        /*
        //方法一：自己计算
        //获取Path指定点的的位置，和正切值tan
        pathMeasure.getPosTan(boatDx, pos, tan)
        //将tan值通过反三角函数得到对应的弧度，然后将弧度转换成度数
		var degrees : Float = ((Math.atan2(tan[1].toDouble(), tan[0].toDouble())*180f/Math.PI).toFloat())
        //旋转
		mMatrix.postRotate(degrees , bitmap.width/2F, bitmap.height/2F)
        //移动
		mMatrix.postTranslate((pos[0]-bitmap.width/2F).toFloat(), (pos[1]-bitmap.height).toFloat())
		*/

        //方法二：使用API
        //getMatrix()，获取Path指定位置的Matrix
        pathMeasure.getMatrix(boatDx, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG
                or PathMeasure.POSITION_MATRIX_FLAG)
        mMatrix.preTranslate((-bitmap.width / 2).toFloat(), (-bitmap.height).toFloat())
        canvas.drawPath(path, paint)
        canvas.drawBitmap(bitmap, mMatrix, paint)

        circleMatrix.reset()
        circlePathMeasure = PathMeasure(circlePath, false)
        circleLength = circlePathMeasure.length
        circlePathMeasure.getMatrix(circleDx, circleMatrix, PathMeasure.TANGENT_MATRIX_FLAG
                or PathMeasure.POSITION_MATRIX_FLAG)
        circleMatrix.preTranslate((-bitmap.width / 2).toFloat(), (-bitmap.height).toFloat())
        canvas.drawPath(circlePath, paint)
        canvas.drawBitmap(bitmap, circleMatrix, paint)


    }


}