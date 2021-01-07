package com.ahmadsuyadi.barqiaudioadsmanagerlib

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.admob.AdmobAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.applovin.AppLovinAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.fan.FanAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.mopub.MopubAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.startapp.StartAppAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.unityads.MyUnityAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.model.ConfigAdsModel
import com.mopub.mobileads.MoPubView
import com.startapp.sdk.ads.banner.Banner
import org.jetbrains.anko.AnkoLogger

class AdsManager(
    private val admobAds: AdmobAds,
    private val unityAds: MyUnityAds,
    private val fanAds: FanAds,
    private val mopubAds: MopubAds,
    private val startAppAds: StartAppAds,
    private val appLovinAds: AppLovinAds,
) : AnkoLogger {

    fun setUp(configAdsModel: ConfigAdsModel) {
        with(configAdsModel) {
            ConfigAds.isShowAds = isShowAds ?: false
            ConfigAds.isTestAds = isTestAds ?: false
            ConfigAds.isShowImageAudio = isShowImageAudio ?: false
            ConfigAds.modeAds = modeAds ?: 1
            ConfigAds.idBannerAdMob = idBannerAdmob ?: ""
            ConfigAds.idInterstitialAdMob = idIntAdmob ?: ""
            ConfigAds.idNativeAdmob = idNativeAdmob ?: ""
            ConfigAds.idRewardAdmob = idRewardAdmob ?: ""
            ConfigAds.openIdAdmob = openIdAdmob ?: ""
            ConfigAds.unityGameID = unityGameID ?: ""
            ConfigAds.unityBanner = unityBanner ?: ""
            ConfigAds.unityInter = unityInter ?: ""
            ConfigAds.fanBanner = fanBanner ?: ""
            ConfigAds.fanInter = fanInter ?: ""
            ConfigAds.mopubBanner = mopubBanner ?: ""
            ConfigAds.mopubInter = mopubInter ?: ""
            ConfigAds.startAppId = startAppId ?: ""
            ConfigAds.appLovinInter = appLovinInter ?: ""
            ConfigAds.appLovinBanner = appLovinBanner ?: ""
            ConfigAds.intervalInt = intervalInt ?: 1
            ConfigAds.isOnRedirect = isOnRedirect ?: false
            ConfigAds.urlRedirect = urlRedirect ?: ""
            ConfigAds.urlMoreApp = urlMoreApp ?: ""
            ConfigAds.privacyPolicyApp = privacyPolicyApp ?: ""
        }
    }

    fun initialize() {
        if (ConfigAds.isShowAds)
            when (ConfigAds.modeAds) {
                1 -> admobAds.initialize()
                2 -> fanAds.initialize()
                3 -> unityAds.initialize()
                4 -> mopubAds.initialize()
                5 -> startAppAds.initialize()
                6 -> appLovinAds.initialize()
            }

    }

    fun initData() {
        if (ConfigAds.isShowAds)
            when (ConfigAds.modeAds) {
                1 -> admobAds.initData()
                2 -> fanAds.initData()
                3 -> unityAds.initData()
                4 -> mopubAds.initData()
                5 -> startAppAds.initData()
                6 -> appLovinAds.initData()
            }
    }

    fun showBanner(
        adView: LinearLayout? = null,
        moPubView: MoPubView? = null,
        bannerStartApp: Banner? = null
    ) {
        if (ConfigAds.isShowAds) {
            adView?.let {
                when (ConfigAds.modeAds) {
                    1 -> admobAds.showBanner(it)
                    2 -> fanAds.showBanner(it)
                    3 -> unityAds.showBanner(it)
                    6 -> appLovinAds.showBanner(it)
                }
            }
            moPubView?.let {
                when (ConfigAds.modeAds) {
                    4 -> mopubAds.showBanner(it)
                }
            }
            bannerStartApp?.let {
                when (ConfigAds.modeAds) {
                    5 -> startAppAds.showBanner(it)
                }
            }
        }
    }

    fun showInterstitial() {
        if (ConfigAds.isShowAds) {
            if (ConfigAds.currentCountAds % ConfigAds.intervalInt == 0)
                when (ConfigAds.modeAds) {
                    1 -> admobAds.showInterstitial()
                    2 -> fanAds.showInterstitial()
                    3 -> unityAds.showInterstitial()
                    4 -> mopubAds.showInterstitial()
                    5 -> startAppAds.showInterstitial()
                    6 -> appLovinAds.showInterstitial()
                }
            ConfigAds.currentCountAds++
        }
    }
}