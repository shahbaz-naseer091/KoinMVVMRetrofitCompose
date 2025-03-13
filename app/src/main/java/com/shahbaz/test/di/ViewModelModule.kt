package com.shahbaz.test.di

import com.shahbaz.test.ui.viewmodel.TestViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { TestViewModel(get()) }
}

