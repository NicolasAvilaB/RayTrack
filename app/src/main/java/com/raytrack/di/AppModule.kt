package com.raytrack.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.raytrack.data.cache.OnBoardingDataStore
import com.raytrack.data.cache.dataStore
import com.raytrack.data.remote.onboarding.OnBoardingCacheDataSource
import com.raytrack.data.remote.onboarding.OnBoardingCacheImpl
import com.raytrack.data.remote.onboarding.OnBoardingUseCase
import com.raytrack.presentation.home.HomeViewModel
import com.raytrack.presentation.onboarding.OnBoardingViewModel
import com.raytrack.ui.navigation.extensions.AppStartResolve
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
fun AppModule() = module {

    single<DataStore<Preferences>> {
        androidContext().dataStore
    }

    single {
        OnBoardingDataStore(get())
    }

    single<OnBoardingCacheDataSource> {
        OnBoardingCacheImpl(get())
    }

    factory {
        OnBoardingUseCase(get())
    }

    single {
        AppStartResolve(get())
    }

    viewModel {
        OnBoardingViewModel(get())
    }

    viewModel {
        HomeViewModel()
    }
}
