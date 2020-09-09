package com.albuquerque.moviecatalog.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.albuquerque.moviecatalog.R
import com.albuquerque.core.view.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*

class SplashActivity : BaseActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityScope.launch {
            logo.startAnimation(
                    AnimationUtils.loadAnimation(
                            this@SplashActivity,
                            R.anim.bounce_animation
                    )
            )

            delay(3000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

}