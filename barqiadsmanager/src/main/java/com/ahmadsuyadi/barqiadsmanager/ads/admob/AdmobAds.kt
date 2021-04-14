package com.ahmadsuyadi.barqiadsmanager.ads.admob

import android.app.Activity
import android.content.Context
import android.widget.RelativeLayout
import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.ahmadsuyadi.barqiadsmanager.ads.IAds
import com.google.android.gms.ads.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class AdmobAds : IAds, AnkoLogger {

    private lateinit var adRequest: AdRequest
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var activity: Activity
    private lateinit var context: Context

    override fun initialize(activity: Activity) {
        this.activity = activity
        context = activity.applicationContext
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf(ConfigAds.testDeviceID))
                .build()
        )
        MobileAds.initialize(activity) { info("onInitialize Admob") }
        adRequest = AdRequest.Builder().build()
        mInterstitialAd = InterstitialAd(context).apply {
            adUnitId =
                if (ConfigAds.isTestAds) "ca-app-pub-3940256099942544/1033173712" else ConfigAds.idInterstitialAdMob
            loadAd(adRequest)
            adListener = object : AdListener() {
                override fun onAdClosed() {
                    if (!mInterstitialAd.isLoading && !mInterstitialAd.isLoaded) {
                        mInterstitialAd.loadAd(adRequest)
                    }
                }
            }
        }
    }

    override fun showBanner(adView: RelativeLayout) {
        val mAdView = AdView(context).apply {
            adUnitId =
                if (ConfigAds.isTestAds) "ca-app-pub-3940256099942544/6300978111" else ConfigAds.idBannerAdMob
            adSize = AdSize.BANNER
        }
        with(mAdView) {
            adView.addView(this)
            loadAd(adRequest)
        }
    }

    override fun showInterstitial() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }

}