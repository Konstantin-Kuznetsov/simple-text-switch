package com.example.konstantin.simpletextswitchexample

import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val animateTextPadding: AnimatorSet by lazy {
        initTextPaddingAnimatorSet()
    }

    private val animateTextAlpha: AnimatorSet by lazy {
        initTextAlphaAnimatorSet()
    }

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

    private fun initTextAlphaAnimatorSet() = AnimatorSet().animateTextAlpha(buttonText1, buttonText2, 200)
    private fun initTextPaddingAnimatorSet() = AnimatorSet().animateTextPadding(buttonText1, buttonText2, 200)

    private fun animatePadding() {
        if (!animateTextPadding.isRunning) {
            animateTextPadding.start()
        }
    }

    private fun animateTextFadeInFadeOut() {
        if (!animateTextAlpha.isRunning) {
            animateTextAlpha.start()
        }
    }
}
