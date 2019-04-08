package com.example.rssreader.utils.di.modules

import com.example.rssreader.base.BaseActivity
import com.example.rssreader.ui.home.HomeActivity
import com.example.rssreader.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}
