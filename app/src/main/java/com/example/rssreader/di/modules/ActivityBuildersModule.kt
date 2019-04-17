package com.example.rssreader.di.modules

import com.example.rssreader.ui.home.HomeActivity
import com.example.rssreader.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}
