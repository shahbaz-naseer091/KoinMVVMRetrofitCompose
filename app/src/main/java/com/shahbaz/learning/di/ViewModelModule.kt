package com.shahbaz.learning.di

import com.shahbaz.learning.ui.viewmodel.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainViewModel(get()) }
}

