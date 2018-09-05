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
                show()
            } finally {
                recycle()
            }
        }
    }

    private val hideAnimationListener = object : Animation.AnimationListener {
        override fun onAnimationRepeat(p0: Animation?) = Unit

        override fun onAnimationEnd(animation: Animation?) {
            visibility = View.INVISIBLE
            isMinimized = true
        }

        override fun onAnimationStart(animation: Animation?) = Unit
    }


    fun show(backgroundColor: Int? = null, textColor: Int? = null, newText: String? = null) {
        if (isMinimized) {
            visibility = View.VISIBLE
            isMinimized = false
        }
        backgroundColor?.let { drawableColor = it }
        setTextColor(textColor ?: currentTextColor)
        newText?.let { text = it }
        val drawable = DrawableBuilder()
                .rectangle()
                .solidColor(drawableColor)
                .bottomLeftRadius(bottomLeftRadius) // in pixels
                .bottomRightRadius(bottomRightRadius) // in pixels
                .topLeftRadius(topLeftRadius)
                .topRightRadius(topRightRadius)
                .build()
        setBackgroundDrawable(drawable)
    }


    fun showUp(backgroundColor: Int? = null, textColor: Int? = null, newText: String? = null) {
        animateShowHide(backgroundColor, textColor, newText, R.anim.slide_up_down_hide, R.anim.slide_up_down_show)
    }

    fun showDown(backgroundColor: Int? = null, textColor: Int? = null, newText: String? = null) {
        animateShowHide(backgroundColor, textColor, newText, R.anim.slide_down_up_hide, R.anim.slide_down_up_show)
    }

    fun hideUp() {
        hide(R.anim.slide_up_down_hide)
    }


    fun hideDown() {
        hide(R.anim.slide_down_up_hide)
    }

    fun hide() {
        visibility = View.INVISIBLE
        isMinimized = true
    }

    private fun hide(hide: Int) {
        if (isMinimized)
            return
        val anim = AnimationUtils.loadAnimation(context, hide)
        anim.setAnimationListener(hideAnimationListener)
        startAnimation(anim)
    }

    private fun animateShowHide(backgroundColor: Int? = null, textColor: Int? = null, newText: String? = null, hide: Int, show: Int) {
        if (isMinimized) {
            show(backgroundColor, textColor, newText)
            startAnimation(AnimationUtils.loadAnimation(context, show))
            isMinimized = false
        } else {
            val animSlideUp = AnimationUtils.loadAnimation(context, hide)
            animSlideUp.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) = Unit

                override fun onAnimationEnd(animation: Animation?) {
                    show(backgroundColor, textColor, newText)
                    startAnimation(AnimationUtils.loadAnimation(context, show))
                    isMinimized = false
                }

                override fun onAnimationStart(animation: Animation?) = Unit
            })
            startAnimation(animSlideUp)
        }
    }
}