package com.example.rssreader.utils.di.component

import android.app.Application
import com.example.rssreader.MyApplication
import com.example.rssreader.utils.di.modules.ActivityBuildersModule
import com.example.rssreader.utils.di.modules.AppModule
import com.example.rssreader.utils.di.modules.NetworkModule
import com.example.rssreader.utils.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        NetworkModule::class,
        AppModule::class,
        RepositoryModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
