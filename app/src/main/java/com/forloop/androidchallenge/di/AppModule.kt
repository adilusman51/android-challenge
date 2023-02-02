package com.forloop.androidchallenge.di

import android.app.Application
import com.forloop.androidchallenge.data.local.InMemoryStoreImpl
import com.forloop.androidchallenge.data.remote.PlacesApi
import com.forloop.androidchallenge.domain.location.LocationTracker
import com.forloop.androidchallenge.domain.repository.InMemoryStore
import com.forloop.androidchallenge.domain.repository.PlacesRepository
import com.forloop.androidchallenge.domain.use_case.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlacesApi(): PlacesApi {
        return Retrofit.Builder()
            .baseUrl(PlacesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideInMemoryStore(): InMemoryStore {
        return InMemoryStoreImpl(listOf())
    }

    @Provides
    @Singleton
    fun provideLocationClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun providesGetNearbyPlacesUseCase(repository: PlacesRepository): GetNearByPlaces {
        return GetNearByPlaces(repository)
    }

    @Provides
    @Singleton
    fun providesGetCurrentLocationUseCase(locationTracker: LocationTracker): GetCurrentLocation {
        return GetCurrentLocation(locationTracker)
    }

    @Provides
    @Singleton
    fun providesSearchNearbyPlaceUseCase(repository: PlacesRepository): SearchNearbyPlaces {
        return SearchNearbyPlaces(repository)
    }


}