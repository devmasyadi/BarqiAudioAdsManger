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

    single { AdmobAds(get()) }
    single { MyUnityAds(get()) }
    single { FanAds(get()) }
    single { MopubAds(get()) }
    single { StartAppAds(get()) }
    single { AppLovinAds(get()) }

    single {
        AdsManager(get(), get(), get(), get(), get(), get())
    }
}