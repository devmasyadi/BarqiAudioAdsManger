package com.ahmadsuyadi.barqiadsmanager.di

import com.ahmadsuyadi.barqiadsmanager.AdsManager
import com.ahmadsuyadi.barqiadsmanager.ads.admob.AdmobAds
import com.ahmadsuyadi.barqiadsmanager.ads.applovin.AppLovinAds
import com.ahmadsuyadi.barqiadsmanager.ads.fan.FanAds
import com.ahmadsuyadi.barqiadsmanager.ads.mopub.MopubAds
import com.ahmadsuyadi.barqiadsmanager.ads.startapp.StartAppAds
import com.ahmadsuyadi.barqiadsmanager.ads.unityads.MyUnityAds
import org.koin.dsl.module

val barqiAdsModule = module {

    single { AdmobAds() }
    single { MyUnityAds() }
    single { FanAds() }
    single { MopubAds() }
    single { StartAppAds() }
    single { AppLovinAds() }

    single {
        AdsManager(get(), get(), get(), get(), get(), get())
    }
}