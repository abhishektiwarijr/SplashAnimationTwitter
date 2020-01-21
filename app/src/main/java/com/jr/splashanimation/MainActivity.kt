package com.jr.splashanimation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.jr.splashanimation.view.SplashView
import com.jr.splashanimation.view.SplashView.ISplashListener
import java.util.*

class MainActivity : AppCompatActivity() {
    private val random = Random()
    private var mMainView: ViewGroup? = null
    private var mSplashView: SplashView? = null
    private val mHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // change the DO_XML variable to switch between code and xml
        setContentView(R.layout.activity_main)
        mMainView = findViewById(R.id.main_view)
        mSplashView = findViewById(R.id.splash_view)
        // pretend like we are loading data
        startLoadingData()
    }

    private fun startLoadingData() { // finish "loading data" in a random time between 1 and 3 seconds
        mHandler.postDelayed({ onLoadingDataEnded() }, 1000 + random.nextInt(2000).toLong())
    }

    private fun onLoadingDataEnded() { // start the splash animation
        mSplashView?.splashAndDisappear(object : ISplashListener {
            override fun onStart() { // log the animation start event
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "splash started")
                }
            }

            override fun onUpdate(completionFraction: Float) { // log animation update events
                if (BuildConfig.DEBUG) {
                    Log.d(
                        TAG,
                        "splash at " + String.format(
                            "%.2f",
                            completionFraction * 100
                        ) + "%"
                    )
                }
            }

            override fun onEnd() { // log the animation end event
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "splash ended")
                }
                // free the view so that it turns into garbage
                mSplashView = null
                startActivity(
                    Intent(
                        this@MainActivity,
                        NextActivity::class.java
                    )
                )
                finish()
                //Apply splash exit (fade out) and main entry (fade in) animation transitions.
                overridePendingTransition(R.anim.next_fade_in, R.anim.main_fade_out)
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}