package com.ahmadsuyadi.barqiadsmanager.utils

import com.ahmadsuyadi.barqiadsmanager.ConfigAds
import com.ahmadsuyadi.barqiadsmanager.model.ConfigAdsModel

object ConfigAdsMapper {
    fun mapFromConfigAdsModel(input: ConfigAdsModel) {
        with(input) {
            ConfigAds.isShowAds = isShowAds ?: false
            ConfigAds.isTestAds = isTestAds ?: false
            ConfigAds.modeAds = modeAds ?: 1
            ConfigAds.idBannerAdMob = idBannerAdmob ?: ""
            ConfigAds.testDeviceID = testDeviceID ?: ""
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
            ConfigAds.sdkKeyAppLovin = sdkKeyAppLovin ?: ""
            ConfigAds.appLovinInter = appLovinInter ?: ""
            ConfigAds.appLovinBanner = appLovinBanner ?: ""
            ConfigAds.intervalInt = intervalInt ?: 1
        }
    }
}