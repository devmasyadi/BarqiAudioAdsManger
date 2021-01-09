package com.ahmadsuyadi.barqiadsmanager.ads.unityads

import android.app.Activity
import android.content.Context
import android.widget.RelativeLayout
import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.ahmadsuyadi.barqiadsmanager.ads.IAds
import com.unity3d.ads.IUnityAdsListener
import com.unity3d.ads.UnityAds
import com.unity3d.services.banners.BannerErrorInfo
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MyUnityAds : IAds, AnkoLogger {

    private lateinit var activity: Activity
    private lateinit var context: Context

    override fun initialize(activity: Activity) {
        this.activity = activity
        context = activity.applicationContext
        UnityAds.initialize(context, ConfigAds.unityGameID, ConfigAds.isTestAds)
        UnityAds.addListener(iUnityAdsListener)
    }

    override fun showBanner(adView: RelativeLayout) {
        val bottomBanner = BannerView(activity, ConfigAds.unityBanner, UnityBannerSize(320, 50))
        bottomBanner.listener = bannerListener
        adView.addView(bottomBanner)
        bottomBanner.load()
    }

    private val iUnityAdsListener = object : IUnityAdsListener {
        override fun onUnityAdsStart(placementId: String?) {
            info("onUnityAdsStart placementId: $placementId")
        }

        override fun onUnityAdsFinish(placementId: String?, result: UnityAds.FinishState?) {
            info("onUnityAdsFinish placementId: $placementId")
        }

        override fun onUnityAdsError(error: UnityAds.UnityAdsError?, message: String?) {
            info("onUnityAdsError error: $error, message: $message")
        }

        override fun onUnityAdsReady(placementId: String?) {
            info("onUnityAdsReady placementId: $placementId")
        }
    }

    private val bannerListener = object : BannerView.IListener {
        override fun onBannerLeftApplication(p0: BannerView?) {

        }

        override fun onBannerClick(p0: BannerView?) {

        }

        override fun onBannerLoaded(p0: BannerView?) {
            info("onBannerLoaded")
        }

        override fun onBannerFailedToLoad(p0: BannerView?, p1: BannerErrorInfo?) {
            info("onBannerFailedToLoad $p0, $p1")
        }
    }

    override fun showInterstitial() {
        if (UnityAds.isReady(ConfigAds.unityInter))
            UnityAds.show(activity, ConfigAds.unityInter)
    }

}