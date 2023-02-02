package com.forloop.androidchallenge.data.remote

import com.forloop.androidchallenge.data.remote.dto.PlacesInfoDto
import retrofit2.http.GET

interface PlacesApi {

    @GET("/germany.json")
    suspend fun getPlacesData(): PlacesInfoDto

    companion object {
        const val BASE_URL: String =
            "https://payback-coding-challenge.s3.eu-central-1.amazonaws.com/"
    }
}