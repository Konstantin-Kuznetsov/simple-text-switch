package com.example.konstantin.simpletextswitchexample

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.animation.doOnRepeat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slideup_slidedown_button.setOnClickListener {
            animatePadding()
        }

        text_fadein_fadeout_button.setOnClickListener {
            animateTextFadeInFadeOut()
        }


    }

    private fun animateTextFadeInFadeOut() {
        val animText1 = ValueAnimator.ofInt(255, 0)
        with(animText1) {
            duration = 200
            interpolator = LinearInterpolator()
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
        }

        animText1.addUpdateListener { animation ->
            buttonText1.setTextColor(buttonText1.textColors.withAlpha(animation.animatedValue as Int))
        }

        val animText2 = ValueAnimator.ofInt(255, 0)
        with(animText2) {
            duration = 200
            interpolator = LinearInterpolator()
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
        }

        animText2.addUpdateListener { animation ->
            buttonText2.setTextColor(buttonText2.textColors.withAlpha(animation.animatedValue as Int))
        }

        animText1.doOnRepeat {
            buttonText1.text = buttonText2.text
                .also { buttonText2.text = buttonText1.text }
        }

        val textFadeInFadeOutAnimatorSet = AnimatorSet()
        textFadeInFadeOutAnimatorSet.playTogether(animText1, animText2)
        textFadeInFadeOutAnimatorSet.start()
    }

    private fun animatePadding() {
        val textSlideDownUpAnimator: ValueAnimator = ValueAnimator.ofInt(buttonText1.paddingTop, buttonText1.height - buttonText1.paddingTop)
        val textSlideUpDownAnimator: ValueAnimator = ValueAnimator.ofInt(buttonText2.paddingTop, (buttonText2.height + buttonText2.paddingBottom) * -1)

        textSlideDownUpAnimator.apply {
            duration = 300
            interpolator = AccelerateInterpolator()
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
        }

        textSlideUpDownAnimator.apply {
            duration = 300
            interpolator = AccelerateInterpolator()
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 1
        }

        textSlideDownUpAnimator.doOnRepeat {
            buttonText1.text = buttonText2.text
                .also { buttonText2.text = buttonText1.text }
        }

        textSlideDownUpAnimator.addUpdateListener { animation ->
            buttonText1.setPadding(
                buttonText1.paddingLeft,
                animation.animatedValue as Int,
                buttonText1.paddingRight,
                buttonText1.paddingBottom
            )
        }

        textSlideUpDownAnimator.addUpdateListener { animation ->
            buttonText2.setPadding(
                buttonText2.paddingLeft,
                animation.animatedValue as Int,
                buttonText2.paddingRight,
                buttonText2.paddingBottom
                )
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(textSlideDownUpAnimator, textSlideUpDownAnimator)
        animatorSet.start()
    }
}
