package com.shahbaz.test.di

import com.shahbaz.test.data.repository.DataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { DataRepository(get()) }
}

