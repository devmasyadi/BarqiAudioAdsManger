package com.ahmadsuyadi.barqiadsmanager.ads.applovin

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.ahmadsuyadi.barqiadsmanager.utils.activity
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.ads.MaxAdView
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.sdk.AppLovinSdk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.concurrent.TimeUnit
import kotlin.math.pow


class AppLovinAds(private val context: Context) : IAppLovin, AnkoLogger {

    private lateinit var interstitialAd: MaxInterstitialAd
    private var retryAttempt = 0.0
    private lateinit var activity: Activity

    override fun initialize() {
        AppLovinSdk.getInstance(context).mediationProvider = "max"
        AppLovinSdk.getInstance(context).initializeSdk {
            info("Success initialize appLovinAds")
        }
    }

    override fun initData() {

    }

    override fun showBanner(adView: LinearLayout) {
        adView.context.activity()?.let {
            activity = it
        }
        createBannerAd(adView)
        createInterstitialAd()
    }

    override fun showInterstitial() {
        if (interstitialAd.isReady) {
            interstitialAd.showAd();
        }
    }

    private fun createInterstitialAd() {
        interstitialAd = MaxInterstitialAd(ConfigAds.appLovinInter, activity)
        interstitialAd.setListener(interstitialListener)

        // Load the first ad
        interstitialAd.loadAd()
    }

    private var adView: MaxAdView? = null

    private fun createBannerAd(rootView: LinearLayout) {
        adView = MaxAdView(ConfigAds.appLovinBanner, activity)
        adView?.setListener(bannerListener)

        rootView.addView(adView)

        // Load the ad
        adView?.loadAd()
    }

    private val interstitialListener = object : MaxAdListener {
        // MAX Ad Listener
        override fun onAdLoaded(maxAd: MaxAd) {
            // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'

            // Reset retry attempt
            retryAttempt = 0.0
            info("interstitialListener onAdLoaded: ${maxAd.networkName}")
        }

        override fun onAdLoadFailed(adUnitId: String, errorCode: Int) {
            // Interstitial ad failed to load
            // We recommend retrying with exponentially higher delays up to a maximum delay (in this case 64 seconds)
            retryAttempt++
            val delayMillis =
                TimeUnit.SECONDS.toMillis(2.0.pow(6.0.coerceAtMost(retryAttempt)).toLong())

            GlobalScope.launch {
                delay(delayMillis)
                interstitialAd.loadAd()
            }
            error("interstitialListener onAdLoadFailed, adUnitId: $adUnitId, errorCode: $errorCode")
        }

        override fun onAdDisplayFailed(maxAd: MaxAd, errorCode: Int) {
            // Interstitial ad failed to display. We recommend loading the next ad
            interstitialAd.loadAd()
        }

        override fun onAdDisplayed(maxAd: MaxAd) {}

        override fun onAdClicked(maxAd: MaxAd) {}

        override fun onAdHidden(maxAd: MaxAd) {
            // Interstitial ad is hidden. Pre-load the next ad
            interstitialAd.loadAd()
        }

    }

    private val bannerListener = object : MaxAdViewAdListener {
        // MAX Ad Listener
        override fun onAdLoaded(maxAd: MaxAd) {
            info("banner onAdLoaded: ${maxAd.networkName}")
        }

        override fun onAdLoadFailed(adUnitId: String, errorCode: Int) {
            error("banner onAdLoadFailed, adUnitId: $adUnitId, errorCode: $errorCode")
        }

        override fun onAdDisplayFailed(maxAd: MaxAd, errorCode: Int) {}

        override fun onAdClicked(maxAd: MaxAd) {}

        override fun onAdExpanded(maxAd: MaxAd) {}

        override fun onAdCollapsed(maxAd: MaxAd) {}

        override fun onAdDisplayed(maxAd: MaxAd) { /* DO NOT USE - THIS IS RESERVED FOR FULLSCREEN ADS ONLY AND WILL BE REMOVED IN A FUTURE SDK RELEASE */
        }

        override fun onAdHidden(maxAd: MaxAd) { /* DO NOT USE - THIS IS RESERVED FOR FULLSCREEN ADS ONLY AND WILL BE REMOVED IN A FUTURE SDK RELEASE */
        }
    }
}