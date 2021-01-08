package com.ahmadsuyadi.barqiadsmanager.ads.mopub

import android.content.Context
import com.ahmadsuyadi.barqiadsmanager.BuildConfig
import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.ahmadsuyadi.barqiadsmanager.utils.activity
import com.ahmadsuyadi.barqiadsmanager.utils.visible
import com.mopub.common.MoPub
import com.mopub.common.SdkConfiguration
import com.mopub.common.logging.MoPubLog
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import com.mopub.mobileads.MoPubView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MopubAds(private val context: Context) : IMopub, AnkoLogger {

    private lateinit var sdkConfiguration: SdkConfiguration
    private var mInterstitial: MoPubInterstitial? = null

    override fun initialize() {
        sdkConfiguration = SdkConfiguration.Builder(ConfigAds.mopubBanner)
            .withLogLevel(if (BuildConfig.DEBUG) MoPubLog.LogLevel.DEBUG else MoPubLog.LogLevel.INFO)
            .build()
        MoPub.initializeSdk(context, sdkConfiguration) {
            info("onInitializationFinished")
        }
    }

    override fun initData() {

    }

    override fun showBanner(moPubView: MoPubView) {
        with(moPubView) {
            visible()
            setAdUnitId(ConfigAds.mopubBanner)
            adSize = MoPubView.MoPubAdSize.HEIGHT_50
            loadAd(MoPubView.MoPubAdSize.HEIGHT_50)
            loadAd()
        }
        moPubView.context.activity()?.let {
            mInterstitial = MoPubInterstitial(it, ConfigAds.mopubInter)
            mInterstitial?.interstitialAdListener = interstitialAdListener
            mInterstitial?.load()
        }
    }

    override fun showInterstitial() {
        mInterstitial?.let {
            if (it.isReady)
                it.show()
            else
                it.load()
            info("showInterstitial is Ready : ${it.isReady}")
        }
    }

    private val interstitialAdListener = object : MoPubInterstitial.InterstitialAdListener {
        override fun onInterstitialLoaded(interstitial: MoPubInterstitial?) {
            info("onInterstitialLoaded")
        }

        override fun onInterstitialShown(interstitial: MoPubInterstitial?) {
            info("onInterstitialShown")
        }

        override fun onInterstitialFailed(
            interstitial: MoPubInterstitial?,
            errorCode: MoPubErrorCode?
        ) {
            info("onInterstitialFailed, errorCode : $errorCode")
        }

        override fun onInterstitialDismissed(interstitial: MoPubInterstitial?) {
            info("onInterstitialDismissed")
            mInterstitial?.load()
        }

        override fun onInterstitialClicked(interstitial: MoPubInterstitial?) {
            info("onInterstitialClicked")
            mInterstitial?.load()
        }

    }
}