package com.ahmadsuyadi.libbarqi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmadsuyadi.barqiadsmanager.ads.AdsManager
import com.ahmadsuyadi.barqiadsmanager.model.ConfigAdsModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val adsManager: AdsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adsManager.setUp(
                ConfigAdsModel(
                        isShowAds = true,
                        isTestAds = true,
                        modeAds = 5,
                        startAppId = "200181702"
                )
        )
        adsManager.initialize(this)

        adsManager.showBanner(adView = bannerView)

        btnShowInter.setOnClickListener {
            adsManager.showInterstitial()
        }

    }
}