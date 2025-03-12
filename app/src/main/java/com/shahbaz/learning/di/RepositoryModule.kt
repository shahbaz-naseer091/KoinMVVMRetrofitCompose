package com.shahbaz.learning.di

import com.shahbaz.learning.data.repository.DataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { DataRepository(get()) }
}

