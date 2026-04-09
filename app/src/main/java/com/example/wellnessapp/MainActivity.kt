package com.example.wellnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.LoadAdError

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Initialize Admob ads
        MobileAds.initialize(this)

        //Load Banner ad
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        //Load Interstitial ad
        loadInterstitialAd()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //find Buttons by IDs
        val healthButton = findViewById<Button>(R.id.health_recipes)
        val hydrationButton = findViewById<Button>(R.id.hydration_alert)
        val nutritionAdviceButton = findViewById<Button>(R.id.nutrition_advice)
        val meditationButton = findViewById<Button>(R.id.meditation)
        val progressButton = findViewById<Button>(R.id.check_progress)
        val exerciseButton = findViewById<Button>(R.id.start_exercise)
        val motivationButton = findViewById<Button>(R.id.daily_motivation)
        val goalsButton = findViewById<Button>(R.id.weekly_goals)


        //Set on click listener
        healthButton.setOnClickListener {
            val intent = Intent(this, HealthyRecipesActivity::class.java)
            startActivity(intent)
        }
        hydrationButton.setOnClickListener {
            val intent = Intent(this, HydrationActivity::class.java)
            startActivity(intent)
        }

        nutritionAdviceButton.setOnClickListener {
            val intent = Intent(this, NutritionAdviceActivity::class.java)
            startActivity(intent)
        }
        meditationButton.setOnClickListener {
            val intent = Intent(this, MeditationActivity::class.java)
            startActivity(intent)
        }
        progressButton.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }
        exerciseButton.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }
        motivationButton.setOnClickListener {
            val intent = Intent(this, MotivationActivity::class.java)
            startActivity(intent)
        }
        goalsButton.setOnClickListener {
            val intent = Intent(this, GoalsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        //Requests interstitial ads
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }

    //Function checks if ad already running not to run another one and overlap - which is wrong
    private fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }
}
