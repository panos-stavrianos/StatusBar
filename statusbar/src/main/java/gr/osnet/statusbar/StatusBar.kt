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
    var showOnStart = true
    var isMinimized: Boolean = false
    val drawable
        get() = DrawableBuilder()
                .rectangle()
                .solidColor(drawableColor)
                .bottomLeftRadius(bottomLeftRadius) // in pixels
                .bottomRightRadius(bottomRightRadius) // in pixels
                .topLeftRadius(topLeftRadius)
                .topRightRadius(topRightRadius)
                .build()

    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.StatusBar,
                0, 0).apply {
            try {
                bottomLeftRadius = getDimensionPixelSize(R.styleable.StatusBar_bottomLeftRadius, 0)
                bottomRightRadius = getDimensionPixelSize(R.styleable.StatusBar_bottomRightRadius, 0)
                topLeftRadius = getDimensionPixelSize(R.styleable.StatusBar_topLeftRadius, 0)
                topRightRadius = getDimensionPixelSize(R.styleable.StatusBar_topRightRadius, 0)
                drawableColor = getColor(R.styleable.StatusBar_drawableColor, 0)
                showOnStart = getBoolean(R.styleable.StatusBar_drawableColor, true)
                justApply()
            } finally {
                recycle()
            }
        }
    }

    fun slideUpDown(backgroundColor: Int? = null, textColor: Int? = null, newText: String? = null) {
        animateShowHide(backgroundColor, textColor, newText, R.anim.slide_up_down_hide, R.anim.slide_up_down_show)
    }

    fun slideDownUp(backgroundColor: Int? = null, textColor: Int? = null, newText: String? = null) {
        animateShowHide(backgroundColor, textColor, newText, R.anim.slide_down_up_hide, R.anim.slide_down_up_show)
    }

    private fun animateShowHide(backgroundColor: Int? = null, textColor: Int? = null, newText: String? = null, hide: Int, show: Int) {
        backgroundColor?.let { drawableColor = it }
        if (isMinimized) {
            visibility = View.VISIBLE
            newText?.let { text = it }
            setTextColor(textColor ?: currentTextColor)
            setBackgroundDrawable(drawable)
            startAnimation(AnimationUtils.loadAnimation(context, show))
            isMinimized = false
        } else {
            val animSlideUp = AnimationUtils.loadAnimation(context, hide)
            animSlideUp.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) = Unit

                override fun onAnimationEnd(animation: Animation?) {
                    newText?.let { text = it }
                    setTextColor(textColor ?: currentTextColor)
                    setBackgroundDrawable(drawable)
                    startAnimation(AnimationUtils.loadAnimation(context, show))
                    isMinimized = false
                }

                override fun onAnimationStart(animation: Animation?) = Unit
            })
            startAnimation(animSlideUp)
        }
    }

    fun hideUp() {
        if (isMinimized)
            return
        val anim = AnimationUtils.loadAnimation(context, R.anim.slide_up_down_hide)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                visibility = View.INVISIBLE
                isMinimized = true
            }

            override fun onAnimationStart(animation: Animation?) = Unit
        })
        startAnimation(anim)
    }


    fun hideDown() {
        if (isMinimized)
            return
        val anim = AnimationUtils.loadAnimation(context, R.anim.slide_down_up_hide)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                visibility = View.INVISIBLE
                isMinimized = true
            }

            override fun onAnimationStart(animation: Animation?) = Unit
        })
        startAnimation(anim)
    }

    fun justApply(backgroundColor: Int? = null) {
        backgroundColor?.let { drawableColor = it }
        animation?.cancel()
        visibility = View.VISIBLE
        setBackgroundDrawable(drawable)
    }
}