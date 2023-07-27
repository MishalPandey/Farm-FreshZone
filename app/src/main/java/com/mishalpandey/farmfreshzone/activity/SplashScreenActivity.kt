package com.mishalpandey.farmfreshzone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.mishalpandey.farmfreshzone.MainActivity
import com.mishalpandey.farmfreshzone.R
import com.mishalpandey.farmfreshzone.databinding.ActivityRegisterBinding
import com.mishalpandey.farmfreshzone.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)




                val topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
                val bottomAnimation= AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

                binding.logo.setAnimation(topAnimation);
                binding.title.setAnimation(bottomAnimation);

                val SPLASH_SCREEN = 4300;

                Handler().postDelayed({
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, SPLASH_SCREEN.toLong())


    }
}