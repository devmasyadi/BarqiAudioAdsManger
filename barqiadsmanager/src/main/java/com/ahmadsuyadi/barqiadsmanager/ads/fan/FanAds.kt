package com.ahmadsuyadi.barqiadsmanager.ads.fan

import android.app.Activity
import android.content.Context
import android.widget.RelativeLayout
import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.ahmadsuyadi.barqiadsmanager.ads.IAds
import com.facebook.ads.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

class FanAds : IAds, AudienceNetworkAds.InitListener, AnkoLogger {

    private var interstitialAd: InterstitialAd? = null
    private lateinit var activity: Activity
    private lateinit var context: Context

    override fun onInitialized(result: AudienceNetworkAds.InitResult?) {
        info("onInitialized FAN result : ${result?.message}")
    }

    override fun initialize(activity: Activity) {
        this.activity = activity
        context = activity.applicationContext
        AudienceNetworkAds.buildInitSettings(activity).withInitListener(this).initialize()
        AdSettings.setTestMode(ConfigAds.isTestAds)
        AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE);
        requestInterstitial()
    }

    private fun requestInterstitial() {
        if (interstitialAd != null) {
            interstitialAd?.destroy()
            interstitialAd = null
        }
        interstitialAd = InterstitialAd(context, ConfigAds.fanInter)
        interstitialAd?.loadAd(
            interstitialAd!!
                .buildLoadAdConfig()
                .withAdListener(interstitialListener)
                .withCacheFlags(EnumSet.of(CacheFlag.VIDEO))
                .build()
        )
    }

    override fun showBanner(adView: RelativeLayout) {
        val bannerAdView = AdView(context, ConfigAds.fanBanner, AdSize.BANNER_HEIGHT_50)
        with(bannerAdView) {
            adView.addView(this)
            loadAd(buildLoadAdConfig().withAdListener(bannerListener).build())
        }
    }

    override fun showInterstitial() {
        if (interstitialAd != null || interstitialAd!!.isAdLoaded) {
            interstitialAd?.show()
        }
    }

    private val bannerListener = object : AdListener {
        override fun onAdClicked(p0: Ad?) {
            info("onAdClicked")
        }

        override fun onError(ad: Ad?, error: AdError?) {
            info("onError ad: $ad, error : ${error?.errorMessage}")
        }

        override fun onAdLoaded(p0: Ad?) {
            info("onAdLoaded")
        }

        override fun onLoggingImpression(p0: Ad?) {
            info("onLoggingImpression")
        }
    }

    private val interstitialListener = object : InterstitialAdListener {
        override fun onInterstitialDisplayed(p0: Ad?) {
            info("onInterstitialDisplayed")
        }

        override fun onAdClicked(p0: Ad?) {
            info("onAdClicked")
        }

        override fun onInterstitialDismissed(p0: Ad?) {
            info("onInterstitialDismissed")
            requestInterstitial()
        }

        override fun onError(p0: Ad?, error: AdError?) {
            info("onError : ${error?.errorMessage}")
        }

        override fun onAdLoaded(p0: Ad?) {
            info("onAdLoaded")
        }

        override fun onLoggingImpression(p0: Ad?) {
            info("onLoggingImpression")
        }
    }

}