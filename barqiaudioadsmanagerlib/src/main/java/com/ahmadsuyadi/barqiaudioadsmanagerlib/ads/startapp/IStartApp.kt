package com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.startapp

import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.IAds
import com.startapp.sdk.ads.banner.Banner

interface IStartApp : IAds {
    fun showBanner(banner: Banner)
}