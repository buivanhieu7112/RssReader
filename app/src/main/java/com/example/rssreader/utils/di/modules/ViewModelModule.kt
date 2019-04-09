package com.example.rssreader.utils.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rssreader.ui.main.MainViewModel
import com.example.rssreader.ui.main2.Main2ViewModel
import com.example.rssreader.utils.di.ViewModelFactory
import com.example.rssreader.utils.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Main2ViewModel::class)
    abstract fun bindMain2ViewModel(main2ViewModel: Main2ViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
