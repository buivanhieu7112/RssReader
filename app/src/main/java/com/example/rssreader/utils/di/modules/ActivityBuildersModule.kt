package com.example.rssreader.utils.di.modules

import com.example.rssreader.ui.home.HomeActivity
import com.example.rssreader.ui.main.MainActivity
import com.example.rssreader.ui.main2.Main2Activity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMain2Activity(): Main2Activity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}
