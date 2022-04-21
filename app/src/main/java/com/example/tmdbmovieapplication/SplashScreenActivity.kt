package com.example.tmdbmovieapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.tmdbmovieapplication.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    lateinit var activitySplashScreenBinding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        val fadeOutAnimation =
            AnimationUtils.loadAnimation(this, R.anim.anim_fade_out).also { it.duration = 4000 }

        //starting the animation
        activitySplashScreenBinding.imgSplash.startAnimation(fadeOutAnimation)

        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                activitySplashScreenBinding.imgSplash.visibility = View.GONE
                startActivity(Intent(this@SplashScreenActivity, MoviesActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }
}