package com.raytrack.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.raytrack.data.cache.OnBoardingDataStore
import com.raytrack.data.cache.dataStore
import com.raytrack.data.localdb.database.RayTracDatabase
import com.raytrack.data.models.Constants.RAYTRACK_DATABASE
import com.raytrack.data.repository.destination.DestinationImpl
import com.raytrack.data.repository.destination.DestinationRepository
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase
import com.raytrack.data.repository.destination.usecase.GetDestinationUseCase
import com.raytrack.data.repository.destination.usecase.DestinationSearchUseCase
import com.raytrack.data.repository.onboarding.OnBoardingRepository
import com.raytrack.data.repository.onboarding.OnBoardingImpl
import com.raytrack.data.repository.onboarding.OnBoardingUseCase
import com.raytrack.presentation.home.HomeViewModel
import com.raytrack.presentation.maps.MapsViewModel
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

    single {
        Room.databaseBuilder(
            androidContext(),
            RayTracDatabase::class.java,
            RAYTRACK_DATABASE
        ).build()
    }

    single {
        get<RayTracDatabase>().destinationDao()
    }

    single<OnBoardingRepository> {
        OnBoardingImpl(get())
    }

    single<DestinationRepository> {
        DestinationImpl(get())
    }

    factory {
        OnBoardingUseCase(get())
    }

    factory {
        DestinationSearchUseCase(get())
    }

    factory {
        GetDestinationUseCase(get())
    }

    factory {
        DestinationCommandUseCase(get())
    }

    single {
        AppStartResolve(get())
    }

    viewModel {
        OnBoardingViewModel(get())
    }

    viewModel {
        HomeViewModel(get(), get())
    }

    viewModel {
        MapsViewModel(get())
    }
}
