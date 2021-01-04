package com.ahmadsuyadi.barqiaudioadsmanagerlib.di

import com.ahmadsuyadi.barqiaudioadsmanagerlib.AdsManager
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.admob.AdmobAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.fan.FanAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.mopub.MopubAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.startapp.StartAppAds
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.unityads.MyUnityAds
import org.koin.dsl.module

val adsModule = module {
    single { AdmobAds(get()) }
    single { MyUnityAds(get()) }
    single { FanAds(get()) }
    single { MopubAds(get()) }
    single { StartAppAds(get()) }

    single {
        AdsManager(get(), get(), get(), get(), get())
    }
}