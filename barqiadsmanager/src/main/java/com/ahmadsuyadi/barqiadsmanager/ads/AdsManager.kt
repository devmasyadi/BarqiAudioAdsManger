package com.ahmadsuyadi.barqiadsmanager.ads

import android.app.Activity
import android.widget.RelativeLayout
import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.ahmadsuyadi.barqiadsmanager.ads.admob.AdmobAds
import com.ahmadsuyadi.barqiadsmanager.ads.applovin.AppLovinAds
import com.ahmadsuyadi.barqiadsmanager.ads.fan.FanAds
import com.ahmadsuyadi.barqiadsmanager.ads.mopub.MopubAds
import com.ahmadsuyadi.barqiadsmanager.ads.startapp.StartAppAds
import com.ahmadsuyadi.barqiadsmanager.ads.unityads.MyUnityAds
import com.ahmadsuyadi.barqiadsmanager.model.ConfigAdsModel
import com.ahmadsuyadi.barqiadsmanager.utils.ConfigAdsMapper

class AdsManager(
    private val admobAds: AdmobAds,
    private val unityAds: MyUnityAds,
    private val fanAds: FanAds,
    private val mopubAds: MopubAds,
    private val startAppAds: StartAppAds,
    private val appLovinAds: AppLovinAds
) {

    fun setUp(configAdsModel: ConfigAdsModel) {
        ConfigAdsMapper.mapFromConfigAdsModel(configAdsModel)
    }

    fun initialize(activity: Activity) {
        if (ConfigAds.isShowAds)
            when (ConfigAds.modeAds) {
                1 -> admobAds.initialize(activity)
                2 -> fanAds.initialize(activity)
                3 -> unityAds.initialize(activity)
                4 -> mopubAds.initialize(activity)
                5 -> startAppAds.initialize(activity)
                6 -> appLovinAds.initialize(activity)
            }
    }

    fun showBanner(adView: RelativeLayout? = null) {
        if (ConfigAds.isShowAds) {
            adView?.let {
                when (ConfigAds.modeAds) {
                    1 -> admobAds.showBanner(it)
                    2 -> fanAds.showBanner(it)
                    3 -> unityAds.showBanner(it)
                    4 -> mopubAds.showBanner(it)
                    5 -> startAppAds.showBanner(it)
                    6 -> appLovinAds.showBanner(it)
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