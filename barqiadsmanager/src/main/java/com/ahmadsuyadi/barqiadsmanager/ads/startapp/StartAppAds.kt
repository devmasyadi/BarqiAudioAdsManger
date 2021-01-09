package com.ahmadsuyadi.barqiadsmanager.ads.startapp

import android.app.Activity
import android.content.Context
import android.view.View
import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.ahmadsuyadi.barqiadsmanager.utils.visible
import com.startapp.sdk.ads.banner.Banner
import com.startapp.sdk.ads.banner.BannerListener
import com.startapp.sdk.adsbase.Ad
import com.startapp.sdk.adsbase.StartAppAd
import com.startapp.sdk.adsbase.StartAppSDK
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class StartAppAds : IStartApp, AnkoLogger {

    private lateinit var activity: Activity
    private lateinit var context: Context
    private lateinit var startAppAd: StartAppAd

    override fun initialize(activity: Activity) {
        this.activity = activity
        context = activity.applicationContext
        StartAppSDK.init(context, ConfigAds.startAppId, true)
        StartAppSDK.setTestAdsEnabled(ConfigAds.isTestAds);
        startAppAd = StartAppAd(context)
    }

    override fun showBanner(banner: Banner) {
        banner.visible()
    }

    override fun showInterstitial() {
        startAppAd.showAd(interstitialListener)
    }

    private val interstitialListener = object : AdDisplayListener {
        override fun adHidden(p0: Ad?) {
            info("interstitial adHidden $p0")
        }

        override fun adDisplayed(p0: Ad?) {
            info("interstitial adDisplayed $p0")
        }

        override fun adNotDisplayed(p0: Ad?) {
            info("interstitial adNotDisplayed $p0")
        }

        override fun adClicked(p0: Ad?) {
            info("interstitial onClick $p0")
        }
    }

    private val bannerListener = object : BannerListener {

        override fun onClick(p0: View?) {
            info("banner onClick $p0")
        }

        override fun onFailedToReceiveAd(p0: View?) {
            info("banner onFailedToReceiveAd $p0")
        }

        override fun onImpression(p0: View?) {
            info("banner onImpression $p0")
        }

        override fun onReceiveAd(p0: View?) {
            info("banner onReceiveAd $p0")
        }

    }

}