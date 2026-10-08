package com.raytrack.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.raytrack.data.cache.OnBoardingDataStore
import com.raytrack.data.cache.dataStore
import com.raytrack.data.localdb.database.RayTracDatabase
import com.raytrack.data.maps.LocationProvider
import com.raytrack.data.models.Constants.RAYTRACK_DATABASE
import com.raytrack.data.remote.NominatimApiServices
import com.raytrack.data.remote.RetrofitClient
import com.raytrack.data.repository.destination.DestinationImpl
import com.raytrack.data.repository.destination.DestinationRepository
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase
import com.raytrack.data.repository.destination.usecase.DestinationSearchUseCase
import com.raytrack.data.repository.destination.usecase.GetDestinationUseCase
import com.raytrack.data.repository.maps.GeocodingImpl
import com.raytrack.data.repository.maps.GeocodingRepository
import com.raytrack.data.repository.maps.GeocodingSearchLocationUseCase
import com.raytrack.data.repository.maps.ObserveLocationUseCase
import com.raytrack.data.repository.onboarding.OnBoardingImpl
import com.raytrack.data.repository.onboarding.OnBoardingRepository
import com.raytrack.data.repository.onboarding.OnBoardingUseCase
import com.raytrack.presentation.home.HomeViewModel
import com.raytrack.presentation.maps.MapsViewModel
import com.raytrack.presentation.onboarding.OnBoardingViewModel
import com.raytrack.ui.navigation.extensions.AppStartResolve
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
fun AppModule() = module {

    single<NominatimApiServices> {
        RetrofitClient.retrofit.create(
            NominatimApiServices::class.java
        )
    }

    single<GeocodingRepository> {
        GeocodingImpl(get())
    }

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
        LocationProvider(androidContext())
    }

    factory {
        GeocodingSearchLocationUseCase(get())
    }

    factory {
        ObserveLocationUseCase(get())
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
        MapsViewModel(get(), get(), get())
    }
}
