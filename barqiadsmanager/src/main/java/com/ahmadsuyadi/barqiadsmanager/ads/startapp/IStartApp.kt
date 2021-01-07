package com.ahmadsuyadi.barqiadsmanager.ads.startapp

import com.ahmadsuyadi.barqiadsmanager.ads.IAds
import com.startapp.sdk.ads.banner.Banner

interface IStartApp : IAds {
    fun showBanner(banner: Banner)
}