package gr.osnet.statusbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import top.defaults.drawabletoolbox.DrawableBuilder


class StatusBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextView(context, attrs, defStyleAttr) {
    var bottomLeftRadius = 0
    var bottomRightRadius = 0
    var topLeftRadius = 0
    var topRightRadius = 0
    var drawableColor = 0

    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.StatusBar,
                0, 0).apply {
            visibility = View.INVISIBLE

            try {
                bottomLeftRadius = getInteger(R.styleable.StatusBar_bottomLeftRadius, 0)
                bottomRightRadius = getInteger(R.styleable.StatusBar_bottomRightRadius, 0)
                topLeftRadius = getInteger(R.styleable.StatusBar_topLeftRadius, 0)
                topRightRadius = getInteger(R.styleable.StatusBar_topRightRadius, 0)
                drawableColor = getInteger(R.styleable.StatusBar_drawableColor, 0)
            } finally {
                recycle()
            }
        }
    }

    fun slide(backgroundColor: Int? = null, textColor: Int? = null, newText: String? = null) {
        val drawable = DrawableBuilder()
                .rectangle()
                .solidColor(backgroundColor ?: drawableColor)
                .bottomLeftRadius(bottomLeftRadius) // in pixels
                .bottomRightRadius(bottomRightRadius) // in pixels
                .topLeftRadius(topLeftRadius)
                .topRightRadius(topRightRadius)
                .build()

        val animSlideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        animSlideUp.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                newText?.let { text = it }
                setBackgroundDrawable(drawable)
                justApply(backgroundColor)
                setTextColor(textColor ?: currentTextColor)
                startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_down))
            }

            override fun onAnimationStart(animation: Animation?) = Unit
        })
        startAnimation(animSlideUp)
    }

    fun justApply(backgroundColor: Int? = null) {
        animation?.cancel()
        visibility = View.VISIBLE

        val drawable = DrawableBuilder()
                .rectangle()
                .solidColor(backgroundColor ?: drawableColor)
                .bottomLeftRadius(bottomLeftRadius) // in pixels
                .bottomRightRadius(bottomRightRadius) // in pixels
                .topLeftRadius(topLeftRadius)
                .topRightRadius(topRightRadius)
                .build()
        setBackgroundDrawable(drawable)
    }
}