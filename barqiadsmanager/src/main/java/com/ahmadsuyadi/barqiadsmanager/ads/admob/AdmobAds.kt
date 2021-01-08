package com.ahmadsuyadi.barqiadsmanager.ads.admob

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.google.android.gms.ads.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class AdmobAds : IAdmob, AnkoLogger {

    private var mAdView: AdView? = null
    private lateinit var adRequest: AdRequest
    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var activity: Activity
    private lateinit var context: Context

    override fun initialize(activity: Activity) {
        this.activity = activity
        context = activity.applicationContext
        if (ConfigAds.isTestAds)
            MobileAds.setRequestConfiguration(
                RequestConfiguration.Builder()
                    .setTestDeviceIds(listOf("DB312C879640DB1A1BA381031953D342"))
                    .build()
            )
        MobileAds.initialize(activity) { info("onInitialize Admob") }
    }

    override fun initData() {
        adRequest = AdRequest.Builder().build()
        mAdView = AdView(context).apply {
            adUnitId =
                if (ConfigAds.isTestAds) "ca-app-pub-3940256099942544/6300978111" else ConfigAds.idBannerAdMob
            adSize = AdSize.BANNER
            info("adUnitId banner : $adUnitId")
        }

        mInterstitialAd = InterstitialAd(context).apply {
            adUnitId =
                if (ConfigAds.isTestAds) "ca-app-pub-3940256099942544/1033173712" else ConfigAds.idInterstitialAdMob
            info("adUnitId inter : $adUnitId")
            loadAd(adRequest)
            adListener = object : AdListener() {
                override fun onAdClosed() {
                    if (!mInterstitialAd.isLoading && !mInterstitialAd.isLoaded) {
                        mInterstitialAd.loadAd(adRequest)
                        info("onAdClosed")
                    }
                }
            }
        }
    }

    override fun showBanner(adView: LinearLayout) {
        mAdView?.let {
            with(it) {
                adView.addView(this)
                loadAd(adRequest)
                info("showBanner Admob")
            }
        }
    }

    override fun showInterstitial() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
            info("showInterstitial Admob")
        }
    }

}