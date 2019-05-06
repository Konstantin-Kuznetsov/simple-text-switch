package com.example.konstantin.simpletextswitchexample

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.core.animation.doOnRepeat

fun AnimatorSet.animateTextPadding(topView: TextView, bottomView: TextView, animDuration: Long): AnimatorSet {
    val textSlideDownUpAnimator: ValueAnimator = ValueAnimator.ofInt(topView.paddingTop, topView.height - topView.paddingTop)
    val textSlideUpDownAnimator: ValueAnimator = ValueAnimator.ofInt(bottomView.paddingTop, (bottomView.height + bottomView.paddingBottom) * -1)

    textSlideDownUpAnimator.apply {
        duration = animDuration
        interpolator = AccelerateInterpolator()
        repeatMode = ValueAnimator.REVERSE
        repeatCount = 1
    }

    textSlideUpDownAnimator.apply {
        duration = animDuration
        interpolator = AccelerateInterpolator()
        repeatMode = ValueAnimator.REVERSE
        repeatCount = 1
    }

    textSlideDownUpAnimator.doOnRepeat {
        topView.text = bottomView.text
            .also { bottomView.text = topView.text }
    }

    textSlideDownUpAnimator.addUpdateListener { animation ->
        topView.setPadding(
            topView.paddingLeft,
            animation.animatedValue as Int,
            topView.paddingRight,
            topView.paddingBottom
        )
    }

    textSlideUpDownAnimator.addUpdateListener { animation ->
        bottomView.setPadding(
            bottomView.paddingLeft,
            animation.animatedValue as Int,
            bottomView.paddingRight,
            bottomView.paddingBottom
        )
    }

    return this.apply {
        playTogether(textSlideDownUpAnimator, textSlideUpDownAnimator)
    }
}

fun AnimatorSet.animateTextAlpha(topView: TextView, bottomView: TextView, animDuration: Long): AnimatorSet {
    val animText1 = ValueAnimator.ofInt(255, 0)
    with(animText1) {
        duration = animDuration
        interpolator = LinearInterpolator()
        repeatCount = 1
        repeatMode = ValueAnimator.REVERSE
    }

    animText1.addUpdateListener { animation ->
        topView.setTextColor(topView.textColors.withAlpha(animation.animatedValue as Int))
    }

    val animText2 = ValueAnimator.ofInt(255, 0)
    with(animText2) {
        duration = animDuration
        interpolator = LinearInterpolator()
        repeatCount = 1
        repeatMode = ValueAnimator.REVERSE
    }

    animText2.addUpdateListener { animation ->
        bottomView.setTextColor(bottomView.textColors.withAlpha(animation.animatedValue as Int))
    }

    animText1.doOnRepeat {
        topView.text = bottomView.text
            .also { bottomView.text = topView.text }
    }

    return this.apply {
        playTogether(animText1, animText2)
    }
}