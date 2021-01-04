package com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.mopub

import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.IAds
import com.mopub.mobileads.MoPubView

interface IMopub : IAds {
    fun showBanner(moPubView: MoPubView)
}